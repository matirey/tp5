package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.services.AirportService;
import com.utn.services.CityService;
import com.utn.wrappers.AirportWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Matias on 14/06/2018.
 */
@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @Autowired
    CityService cityService;

    @PostMapping(value="", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveAirport(@RequestBody AirportWrapper request){
        try {
            airportService.save(request.getName(),request.getIataCode(),request.getLatitude(),
                    request.getLongitude(),cityService.findCityByIataCode(request.getCityCode()));
            return new ResponseEntity <>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<Airport>> findAll(){
        List<Airport> list = airportService.findall();
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{iatacode}")
    public @ResponseBody ResponseEntity<Airport> findAirportByIataCode(@PathVariable (value="iatacode")String iatacode){
        try{
            Airport airport= airportService.findByIataCode(iatacode);
            if(airport!=null){
                return new ResponseEntity<>(airport,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
