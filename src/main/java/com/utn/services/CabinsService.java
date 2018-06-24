package com.utn.services;

import com.utn.models.Cabins;
import com.utn.persistence.CabinsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class CabinsService {

    @Autowired
    private CabinsRepository repositorio;

    // Traer todo
    public List<Cabins> findAll(){
        return repositorio.findAll();
    }

    public Cabins findByName(String name)
    {
        return repositorio.findByName(name);
    }

    // Guardar
    public void  save(String name){
        Cabins cabin = new Cabins();
        cabin.setName(name);
        repositorio.save(cabin);
    }

    // Borrar en cascada
    public void delete(Cabins cabins)
    {
        repositorio.delete(cabins);
    }
}
