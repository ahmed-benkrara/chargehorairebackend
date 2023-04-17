package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.TypeH;
import com.fssm.chargehoraire.Repositories.TypeHRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeHService {
    @Autowired
    private TypeHRepository typeHRepository;

    public Boolean isModuleExist(String name){
        return typeHRepository.findByName(name) != null;
    }

    public TypeH create(TypeH typeH){
        if(isModuleExist(typeH.getName())){
            return null;
        }else{
            TypeH s = new TypeH();
            s.setName(typeH.getName());
            return typeHRepository.save(s);
        }
    }

    public List<TypeH> getAll(){
        return typeHRepository.findAll();
    }

    public TypeH getById(int id){
        Optional<TypeH> typeH = typeHRepository.findById(Long.valueOf(id));
        if(typeH.isPresent()){
            return typeH.get();
        }else{
            return null;
        }
    }

    public TypeH update(TypeH typeH){
        Optional<TypeH> find = typeHRepository.findById(Long.valueOf(typeH.getId()));
        if(find.isPresent()){
            TypeH found = find.get();
            found.setName(typeH.getName());
            return typeHRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        typeHRepository.deleteById(Long.valueOf(id));
    }

}
