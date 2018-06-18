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

    @PostMapping("/add")
    public ResponseEntity SaveAirport(@RequestHeader (value="name") String name, @RequestHeader (value="pass") String pass,
                                      @RequestHeader (value="iatacode") String iatacode,
                                      @RequestHeader (value="city-code") String citycode,
                                      @RequestHeader (value="state-code") String statecode,
                                      @RequestHeader (value="country-code") String countrycode,
                                      @RequestHeader (value="lat") String lat, @RequestHeader (value="lon") String lon){
        Airport airport = new Airport();
        airport.setName(name);
        airport.setIataCode(iatacode);
        airport.setLatitude(lat);
        airport.setLongitude(lon);
        try {
            //verificar password tambien para poder añadir
            airportService.save(airport);
            //cityService.saveAirport(citycode,iatacode);
            //statecode.saveAirport(statecode,)
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
