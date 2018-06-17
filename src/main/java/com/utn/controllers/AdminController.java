package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by Matias on 17/06/2018.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AirportService airportService;
    @Autowired
    CityService cityService;
    @Autowired
    CountryService countryService;
    @Autowired
    PricesService pricesService;
    @Autowired
    StateService stateService;
    @Autowired
    RoadService roadService;
    @Autowired
    CabinsService cabinsService;
    @Autowired
    CabinsForRoadService cabinsForRoadService;

    //ACA SE VAN LAS FUNCIONES PARA SAVE-UPDATE-DELETE

    @PostMapping("airport/add")
    public ResponseEntity SaveAirport(@RequestHeader (value="name") String name,
                                      @RequestHeader (value="message") String message,
                                      @RequestHeader (value="iatacode") String iatacode,
                                      @RequestHeader (value="city-code") String citycode,
                                      @RequestHeader (value="country-code") String countrycode,
                                      @RequestHeader (value="lat") String lat, @RequestHeader (value="lon") String lon){
        Airport airport = new Airport();
        airport.setName(name);
        airport.setMessage(message);
        airport.setIatacode(iatacode);
        airport.setLat(lat);
        airport.setLon(lon);
        try {
            airportService.save(airport);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
