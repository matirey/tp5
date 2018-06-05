package com.utn.persistence;

import com.utn.models.CabinsForRoad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface CabinsForRoadRepository extends CrudRepository<CabinsForRoad, Long> {

    CabinsForRoad findCabinsForRoadById(long id);

    List<CabinsForRoad> findAll();

    List<CabinsForRoad> findCabinsForRoadByCabinAndRoad(long idcabin, long idroad);
}
