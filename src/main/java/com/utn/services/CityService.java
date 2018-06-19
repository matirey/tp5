package com.utn.services;

import com.utn.models.Airport;
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
    private CityRepository repository;

    public City findCityByIataCode(String iatacode){return repository.findCityByIataCode(iatacode);}

    // Guardar
    public void  save(City city){
        repository.save(city);
    }

    // Borrar en cascada
    public void delete(City city)
    {
        repository.delete(city);
    }

}
