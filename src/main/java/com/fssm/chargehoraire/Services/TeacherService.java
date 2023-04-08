package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Teacher;
import com.fssm.chargehoraire.Repositories.TeacherRepository;
import com.fssm.chargehoraire.Security.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher register(Teacher teacher) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        teacher.setPassword(Encryption.encrypt(teacher.getPassword(), Encryption.KEY));
        return teacherRepository.save(teacher);
    }
}
