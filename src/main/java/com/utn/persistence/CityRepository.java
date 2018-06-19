package com.utn.persistence;

import com.utn.models.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 1/6/2018.
 */
@Repository
public interface CityRepository extends CrudRepository<City, Long>  {

    City findCityByIataCode(String iatacode);

    List<City> findCitiesByState(long idstate);
}
