package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.Admin;
import com.fssm.chargehoraire.Models.Teacher;
import com.fssm.chargehoraire.Models.User;
import com.fssm.chargehoraire.Repositories.AdminRepository;
import com.fssm.chargehoraire.Repositories.TeacherRepository;
import com.fssm.chargehoraire.Repositories.UserRepository;
import com.fssm.chargehoraire.Security.Encryption;
import com.fssm.chargehoraire.Security.TokenSigner;
import com.fssm.chargehoraire.Services.AdminService;
import com.fssm.chargehoraire.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    UserService userService;
    @Autowired
    AdminRepository admin;
    @Autowired
    TeacherRepository teacher;

    @GetMapping("/login/{email}")
    public ResponseEntity<Object> login(@PathVariable String email){
        if(userService.isUserPresent(email)){
            return ResponseEntity.ok(admin.findById(Long.valueOf("2")).get());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }
}
