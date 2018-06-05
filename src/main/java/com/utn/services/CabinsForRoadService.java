package com.utn.services;

import com.utn.models.CabinsForRoad;
import com.utn.persistence.CabinsForRoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class CabinsForRoadService {

    @Autowired
    private CabinsForRoadRepository repositorio;

    public CabinsForRoad BuscarXID(long id){
        return repositorio.findCabinsForRoadById(id);
    }

    public List<CabinsForRoad> BuscarXCabinYRoad(long idcabin, long idroad){
        return repositorio.findCabinsForRoadByCabinAndRoad(idcabin, idroad);
    }

    // Traer todo
    public List<CabinsForRoad> findAll(){
        return repositorio.findAll();
    }

    // Guardar
    public void  save(CabinsForRoad reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(CabinsForRoad cabinsforroad)
    {
        repositorio.delete(cabinsforroad);
    }
}
