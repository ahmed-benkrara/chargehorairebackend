package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.AdminTask;
import com.fssm.chargehoraire.Models.AdminTaskKey;
import com.fssm.chargehoraire.Models.Year;
import com.fssm.chargehoraire.Repositories.AdminTaskRepository;
import com.fssm.chargehoraire.Repositories.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fssm.chargehoraire.Requests.AdmintaskRequest;

import java.util.List;
import java.util.Optional;

@Service
public class AdminTaskService {
    @Autowired
    private AdminTaskRepository adminTaskRepository;
    @Autowired
    private YearRepository yearRepository;

    public AdminTask create(AdmintaskRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            AdminTaskKey key = new AdminTaskKey();
            key.setDepartment_id(request.getDepartment_id());
            key.setTeacher_id(request.getTeacher_id());
            key.setYear(year);
            Optional<AdminTask> admint = adminTaskRepository.findById(key);
            if(admint.isEmpty()){
                AdminTask adminTask = new AdminTask();
                adminTask.setKey(key);
                adminTask.setHours(request.getHours());
                return adminTaskRepository.save(adminTask);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public AdminTask getById(AdmintaskRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            AdminTaskKey key = new AdminTaskKey();
            key.setDepartment_id(request.getDepartment_id());
            key.setTeacher_id(request.getTeacher_id());
            key.setYear(year);
            Optional<AdminTask> adminTask = adminTaskRepository.findById(key);
            if(adminTask.isPresent()){
                return adminTask.get();
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public List<AdminTask> getAll(){
        return adminTaskRepository.findAll();
    }

    public AdminTask update(AdmintaskRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            AdminTaskKey key = new AdminTaskKey();
            key.setDepartment_id(request.getDepartment_id());
            key.setTeacher_id(request.getTeacher_id());
            key.setYear(year);
            Optional<AdminTask> adminTask = adminTaskRepository.findById(key);
            if(adminTask.isPresent()){
                AdminTask updated = adminTask.get();
                updated.setKey(key);
                updated.setHours(request.getHours());
                return adminTaskRepository.save(updated);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

    public Boolean delete(AdmintaskRequest request){
        Year year = yearRepository.findByName(request.getYear());
        if(year != null){
            AdminTaskKey key = new AdminTaskKey();
            key.setDepartment_id(request.getDepartment_id());
            key.setTeacher_id(request.getTeacher_id());
            key.setYear(year);
            try{
                Optional<AdminTask> admintask = adminTaskRepository.findById(key);
                if(admintask.isPresent()){
                    adminTaskRepository.delete(admintask.get());
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
