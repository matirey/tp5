package com.utn.services;

import com.utn.models.City;
import com.utn.persistence.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class CityService {

    @Autowired
    private CityRepository repositorio;

    public City BuscarXID(long id){
        return repositorio.findCitiesById(id);
    }

    public List<City> BuscarXState(long id){
        return repositorio.findCitiesByState(id);
    }

    // Guardar
    public void  save(City reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(City city)
    {
        repositorio.delete(city);
    }

}
