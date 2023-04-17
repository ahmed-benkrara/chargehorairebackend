package com.fssm.chargehoraire.Services;

import com.fssm.chargehoraire.Models.Field;
import com.fssm.chargehoraire.Models.Module;
import com.fssm.chargehoraire.Models.Session;
import com.fssm.chargehoraire.Repositories.SessionRepository;
import com.fssm.chargehoraire.Requests.ModuleRequest;
import com.fssm.chargehoraire.Requests.ModuleUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public Boolean isModuleExist(String name){
        return sessionRepository.findByName(name) != null;
    }

    public Session create(Session session){
        if(isModuleExist(session.getName())){
            return null;
        }else{
            Session s = new Session();
            s.setName(session.getName());
            return sessionRepository.save(s);
        }
    }

    public List<Session> getAll(){
        return sessionRepository.findAll();
    }

    public Session getById(int id){
        Optional<Session> session = sessionRepository.findById(Long.valueOf(id));
        if(session.isPresent()){
            return session.get();
        }else{
            return null;
        }
    }

    public Session update(Session session){
        Optional<Session> find = sessionRepository.findById(Long.valueOf(session.getId()));
        if(find.isPresent()){
            Session found = find.get();
            found.setName(session.getName());
            return sessionRepository.save(found);
        }else{
            return null;
        }
    }

    public void delete(int id){
        sessionRepository.deleteById(Long.valueOf(id));
    }
}
