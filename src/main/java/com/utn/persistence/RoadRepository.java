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

    Road findRoadById(long id);

    List<Road> findRoadByAirportorigin_IataCode(String iataCode);

    Road findRoadByAirportoriginAndAirportdestiny(long idairportorigin, long idairportdestiny);
}
