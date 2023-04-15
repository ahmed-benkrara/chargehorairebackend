package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Field;
import com.fssm.chargehoraire.Repositories.DepartmentRepository;
import com.fssm.chargehoraire.Repositories.FieldRepository;
import com.fssm.chargehoraire.Requests.FieldRequest;
import com.fssm.chargehoraire.Requests.FieldUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class FieldService {
    @Autowired
    private FieldRepository fieldRepository;
    @Autowired
    private DepartmentRepository departmentRepository;


    public Boolean isFieldExist(String name){
        return fieldRepository.findByName(name) != null;
    }

    public Field create(FieldRequest field){
        if(isFieldExist(field.getName())){
            return null;
        }else{
            Field f = new Field();
            f.setName(field.getName());
            Department department = departmentRepository.findById(field.getDepartment_id()).get();
            f.setDepartment(department);
            return fieldRepository.save(f);
        }
    }

    public List<Field> getAll(){
        return fieldRepository.findAll();
    }

    public Field getById(int id){
        Optional<Field> field = fieldRepository.findById(Long.valueOf(id));
        if(field.isPresent()){
            return field.get();
        }else{
            return null;
        }
    }

    public Field update(FieldUpdateRequest field){
        Optional<Field> find = fieldRepository.findById(Long.valueOf(field.getId()));
        if(find.isPresent()){
            Field found = find.get();
            found.setName(field.getName());
            Department department = departmentRepository.findById(Long.valueOf(field.getDepartment_id())).get();
            found.setDepartment(department);
            return fieldRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        fieldRepository.deleteById(Long.valueOf(id));
    }
}
