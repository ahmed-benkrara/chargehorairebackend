package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Admin;
import com.fssm.chargehoraire.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin create(Admin admin){
        return adminRepository.save(admin);
    }

    public Admin readByEmailAndPassword(String email, String password){
        return adminRepository.findByEmailAndPassword(email, password);
    }
}
