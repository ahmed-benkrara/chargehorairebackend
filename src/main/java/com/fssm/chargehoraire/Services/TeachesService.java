package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.*;
import com.fssm.chargehoraire.Repositories.*;
import com.fssm.chargehoraire.Requests.AdmintaskRequest;
import com.fssm.chargehoraire.Requests.TeachesRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeachesService {
    @Autowired
    private TeachesRepository teachesRepository;
    @Autowired
    private YearRepository yearRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private TypeHRepository typeHRepository;

    public Teaches create(TeachesRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            TeachesKey key = new TeachesKey();
            key.setTeacher_id(request.getTeacher_id());
            key.setModule_id(request.getModule_id());
            key.setYear(year);
            Optional<Teaches> teachest = teachesRepository.findById(key);
            if(teachest.isEmpty()){
                Teaches teaches = new Teaches();
                teaches.setKey(key);
                teaches.setSession(sessionRepository.findByName(request.getSession()));
                teaches.setSemester(semesterRepository.findByName(request.getSemester()));
                teaches.setType(typeHRepository.findByName(request.getTypeh()));
                teaches.setHours(request.getHours());
                return teachesRepository.save(teaches);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Teaches getById(TeachesRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            TeachesKey key = new TeachesKey();
            key.setTeacher_id(request.getTeacher_id());
            key.setModule_id(request.getModule_id());
            key.setYear(year);
            Optional<Teaches> teachest = teachesRepository.findById(key);
            if(teachest.isPresent()){
                return teachest.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public List<Teaches> getAll(){
        return teachesRepository.findAll();
    }

    public Teaches update(TeachesRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            TeachesKey key = new TeachesKey();
            key.setTeacher_id(request.getTeacher_id());
            key.setModule_id(request.getModule_id());
            key.setYear(year);
            Optional<Teaches> teachest = teachesRepository.findById(key);
            if(teachest.isPresent()){
                Teaches updated = teachest.get();
                updated.setKey(key);
                updated.setHours(request.getHours());
                updated.setSession(sessionRepository.findByName(request.getSession()));
                updated.setSemester(semesterRepository.findByName(request.getSemester()));
                updated.setType(typeHRepository.findByName(request.getTypeh()));
                return teachesRepository.save(updated);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Boolean delete(TeachesRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            TeachesKey key = new TeachesKey();
            key.setTeacher_id(request.getTeacher_id());
            key.setModule_id(request.getModule_id());
            key.setYear(year);
            Optional<Teaches> teachest = teachesRepository.findById(key);
            try{
                Optional<Teaches> teaches = teachesRepository.findById(key);
                if(teaches.isPresent()){
                    teachesRepository.delete(teaches.get());
                    return true;
                }else{
                    return false;
                }
            }catch(Exception e){
                return false;
            }
        }else{
            return false;
        }
    }
}
