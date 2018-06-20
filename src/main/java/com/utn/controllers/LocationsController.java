package com.utn.controllers;

import com.utn.models.City;
import com.utn.models.Country;
import com.utn.request.LocationReq;
import com.utn.services.CityService;
import com.utn.services.CountryService;
import com.utn.services.StateService;
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
    StateService stateService;
    @Autowired
    CountryService countryService;

    @PostMapping(value="/country", consumes = "application/json", produces = "application/json")
    public ResponseEntity<LocationReq> SaveCountry(@RequestBody LocationReq request){
        Country country = new Country();
        country.setName(request.getName());
        country.setIsoCode(request.getIsoCode());
        try{
            countryService.save(country);
            return new ResponseEntity<>(request,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{code}")
    public @ResponseBody ResponseEntity<Object> getLocationByCode(@PathVariable (value = "code") String code){
        code=code.replace(" ","");
        try {
            Country country = countryService.findCountryByIsoCode(code);
            if(!country.equals(null)){
                return new ResponseEntity<>(country,HttpStatus.OK);
            }
            else{
                City city= cityService.findCityByIataCode(code);
                if (!city.equals(null)) {
                return new ResponseEntity<>(city,HttpStatus.OK);
                }
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
