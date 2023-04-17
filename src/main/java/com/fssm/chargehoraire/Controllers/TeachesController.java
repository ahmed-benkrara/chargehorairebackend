package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.AdminTask;
import com.fssm.chargehoraire.Models.Teaches;
import com.fssm.chargehoraire.Requests.AdmintaskRequest;
import com.fssm.chargehoraire.Requests.TeachesRequest;
import com.fssm.chargehoraire.Services.AdminTaskService;
import com.fssm.chargehoraire.Services.TeachesService;
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
public class TeachesController {
    @Autowired
    private TeachesService teachesService;

    @PostMapping("/teaches/create")
    public ResponseEntity<Object> create(@RequestBody TeachesRequest request){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(teachesService.create(request) != null){
            response.put("message", "Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Something went wrong please try again later");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/teaches")
    public List<Teaches> getAll(){
        return teachesService.getAll();
    }

    @PostMapping("/teaches/get")
    public ResponseEntity<Teaches> getById(@RequestBody TeachesRequest request){
        if(teachesService.getById(request) != null){
            return ResponseEntity.ok(teachesService.getById(request));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/teaches/update")
    public ResponseEntity<Object> update(@RequestBody TeachesRequest request){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(teachesService.update(request) != null){
            response.put("message", "Updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/teaches/delete")
    public ResponseEntity<Void> delete(@RequestBody TeachesRequest request){
        if(teachesService.delete(request)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
