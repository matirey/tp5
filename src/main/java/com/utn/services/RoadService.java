package com.utn.services;

import com.utn.models.Road;
import com.utn.Utils.Haversine;
import com.utn.persistence.RoadRepository;
import com.utn.models.Airport;
import com.utn.persistence.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class RoadService {

    @Autowired
    private RoadRepository repository;

    public List<Road> findRoadsByOrigin(String iatacode){
        return repository.findRoadsByAirportorigin_IataCode(iatacode);
    }

    public List<Road> findRoadsByDestiny(String iatacode){
        return repository.findRoadsByAirportdestiny_IataCode(iatacode);
    }

    public void  save(Road road){
        repository.save(road);
    }

    public void delete(Road road)
    {
        repository.delete(road);
    }

    public void save(Airport origin, Airport destiny){
        Road road = new Road();
        road.setAirportorigin(origin);
        road.setAirportdestiny(destiny);
        road.setDistance(Haversine.distance(origin.getLatitude(), origin.getLongitude(),
                destiny.getLatitude(), destiny.getLongitude()));
        repository.save(road);
    }

}
