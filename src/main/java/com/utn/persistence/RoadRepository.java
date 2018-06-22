package com.utn.persistence;

import com.utn.models.Road;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface RoadRepository  extends CrudRepository<Road, Long> {

    Road findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(String origin, String destiny);

    List<Road> findRoadsByAirportorigin_IataCode(String iatacode);

    List<Road> findRoadsByAirportdestiny_IataCode(String iatacode);

}
