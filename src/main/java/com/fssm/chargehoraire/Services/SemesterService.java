package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Semester;
import com.fssm.chargehoraire.Repositories.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public Boolean isModuleExist(String name){
        return semesterRepository.findByName(name) != null;
    }

    public Semester create(Semester semester){
        if(isModuleExist(semester.getName())){
            return null;
        }else{
            Semester s = new Semester();
            s.setName(semester.getName());
            return semesterRepository.save(s);
        }
    }

    public List<Semester> getAll(){
        return semesterRepository.findAll();
    }

    public Semester getById(int id){
        Optional<Semester> semester = semesterRepository.findById(Long.valueOf(id));
        if(semester.isPresent()){
            return semester.get();
        }else{
            return null;
        }
    }

    public Semester update(Semester semester){
        Optional<Semester> find = semesterRepository.findById(Long.valueOf(semester.getId()));
        if(find.isPresent()){
            Semester found = find.get();
            found.setName(semester.getName());
            return semesterRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        semesterRepository.deleteById(Long.valueOf(id));
    }
}
