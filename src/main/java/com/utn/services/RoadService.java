package com.utn.services;

import com.utn.models.Road;
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
    private RoadRepository repositorio;

    public Road BuscarXID(long id){
        return repositorio.findRoadById(id);
    }

    public Road BuscarXOrigenYDestino(long idorigen, long iddestino){
        return repositorio.findRoadByAirportoriginAndAirportdestiny(idorigen, iddestino);
    }

    public List<Road> BuscarXOrigen(long id){
        return repositorio.findRoadByAirportorigin(id);
    }

    // Guardar
    public void  save(Road reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(Road road)
    {
        repositorio.delete(road);
    }

}
