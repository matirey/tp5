package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.request.AirportReq;
import com.utn.services.AirportService;
import com.utn.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Matias on 14/06/2018.
 */
@RestController
@RequestMapping("/api")
public class AirportController {
    @Autowired
    AirportService airportService;
    @Autowired
    CityService cityService;

    @GetMapping("/{country}")
    public @ResponseBody ResponseEntity<List<Airport>> findByCountry(@PathVariable ("country") String country ){
        List<Airport> list = airportService.findByCountry(country);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value="/airport", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveAirport(@RequestBody AirportReq request){
        Airport airport = new Airport();
        airport.setName(request.getName());
        airport.setIataCode(request.getIataCode());
        airport.setLatitude(request.getLatitude());
        airport.setLongitude(request.getLongitude());
        try {
            //verificar password tambien para poder añadir
            airport.setCity(cityService.findCityByIataCode(request.getCityCode()));
            airportService.save(airport);
            return new ResponseEntity <>(airport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/airports")
    public @ResponseBody ResponseEntity<List<Airport>> findAll(){
        List<Airport> list = airportService.findall();
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
