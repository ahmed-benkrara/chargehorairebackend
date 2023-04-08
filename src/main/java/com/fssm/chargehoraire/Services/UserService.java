package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.User;
import com.fssm.chargehoraire.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Boolean isUserPresent(String email){
        return userRepository.findByEmail(email) != null;
    }
}
