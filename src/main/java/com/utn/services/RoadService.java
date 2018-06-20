package com.utn.services;

import com.utn.models.Road;
import com.utn.Utils.Haversine;
import com.utn.persistence.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class RoadService {

    @Autowired
    private RoadRepository repository;

    public Road FindByID(long id){
        return repository.findRoadById(id);
    }

    public Road FindByOriginAndDestiny(long idorigen, long iddestino){
        return repository.findRoadByAirportoriginAndAirportdestiny(idorigen, iddestino);
    }

    public List<Road> FindByOrigin(String iataCode){
        return repository.findRoadByAirportorigin_IataCode(iataCode);
    }

    // Guardar
    public void  save(Road reg){
        repository.save(reg);
    }

    // Borrar en cascada
    public void delete(Road road)
    {
        repository.delete(road);
    }

}
