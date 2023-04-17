package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Year;
import com.fssm.chargehoraire.Repositories.YearRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YearService {

    @Autowired
    private YearRepository yearRepository;

    public Boolean isModuleExist(String name){
        return yearRepository.findByName(name) != null;
    }

    public Year create(Year year){
        if(isModuleExist(year.getName())){
            return null;
        }else{
            Year y = new Year();
            y.setName(year.getName());
            return yearRepository.save(y);
        }
    }

    public List<Year> getAll(){
        return yearRepository.findAll();
    }

    public Year getById(int id){
        Optional<Year> year = yearRepository.findById(Long.valueOf(id));
        if(year.isPresent()){
            return year.get();
        }else{
            return null;
        }
    }

    public Year update(Year year){
        Optional<Year> find = yearRepository.findById(Long.valueOf(year.getId()));
        if(find.isPresent()){
            Year found = find.get();
            found.setName(year.getName());
            return yearRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        yearRepository.deleteById(Long.valueOf(id));
    }

}
