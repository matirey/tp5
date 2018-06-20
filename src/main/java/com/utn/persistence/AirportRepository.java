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

    List<Airport> findAll();

    Airport findAirportByIataCode(String iatacode);

    List<Airport> findAirportsByCity_IataCode(String iatacode);

    List<Airport> findAirportsByCity_Country_Name(String country);

}