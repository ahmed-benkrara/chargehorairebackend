package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Services.DepartmentService;
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
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department/create")
    public ResponseEntity<Object> create(@RequestBody Department department){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(departmentService.create(department) != null){
            response.put("message", "Department Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Department already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/departments")
    public List<Department> getAll(){
        return departmentService.getAll();
    }

    @GetMapping("/department/{id}")
    public Department getDepartment(@PathVariable("id") int id){
        return departmentService.getById(id);
    }

    @PutMapping("/department/update")
    public ResponseEntity<Object> update(@RequestBody Department department){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(departmentService.update(department) != null){
            response.put("message", "Department updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/department/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            departmentService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
