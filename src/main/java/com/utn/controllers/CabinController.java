package com.utn.controllers;

import com.utn.models.Cabin;
import com.utn.services.CabinService;
import com.utn.wrappers.CabinWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Matias on 22/06/2018.
 */
@RestController
@RequestMapping("/api/cabins")
public class CabinController {

    @Autowired
    CabinService cabinService;

    @PutMapping(value="", consumes = "application/json", produces = "application/json")
    public ResponseEntity SaveCabin(@RequestBody CabinWrapper request){
        try{
            cabinService.save(request.getName());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public @ResponseBody ResponseEntity<List<Cabin>> findAll(){
        List<Cabin> list = cabinService.findAll();
        if (list.size()>0) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
