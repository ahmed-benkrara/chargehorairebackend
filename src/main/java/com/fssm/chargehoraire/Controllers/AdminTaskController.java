package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.AdminTask;
import com.fssm.chargehoraire.Models.AdminTaskKey;
import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Year;
import com.fssm.chargehoraire.Requests.AdmintaskRequest;
import com.fssm.chargehoraire.Services.AdminTaskService;
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
public class AdminTaskController {
    @Autowired
    private AdminTaskService adminTaskService;

    @PostMapping("/administrative/create")
    public ResponseEntity<Object> create(@RequestBody AdmintaskRequest request){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(adminTaskService.create(request) != null){
            response.put("message", "Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Something went wrong please try again later");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping("/administratives")
    public List<AdminTask> getAll(){
        return adminTaskService.getAll();
    }

    @PostMapping("/administrative/get")
    public ResponseEntity<AdminTask> getById(@RequestBody AdmintaskRequest request){
        if(adminTaskService.getById(request) != null){
            return ResponseEntity.ok(adminTaskService.getById(request));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/administrative/update")
    public ResponseEntity<Object> update(@RequestBody AdmintaskRequest request){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(adminTaskService.update(request) != null){
            response.put("message", "Updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping("/administrative/delete")
    public ResponseEntity<Void> delete(@RequestBody AdmintaskRequest request){
        if(adminTaskService.delete(request)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
