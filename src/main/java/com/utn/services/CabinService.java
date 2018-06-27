package com.utn.services;

import com.utn.models.Cabin;
import com.utn.persistence.CabinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class CabinService {

    @Autowired
    private CabinsRepository repository;

    public List<Cabin> findAll(){
        return repository.findAll();
    }

    public Cabin findByName(String name)
    {
        return repository.findByName(name);
    }

    public void save(String name){
        Cabin cabin = new Cabin();
        cabin.setName(name);
        repository.save(cabin);
    }

    /*public void delete(Cabin cabin)
    {
        repository.delete(cabin);
    }*/
}
