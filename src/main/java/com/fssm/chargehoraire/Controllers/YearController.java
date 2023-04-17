package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Year;
import com.fssm.chargehoraire.Services.YearService;
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
public class YearController {

    @Autowired
    private YearService yearService;

    @PostMapping("/year/create")
    public ResponseEntity<Object> create(@RequestBody Year year){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(yearService.create(year) != null){
            response.put("message", "Year Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Year already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/years")
    public List<Year> getAll(){
        return yearService.getAll();
    }

    @GetMapping("/year/{id}")
    public Year getField(@PathVariable("id") int id){
        return yearService.getById(id);
    }

    @PutMapping("/year/update")
    public ResponseEntity<Object> update(@RequestBody Year year){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(yearService.update(year) != null){
            response.put("message", "Year updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/year/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            yearService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
