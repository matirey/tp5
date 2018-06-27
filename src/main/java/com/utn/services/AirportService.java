package com.utn.services;

import com.utn.models.Airport;
import com.utn.models.City;
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

    public List<Airport> findall(){
        return repository.findAll();
    }

    public void  save(Airport airport){
        repository.save(airport);
    }

    public void save(String name,String iatacode, double lat, double lon, City city){
        Airport airport = new Airport();
        airport.setName(name);
        airport.setIataCode(iatacode);
        airport.setLatitude(lat);
        airport.setLongitude(lon);
        airport.setCity(city);
        repository.save(airport);
    }
    // cascade delete
   /* public void delete(Airport airport)
    {
        repository.delete(airport);
    }*/
}
