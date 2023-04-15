package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Field;
import com.fssm.chargehoraire.Requests.FieldRequest;
import com.fssm.chargehoraire.Requests.FieldUpdateRequest;
import com.fssm.chargehoraire.Services.FieldService;
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
public class FieldController {
    @Autowired
    private FieldService fieldService;

    @PostMapping("/field/create")
    public ResponseEntity<Object> create(@RequestBody FieldRequest field){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(fieldService.create(field) != null){
            response.put("message", "Field Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Field already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/fields")
    public List<Field> getAll(){
        return fieldService.getAll();
    }

    @GetMapping("/field/{id}")
    public Field getField(@PathVariable("id") int id){
        return fieldService.getById(id);
    }

    @PutMapping("/field/update")
    public ResponseEntity<Object> update(@RequestBody FieldUpdateRequest field){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(fieldService.update(field) != null){
            response.put("message", "Department updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/field/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            fieldService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
