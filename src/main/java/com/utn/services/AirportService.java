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
    private AirportRepository repositorio;

    public Airport findByIataCode(String iatacode){
        return repositorio.findAirportByIataCode(iatacode);
    }

    public List<Airport> findByCountry(String countryName){
        return repositorio.findAirportsByCity_State_Country_Name(countryName);
    }

    public List<Airport> findByCountryState(String stateName, String countryName){
        return repositorio.findAirportByCity_State_NameAndCity_State_Country_Name(stateName,countryName);
    }

    public List<Airport> findByCountryStateCity(String city,String state, String country){
        return repositorio.findAirportsByCity_NameAndCity_State_NameAndCity_State_Country_Name(country,state,city);
    }

    public Iterable<Airport> findall(){
        return repositorio.findAll();
    }

    public void  save(Airport airport){
        repositorio.save(airport);
    }

    // cascade delete
    public void delete(Airport airport)
    {
        repositorio.delete(airport);
    }
}
