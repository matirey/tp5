package com.utn.services;

import com.utn.models.CabinsForRoad;
import com.utn.models.Cabin;
import com.utn.models.Road;
import com.utn.persistence.CabinsForRoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class CabinsForRoadService {

    @Autowired
    private CabinsForRoadRepository repository;

    public CabinsForRoad findCabinsForRoadByRoadAndCabin(Road road, Cabin cabin) {
        return repository.findCabinsForRoadByRoadAndCabin(road, cabin);
    }

    public List<CabinsForRoad> findCabinsForRoadByRoad(Road road){
        return repository.findCabinsForRoadsByRoad(road);
    }

    public void save(Cabin cabin, Road road){
        CabinsForRoad cabinsForRoad = new CabinsForRoad();
        cabinsForRoad.setCabin(cabin);
        cabinsForRoad.setRoad(road);
        repository.save(cabinsForRoad);
    }

    /*public void delete(CabinsForRoad cabinsforroad)
    {
        repository.delete(cabinsforroad);
    }*/
}
