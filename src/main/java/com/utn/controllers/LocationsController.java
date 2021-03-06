package com.utn.controllers;

import com.utn.models.City;
import com.utn.models.Country;
import com.utn.services.CityService;
import com.utn.services.CountryService;
import com.utn.wrappers.CityWrapper;
import com.utn.wrappers.CountryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Matias on 18/06/2018.
 */
@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    @Autowired
    CityService cityService;

    @Autowired
    CountryService countryService;

    @PostMapping(value="/country", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveCountry(@RequestBody CountryWrapper request){
        try{
            countryService.save(request.getName(),request.getIsoCode());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/country/{isocode}", produces = "application/json")
    public @ResponseBody ResponseEntity<Country> getLocationByCode(@PathVariable (value = "isocode") String isocode){
        try {
            Country country = countryService.findCountryByIsoCode(isocode);
            if (country!=null) {
                return new ResponseEntity<>(country, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/city")
    public ResponseEntity SaveCity(@RequestBody CityWrapper request){
        try{
            cityService.save(request.getName(),request.getIataCode(),request.getState(),
                    countryService.findCountryByIsoCode(request.getIsoCode()));
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/city/{iatacode}", produces = "application/json")
    public ResponseEntity<City> findCityByIataCode(@PathVariable (value="iatacode") String iatacode ){
        try{
            City city = cityService.findCityByIataCode(iatacode);
            if (city!=null) {
                return new ResponseEntity<>(city,HttpStatus.OK);
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
