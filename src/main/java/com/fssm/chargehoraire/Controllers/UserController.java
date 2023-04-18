package com.fssm.chargehoraire.Controllers;

import com.fssm.chargehoraire.Models.User;
import com.fssm.chargehoraire.Requests.EmailTokenRequest;
import com.fssm.chargehoraire.Requests.ForgotRequest;
import com.fssm.chargehoraire.Requests.LoginRequest;
import com.fssm.chargehoraire.Requests.TokenRequest;
import com.fssm.chargehoraire.Security.Encryption;
import com.fssm.chargehoraire.Services.UserService;
import jakarta.mail.MessagingException;
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
@CrossOrigin(origins = "*")
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

    @PostMapping("/forgot")
    public ResponseEntity<Object> forgot(@RequestBody ForgotRequest forgotRequest) throws MessagingException {
        User user = userService.forgotPassword(forgotRequest.getEmail(), forgotRequest.getRoute());
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(user != null){
            response.put("message","We have Sent you a confirmation mail.");
            return ResponseEntity.ok(response);
        }else {
            response.put("message","There is no user with this email !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/isEmailTokenValid")
    public ResponseEntity<Object> isEmailTokenValid(@RequestBody EmailTokenRequest emailTokenRequest){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(userService.isEmailTokenValid(emailTokenRequest.getEmail(), emailTokenRequest.getToken())){
            response.put("message", "Token is valid");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "Token isn't valid");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/setNewPassword")
    public ResponseEntity<Object> setNewPassword(@RequestBody LoginRequest request) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        User user = userService.setNewPassword(request.getEmail(), request.getPassword());
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(user != null){
            response.put("message", "Password updated successfully !");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "This link isn't valid");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/verifyAuth")
    public ResponseEntity<Object> verifyAuth(@RequestBody TokenRequest tokenRequest) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        if(userService.isAuth(tokenRequest.getToken())){
            response.put("message", "User is Authentified");
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "User isn't Authentified");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/getRole/{email}")
    public ResponseEntity<Object> getRole(@PathVariable String email){
        String time = String.valueOf(new Date().getTime());
        Map<String, String> response = new HashMap<>();
        response.put("timestamps", time);
        try {
            email = Encryption.decrypt(email, Encryption.KEY);
            if(userService.getUserRole(email) == null){
                response.put("message", "Something went wrong ! please try again later.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }else{
                response.put("role", userService.getUserRole(email));
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            response.put("message", "Something went wrong !");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping("/getUserName")
    public ResponseEntity<Object> getUserNameByEmail(@RequestBody TokenRequest token) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        Map map = new HashMap();
        map.put("message", this.userService.getUserName(token.getToken()));
        return ResponseEntity.ok(map);
    }
}
