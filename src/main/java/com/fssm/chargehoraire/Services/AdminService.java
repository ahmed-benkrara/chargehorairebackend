package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Admin;
import com.fssm.chargehoraire.Repositories.AdminRepository;
import com.fssm.chargehoraire.Security.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin register(Admin admin) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        admin.setPassword(Encryption.encrypt(admin.getPassword(), Encryption.KEY));
        return adminRepository.save(admin);
    }
}
