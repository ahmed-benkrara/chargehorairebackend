package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Boolean isDepartmentExist(String name){
        return departmentRepository.findByName(name) != null;
    }

    public Department create(Department department){
        if(isDepartmentExist(department.getName())){
            return null;
        }else{
            return departmentRepository.save(department);
        }
    }

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department getById(int id){
        Optional<Department> department = departmentRepository.findById(Long.valueOf(id));
        if(department.isPresent()){
            return department.get();
        }else{
            return null;
        }
    }

    public Department update(Department department){
        Optional<Department> find = departmentRepository.findById(Long.valueOf(department.getId()));
        if(find.isPresent()){
            Department found = find.get();
            found.setName(department.getName());
            return departmentRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        departmentRepository.deleteById(Long.valueOf(id));
    }
}
