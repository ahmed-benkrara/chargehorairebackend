package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.TypeH;
import com.fssm.chargehoraire.Services.TypeHService;
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
public class TypehController {
    @Autowired
    private TypeHService typeHService;

    @PostMapping("/typeh/create")
    public ResponseEntity<Object> create(@RequestBody TypeH typeH){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(typeHService.create(typeH) != null){
            response.put("message", "TypeH Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "TypeH already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/typehs")
    public List<TypeH> getAll(){
        return typeHService.getAll();
    }

    @GetMapping("/typeh/{id}")
    public TypeH getField(@PathVariable("id") int id){
        return typeHService.getById(id);
    }

    @PutMapping("/typeh/update")
    public ResponseEntity<Object> update(@RequestBody TypeH typeH){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(typeHService.update(typeH) != null){
            response.put("message", "TypeH updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/typeh/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            typeHService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

}
