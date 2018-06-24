package com.utn.services;

import com.utn.models.CabinsForRoad;
import com.utn.models.Cabins;
import com.utn.models.Road;
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

    public List<CabinsForRoad> findCabinsForRoadByRoad(Road road){
        return repositorio.findCabinsForRoadByRoad(road);
    }

    public CabinsForRoad findCabinsForRoadByRoadAndCabin(Road road, Cabins cabin)
    {
        return repositorio.findCabinsForRoadByRoadAndCabin(road, cabin);
    }

    // Traer todo
    public List<CabinsForRoad> findAll(){
        return repositorio.findAll();
    }

    // Guardar
    public void  save(Cabins cabin, Road road){
        CabinsForRoad cabinsForRoad = new CabinsForRoad();
        cabinsForRoad.setCabin(cabin);
        cabinsForRoad.setRoad(road);
        repositorio.save(cabinsForRoad);
    }

    // Borrar en cascada
    public void delete(CabinsForRoad cabinsforroad)
    {
        repositorio.delete(cabinsforroad);
    }
}
