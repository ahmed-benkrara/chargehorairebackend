package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Semester;
import com.fssm.chargehoraire.Models.Session;
import com.fssm.chargehoraire.Repositories.SemesterRepository;
import com.fssm.chargehoraire.Services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @PostMapping("/semester/create")
    public ResponseEntity<Object> create(@RequestBody Semester semester){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(semesterService.create(semester) != null){
            response.put("message", "Semester Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Semester already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/semesters")
    public List<Semester> getAll(){
        return semesterService.getAll();
    }

    @GetMapping("/semester/{id}")
    public Semester getField(@PathVariable("id") int id){
        return semesterService.getById(id);
    }

    @PutMapping("/semester/update")
    public ResponseEntity<Object> update(@RequestBody Semester semester){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(semesterService.update(semester) != null){
            response.put("message", "Semester updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/semester/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            semesterService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
