package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Session;
import com.fssm.chargehoraire.Services.SessionService;
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
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping("/session/create")
    public ResponseEntity<Object> create(@RequestBody Session session){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(sessionService.create(session) != null){
            response.put("message", "Session Created Successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            response.put("message", "Session already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping("/sessions")
    public List<Session> getAll(){
        return sessionService.getAll();
    }

    @GetMapping("/session/{id}")
    public Session getField(@PathVariable("id") int id){
        return sessionService.getById(id);
    }

    @PutMapping("/session/update")
    public ResponseEntity<Object> update(@RequestBody Session session){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(sessionService.update(session) != null){
            response.put("message", "Session updated successfully.");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Something went wrong please try again later !");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/session/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){
        try{
            sessionService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
