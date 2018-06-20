package com.utn.services;

import com.utn.models.Airport;
import com.utn.persistence.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public Airport findByIataCode(String iatacode){
        return repository.findAirportByIataCode(iatacode);
    }

    public List<Airport> findByCountry(String countryName){
        return repository.findAirportsByCity_Country_Name(countryName);
    }


    public List<Airport> findall(){
        return repository.findAll();
    }

    public void  save(Airport airport){
        repository.save(airport);
    }

    // cascade delete
    public void delete(Airport airport)
    {
        repository.delete(airport);
    }
}
