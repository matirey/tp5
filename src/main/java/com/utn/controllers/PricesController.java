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
import com.utn.wrappers.RoadWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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

    @PutMapping(value="/price", consumes = "application/json", produces = "application/json")
    public ResponseEntity SavePrices(@RequestBody PriceWrapper request){
        try{
            pricesService.save(request.getPrice(),
                    cabinsForRoadService.findCabinsForRoadByRoadAndCabin(
                            roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(
                                    request.getOrigin(), request.getDestiny()),
                            cabinService.findByName(request.getCabin())), request.getFromdate(), request.getTodate());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Post -> pricewrapper
     * {
     *     origin : origin_iatacode,
     *     destiny : destiny_iatacode,
     *     cabin : cabinName,
     *     traveldate : YYYY-MM-DD
     * }
     */
    @PostMapping("")
    public @ResponseBody ResponseEntity<Prices> GetPricesByRoadAndYearAndMonth(@RequestBody PriceWrapper request){
        Road road = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(request.getOrigin(),request.getDestiny());
        Cabin cabin = cabinService.findByName(request.getCabin());
        CabinsForRoad cabinsForRoad = cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road,cabin);
        if(road!=null && cabin!=null && cabinsForRoad!=null){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(request.getTraveldate(), formatter);
                Prices prices = pricesService.findByCabinsforroadAndFromdateGreaterThanEqualAndTodateLessThanEqual(cabinsForRoad, date);

                if(prices!=null){
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
