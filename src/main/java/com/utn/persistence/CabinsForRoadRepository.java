package com.utn.persistence;

import com.utn.models.Cabin;
import com.utn.models.CabinsForRoad;
import com.utn.models.Road;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface CabinsForRoadRepository extends CrudRepository<CabinsForRoad, Long> {

    List<CabinsForRoad> findAll();

    List<CabinsForRoad> findCabinsForRoadByRoad(Road road);

    CabinsForRoad findCabinsForRoadByRoadAndCabin(Road road, Cabin cabin);
}
