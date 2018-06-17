package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.services.AirportService;
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

    @GetMapping("/{country}")
    public @ResponseBody ResponseEntity<List<Airport>> findByCountry(@PathVariable ("country") String country ){
        List<Airport> list = airportService.findByCountry(country);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{country}/{state}")
    public @ResponseBody ResponseEntity<List<Airport>> findByCountryState(@PathVariable ("country") String country, @PathVariable ("state") String state){
        List<Airport> list = airportService.findByCountryState(state,country);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{country}/{state}/{city}")
    public @ResponseBody ResponseEntity<List<Airport>> findByCountryStateCity(@PathVariable ("country") String country
    ,@PathVariable ("state") String state, @PathVariable ("city") String city){
        List<Airport> list = airportService.findByCountryStateCity(city,state,country);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
