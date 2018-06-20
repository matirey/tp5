package com.utn.controllers;

import com.utn.request.RoadReq;
import com.utn.models.Road;
import com.utn.services.RoadService;
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
    RoadService roadService;

    @GetMapping(value="/{iata}", consumes = "application/json", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Road>> GetRoadsByIata(@PathVariable ("iata") String iata ) {
        List<Road> list = roadService.FindByOrigin(iata);
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/road")
    public ResponseEntity SaveRoad(@RequestBody RoadReq request){
        try {
            roadService.save(request.getOrigin(), request.getDestiny());
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
