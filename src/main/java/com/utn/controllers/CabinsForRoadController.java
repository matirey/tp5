package com.utn.controllers;

import com.utn.models.Cabin;
import com.utn.models.Road;
import com.utn.models.CabinsForRoad;
import com.utn.services.CabinService;
import com.utn.services.RoadService;
import com.utn.services.CabinsForRoadService;
import com.utn.wrappers.CabinsForRoadWrapper;
import com.utn.wrappers.RoadWrapper;
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
    CabinService cabinService;

    @Autowired
    RoadService roadService;

    @Autowired
    CabinsForRoadService cabinsForRoadService;

    @PostMapping(value="/cabin", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveCabinsForRoad(@RequestBody CabinsForRoadWrapper request){
        try{
            cabinsForRoadService.save(cabinService.findByName(request.getCabin()),
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
        if(road!=null){
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

    @GetMapping(value="/{cabin}", produces = "application/json")
    public @ResponseBody ResponseEntity<CabinsForRoad> GetCabinsForRoadByRoadAndCabin(@RequestBody RoadWrapper request,
                                                                                      @PathVariable ("cabin") String cabinName){
        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(request.getOrigin(), request.getDestiny());
        Cabin cabin = cabinService.findByName(cabinName);
        if(road!=null && cabin!=null){
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
