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

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping("/admin/register")
    public ResponseEntity<Object> register(@RequestBody Admin admin) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(!userService.isUserPresent(admin.getEmail())){
            if(adminService.register(admin) != null){
                return ResponseEntity.status(HttpStatus.CREATED).body("Admin Created Successfully");
            }else{
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong ! please try again later.");
            }
        }else{
            return ResponseEntity.status(HttpStatus.CONFLICT).body("We already have a user with this email !");
        }
    }
}
