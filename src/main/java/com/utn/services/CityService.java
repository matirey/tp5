package com.utn.services;

import com.utn.models.Airport;
import com.utn.models.City;
import com.utn.models.Country;
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

    public void save(City city){
        repository.save(city);
    }

    public City save(String name, String iatacode, String state, Country country){
        City city = new City();
        city.setName(name);
        city.setIataCode(iatacode);
        city.setState(state);
        city.setCountry(country);
        return repository.save(city);
    }

    public void delete(City city)
    {
        repository.delete(city);
    }

}
