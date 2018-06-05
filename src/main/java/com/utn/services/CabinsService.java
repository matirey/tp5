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

    public Cabins BuscarXID(long id){
        return repositorio.findCabinsById(id);
    }

    // Traer todo
    public List<Cabins> findAll(){
        return repositorio.findAll();
    }

    // Guardar
    public void  save(Cabins reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(Cabins cabins)
    {
        repositorio.delete(cabins);
    }
}
