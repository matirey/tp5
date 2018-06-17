package com.utn.persistence;

import com.utn.models.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {

    //List<Airport> findAll();

    Airport findAirportByIataCode(String iatacode);

    List<Airport> findAirportsByCity_NameAndCity_State_NameAndCity_State_Country_Name(String city, String state, String country);

    List<Airport> findAirportByCity_State_NameAndCity_State_Country_Name(String stateName, String countryName);

    List<Airport> findAirportsByCity_State_Country_Name(String country);

}