package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Requests.LoginRequest;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String result = userService.login(loginRequest.getEmail(), loginRequest.getPassword());
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(result == null){
            //user not found
            response.put("message", "There is no user with this email !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else if(result.equals("0")){
            //incorrect password
            response.put("message", "Password is incorrect !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }else{
            //success returns token
            Map<String, String> token = new HashMap<>();
            token.put("token", result);
            token.put("timestamps", time);
            return ResponseEntity.ok(token);
        }
    }
}
