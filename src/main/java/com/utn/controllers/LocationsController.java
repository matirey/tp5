package com.utn.controllers;

import com.utn.models.Country;
import com.utn.services.CityService;
import com.utn.services.CountryService;
import com.utn.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matias on 18/06/2018.
 */
@RestController
@RequestMapping("/api/locations")
public class LocationsController {

    @Autowired
    CityService cityService;
    @Autowired
    StateService stateService;
    @Autowired
    CountryService countryService;

    @PostMapping("/country")
    public ResponseEntity SaveCountry(@RequestHeader (value="name") String name, @RequestHeader (value="isocode") String isocode){
        Country country = new Country();
        country.setName(name);
        country.setIsoCode(isocode);
        try{
            countryService.save(country);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
