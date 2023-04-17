package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Admin;
import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Teacher;
import com.fssm.chargehoraire.Services.TeacherService;
import com.fssm.chargehoraire.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    UserService userService;

    @PostMapping("/teacher/register")
    public ResponseEntity<Object> register(@RequestBody Teacher teacher) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(!userService.isUserPresent(teacher.getEmail())){
            if(teacherService.register(teacher) != null){
                response.put("message", "Teacher Created Successfully");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }else{
                response.put("message","Something went wrong ! please try again later.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        }else{
            response.put("message","We already have a user with this email !");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @GetMapping("/teachers")
    public List<Teacher> getAll(){
        return teacherService.getAll();
    }

    @GetMapping("/teacher/{id}")
    public Teacher getTeacher(@PathVariable("id") int id){
        System.out.println("hellooooooooooo");
        return teacherService.getById(id);
    }

    @PutMapping("/teacher/update")
    public ResponseEntity<Object> update(@RequestBody Teacher teacher){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(teacherService.update(teacher) != null){
            if(teacherService.update(teacher).equalsIgnoreCase("cin")){
                response.put("message", "a professor with this cin already exists");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }else{
                if(teacherService.update(teacher).equalsIgnoreCase("email")){
                    response.put("message", "a professor with this email already exists");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }else{
                    if(teacherService.update(teacher).equalsIgnoreCase("done")){
                        response.put("message", "Professor updated successfully.");
                        return ResponseEntity.ok(response);
                    }else{
                        response.put("message", "something went wrong please try again later.");
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                    }
                }
            }
        }else{
            response.put("message", "Professor not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/teacher/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id){

            teacherService.delete(id);
            return ResponseEntity.noContent().build();
    }
}
