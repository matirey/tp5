package com.utn.controllers;

import com.utn.models.Airport;
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

    @PostMapping("/airport")
    public ResponseEntity SaveAirport(@RequestHeader (value="name") String name, @RequestHeader (value="pass") String pass,
                                      @RequestHeader (value="iatacode") String iatacode,
                                      @RequestHeader (value="city-code") String citycode,
                                      @RequestHeader (value="country-code") String countrycode,
                                      @RequestHeader (value="lat") String lat, @RequestHeader (value="lon") String lon){
        Airport airport = new Airport();
        airport.setName(name);
        airport.setIataCode(iatacode);
        airport.setLatitude(lat);
        airport.setLongitude(lon);
        try {
            //verificar password tambien para poder añadir
            airport.setCity(cityService.findCityByIataCode(citycode));
            airportService.save(airport);
            //statecode.saveAirport(statecode,)
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
