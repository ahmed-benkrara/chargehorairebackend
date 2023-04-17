package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Department;
import com.fssm.chargehoraire.Models.Field;
import com.fssm.chargehoraire.Repositories.FieldRepository;
import com.fssm.chargehoraire.Repositories.ModuleRepository;
import com.fssm.chargehoraire.Requests.FieldUpdateRequest;
import com.fssm.chargehoraire.Requests.ModuleRequest;
import com.fssm.chargehoraire.Requests.ModuleUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fssm.chargehoraire.Models.Module;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private FieldRepository fieldRepository;

    public Boolean isModuleExist(String name){
        return moduleRepository.findByName(name) != null;
    }

    public Module create(ModuleRequest module){
        if(isModuleExist(module.getName())){
            return null;
        }else{
            Module m = new Module();
            m.setName(module.getName());
            Field field = fieldRepository.findById(module.getField_id()).get();
            m.setField(field);
            return moduleRepository.save(m);
        }
    }

    public List<Module> getAll(){
        return moduleRepository.findAll();
    }

    public Module getById(int id){
        Optional<Module> module = moduleRepository.findById(Long.valueOf(id));
        if(module.isPresent()){
            return module.get();
        }else{
            return null;
        }
    }

    public Module update(ModuleUpdateRequest module){
        Optional<Module> find = moduleRepository.findById(Long.valueOf(module.getId()));
        if(find.isPresent()){
            Module found = find.get();
            found.setName(module.getName());
            Field field = fieldRepository.findById(Long.valueOf(module.getField_id())).get();
            found.setField(field);
            return moduleRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        moduleRepository.deleteById(Long.valueOf(id));
    }
}
