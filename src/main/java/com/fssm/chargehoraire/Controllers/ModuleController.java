package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Requests.FieldUpdateRequest;
import com.fssm.chargehoraire.Requests.ModuleRequest;
import com.fssm.chargehoraire.Requests.ModuleUpdateRequest;
import com.fssm.chargehoraire.Services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fssm.chargehoraire.Models.Module;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

    @PostMapping("/module/create")
    public ResponseEntity<Object> create(@RequestBody ModuleRequest module){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(moduleService.create(module) != null){
            response.put("message", "Module Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Module already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/modules")
    public List<Module> getAll(){
        return moduleService.getAll();
    }

    @GetMapping("/module/{id}")
    public Module getField(@PathVariable("id") int id){
        return moduleService.getById(id);
    }

    @PutMapping("/module/update")
    public ResponseEntity<Object> update(@RequestBody ModuleUpdateRequest module){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(moduleService.update(module) != null){
            response.put("message", "Module updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/module/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            moduleService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
