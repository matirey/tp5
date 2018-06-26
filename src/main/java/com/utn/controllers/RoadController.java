package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.models.Road;
import com.utn.services.AirportService;
import com.utn.services.RoadService;
import com.utn.wrappers.RoadWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marcosp on 19/6/2018.
 */
@RestController
@RequestMapping("/api/roads")
public class RoadController {

    @Autowired
    RoadService roadService;
    @Autowired
    AirportService airportService;

    @GetMapping(value="/origin/{iatacode}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Road>> GetRoadsByOriginIata(@PathVariable ("iatacode") String iatacode ) {
        List<Road> list = roadService.findRoadsByOrigin(iatacode);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/destiny/{iatacode}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Road>> GetRoadsByDestinyIata(@PathVariable ("iatacode") String iatacode ) {
        List<Road> list = roadService.findRoadsByDestiny(iatacode);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Post -> RoadWrapper
     * {
     *     origin:origin_iatacode,
     *     destiny:destiny_iatacode
     * }
     */
    @PostMapping(value="", produces = "application/json")
    public @ResponseBody ResponseEntity<Road> GetRoadsByOriginAndDestiny(@RequestBody RoadWrapper request) {
        try{
            Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(request.getOrigin(),request.getDestiny());
            if(road!=null){
                return new ResponseEntity<>(road,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="", consumes = "application/json")
    public ResponseEntity SaveRoad(@RequestBody RoadWrapper request){
        try {
            Airport origin = airportService.findByIataCode(request.getOrigin());
            Airport destiny = airportService.findByIataCode(request.getDestiny());
            if(origin!=null && destiny!=null) {
                roadService.save(origin,destiny);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
