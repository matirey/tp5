package com.utn.controllers;

import com.utn.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
}
