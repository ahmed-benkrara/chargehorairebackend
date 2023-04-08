package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.User;
import com.fssm.chargehoraire.Repositories.UserRepository;
import com.fssm.chargehoraire.Security.Encryption;
import com.fssm.chargehoraire.Security.TokenSigner;
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
}
