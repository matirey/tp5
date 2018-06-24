package com.utn.controllers;

import com.utn.models.Cabins;
import com.utn.models.Road;
import com.utn.models.CabinsForRoad;
import com.utn.services.CabinsService;
import com.utn.services.RoadService;
import com.utn.services.CabinsForRoadService;
import com.utn.wrappers.CabinsForRoadWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marcosp on 23/6/2018.
 */
@RestController
@RequestMapping("/api/cabinsforroad")
public class CabinsForRoadController {

    @Autowired
    CabinsService cabinsService;

    @Autowired
    RoadService roadService;

    @Autowired
    CabinsForRoadService cabinsForRoadService;

    @PostMapping(value="/cabin", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveCabinsForRoad(@RequestBody CabinsForRoadWrapper request){
        try{
            cabinsForRoadService.save(cabinsService.findByName(request.getCabin()),
                    roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(request.getOrigin(), request.getDestiny()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{origin}/{destiny}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<CabinsForRoad>> GetCabinsForRoadByRoad(@PathVariable ("origin") String origin,
                                                                                    @PathVariable ("destiny") String destiny){

        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(origin, destiny);
        if(!road.equals(null)){
            List<CabinsForRoad> cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoad(road);
            if(cabinsForRoad.size()>0){
                return new ResponseEntity<>(cabinsForRoad, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/{origin}/{destiny}/{cabin}", produces = "application/json")
    public @ResponseBody ResponseEntity<CabinsForRoad> GetCabinsForRoadByRoadAndCabin(@PathVariable ("origin") String origin,
                                                                                      @PathVariable ("destiny") String destiny,
                                                                                      @PathVariable ("cabin") String cabinname){

        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(origin, destiny);
        Cabins cabin = cabinsService.findByName(cabinname);
        if(!road.equals(null) && !cabin.equals(null)){
            CabinsForRoad cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road, cabin);
            try{
                return new ResponseEntity<>(cabinsForRoad, HttpStatus.CREATED);
            }
            catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
