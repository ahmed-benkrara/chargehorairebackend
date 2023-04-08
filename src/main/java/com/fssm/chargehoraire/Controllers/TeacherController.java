package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Admin;
import com.fssm.chargehoraire.Models.Teacher;
import com.fssm.chargehoraire.Services.TeacherService;
import com.fssm.chargehoraire.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
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
}
