package com.utn.controllers;

import com.utn.models.Cabin;
import com.utn.models.CabinsForRoad;
import com.utn.models.Prices;
import com.utn.models.Road;
import com.utn.services.CabinsForRoadService;
import com.utn.services.CabinService;
import com.utn.services.PricesService;
import com.utn.services.RoadService;
import com.utn.wrappers.PriceWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Marcosp on 24/6/2018.
 */
@RestController
@RequestMapping("/api/prices")
public class PricesController {

    @Autowired
    PricesService pricesService;

    @Autowired
    CabinsForRoadService cabinsForRoadService;

    @Autowired
    RoadService roadService;

    @Autowired
    CabinService cabinService;

    @PostMapping(value="/price", consumes = "application/json", produces = "application/json")
    public ResponseEntity SavePrices(@RequestBody PriceWrapper request){
        try{
            pricesService.save(request.getPrice(),
                    cabinsForRoadService.findCabinsForRoadByRoadAndCabin(
                            roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(
                                    request.getOrigin(), request.getDestiny()),
                            cabinService.findByName(request.getCabin())),
                    request.getYear(),
                    request.getMonth()
                    );
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/{origin}/{destiny}/{cabin}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Prices>> GetPricesByRoadAndYear(@PathVariable ("origin") String origin,
                                                                             @PathVariable ("destiny") String destiny,
                                                                             @PathVariable ("cabin") String cabinname){

        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(origin, destiny);
        Cabin cabin = cabinService.findByName(cabinname);
        CabinsForRoad cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road,cabin);
        if(!road.equals(null) && !cabin.equals(null) && !cabinsForRoad.equals(null)){
            List<Prices> prices = pricesService.findPricesByCabinsforroad(cabinsForRoad);
            if(prices.size()>0){
                return new ResponseEntity<>(prices, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value="/{origin}/{destiny}/{cabin}/{year}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<Prices>> GetPricesByRoadAndYear(@PathVariable ("origin") String origin,
                                                                             @PathVariable ("destiny") String destiny,
                                                                             @PathVariable ("cabin") String cabinname,
                                                                             @PathVariable ("year") int year){

        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(origin, destiny);
        Cabin cabin = cabinService.findByName(cabinname);
        CabinsForRoad cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road,cabin);
        if(!road.equals(null) && !cabin.equals(null) && !cabinsForRoad.equals(null)){
            List<Prices> prices = pricesService.findPricesByCabinsforroadAndYear(cabinsForRoad, year);
            if(prices.size()>0){
                return new ResponseEntity<>(prices, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{origin}/{destiny}/{cabin}/{year}/{month}")
    public @ResponseBody ResponseEntity<Prices> GetPricesByRoadAndYearAndMonth(@PathVariable ("origin") String origin,
                                                                               @PathVariable ("destiny") String destiny,
                                                                               @PathVariable ("cabin") String cabinname,
                                                                               @PathVariable ("year") int year,
                                                                               @PathVariable ("month") int month){
        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(origin, destiny);
        Cabin cabin = cabinService.findByName(cabinname);
        CabinsForRoad cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road,cabin);
        if(!road.equals(null) && !cabin.equals(null) && !cabinsForRoad.equals(null)){
            try{
                Prices prices = pricesService.findPricesByCabinsforroadAndYearAndMonth(cabinsForRoad, year, month);

                if(!prices.equals(null)){
                    return new ResponseEntity<>(prices,HttpStatus.OK);
                }
                else{
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            }
            catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
