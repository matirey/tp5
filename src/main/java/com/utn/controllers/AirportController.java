package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.models.City;
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
        System.out.println(request.getLongitude() + " latitude");
        System.out.println(cityService.findCityByIataCode(request.getCityCode()).getName());
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
    public @ResponseBody ResponseEntity<AirportWrapper> findAirportByIataCode(@RequestBody AirportWrapper request){
        try{
            Airport airport= airportService.findByIataCode(request.getIataCode());
            AirportWrapper airportWrapper = new AirportWrapper(airport.getName(),airport.getIataCode(),airport.getLatitude(),
                    airport.getLongitude(),airport.getCity().getIataCode());
            return new ResponseEntity<>(airportWrapper,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
