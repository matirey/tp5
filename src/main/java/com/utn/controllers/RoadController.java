package com.utn.controllers;

import com.utn.models.Airport;
import com.utn.models.Road;
import com.utn.services.RoadService;
import com.utn.services.AirportService;
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
    AirportService airportService;

    @Autowired
    RoadService roadService;

    @GetMapping("/{iata}")
    public @ResponseBody ResponseEntity<List<Road>> GetRoadsByIata(@PathVariable ("iata") String iata ) {
        List<Road> list = roadService.FindByOrigin(iata);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/road")
    public ResponseEntity SaveRoad(@RequestHeader (value="iataCodeOrigin") String iataCodeOrigin,
                                   @RequestHeader (value="iataCodeDestiny") String iataCodeDestiny,
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

}
