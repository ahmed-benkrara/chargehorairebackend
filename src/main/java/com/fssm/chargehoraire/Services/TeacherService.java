package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Teacher;
import com.fssm.chargehoraire.Repositories.TeacherRepository;
import com.fssm.chargehoraire.Security.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public Teacher register(Teacher teacher) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        teacher.setPassword(Encryption.encrypt(teacher.getPassword(), Encryption.KEY));
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public Teacher getById(int id){
        Optional<Teacher> teacher = teacherRepository.findById(Long.valueOf(id));
        if(teacher.isPresent()){
            return teacher.get();
        }else{
            return null;
        }
    }

    public String update(Teacher teacher){
        Optional<Teacher> find = teacherRepository.findById(Long.valueOf(teacher.getId()));
        if(find.isPresent()){
            Teacher found = find.get();
            found.setFirstName(teacher.getFirstName());
            found.setLastName(teacher.getLastName());

            if(!found.getCin().equalsIgnoreCase(teacher.getCin())){
                if(teacherRepository.findByCin(teacher.getCin()) != null){
                    return "cin";
                }else{
                    found.setCin(teacher.getCin());
                }
            }

            if(!found.getEmail().equalsIgnoreCase(teacher.getEmail())){
                if(teacherRepository.findByEmail(teacher.getEmail()) != null){
                    return "email";
                }else{
                    found.setEmail(teacher.getEmail());
                }
            }

            if(teacher.getPicture() != null){
                found.setPicture(teacher.getPicture());
            }

            Teacher test = teacherRepository.save(found);
            if(test != null){
                return "done";
            }else{
                return "wrong";
            }
        }else{
            return null;
        }
    }
    //@Transactional(noRollbackFor = DataIntegrityViolationException.class)
    public void delete(int id){
        teacherRepository.deleteById(Long.valueOf(id));
    }
}
