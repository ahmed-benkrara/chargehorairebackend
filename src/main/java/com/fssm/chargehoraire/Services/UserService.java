package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.User;
import com.fssm.chargehoraire.Repositories.UserRepository;
import com.fssm.chargehoraire.Security.EmailToken;
import com.fssm.chargehoraire.Security.Encryption;
import com.fssm.chargehoraire.Security.TokenSigner;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailSenderService emailSenderService;

    public Boolean isUserPresent(String email){
        return userRepository.findByEmail(email) != null;
    }

    //return null for not found, "0" for incorrect password, token for success
    public String login(String email, String password) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        if(isUserPresent(email)){
            User user = userRepository.findByEmail(email);
            if(Encryption.validatePassword(password,user.getPassword())){
                TokenSigner signer = new TokenSigner(Encryption.KEY);
                Date now = new Date();
                Date nowPlus24 = new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000));
                String token = "";
                String at = String.valueOf(now.getTime());
                String exp = String.valueOf(nowPlus24);
                token = Encryption.encrypt(email, Encryption.KEY) + "." + Encryption.encrypt(at, Encryption.KEY) + "." + Encryption.encrypt(exp, Encryption.KEY);
                token = signer.signToken(token);
                return token;
            }else{
                return "0";
            }
        }else{
            return null;
        }
    }

    public User forgotPassword(String email, String route) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            String token = EmailToken.generateToken(20);
            String body = "<h2>Recover your account</h2><br><a href=\""+route+"/"+email+"/"+token+"\">click here to have a new password</a>";
            emailSenderService.sendEmail(email, "Recover your account", body);
            user.setToken(token);
            userRepository.save(user);
            return user;
        }else{
            return null;
        }
    }

    public Boolean isEmailTokenValid(String email, String token){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return user.getToken().equals(token);
        }else{
            return false;
        }
    }

    public User setNewPassword(String email, String newpass) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        User user = userRepository.findByEmail(email);
        if(user != null){
            user.setPassword(Encryption.encrypt(newpass, Encryption.KEY));
            return userRepository.save(user);
        }else{
            return null;
        }
    }
}
