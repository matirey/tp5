package com.utn.controllers;

import com.utn.Tp5Application;
import com.utn.controllers.CabinController;
import com.utn.controllers.CabinsForRoadController;
import com.utn.models.*;
import com.utn.services.CabinService;
import com.utn.services.CabinsForRoadService;
import com.utn.services.RoadService;
import com.utn.wrappers.CabinsForRoadWrapper;
import com.utn.wrappers.RoadWrapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Marcosp on 26/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class CabinsForRoadControllerTest {

    @InjectMocks
    private CabinsForRoadController controller;

    @Mock
    private CabinsForRoadService service;

    @Mock
    private RoadService roadService;

    @Mock
    private CabinService cabinService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Country country = new Country(1L, "Argentina", "AR");
    private City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
    private City city2 = new City(2L, "Buenos Aires", "BUE", "Buenos Aires", country);
    private Airport airport = new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city);
    private Airport airport2 = new Airport(2L, "Aeroparque Internacional Jorge Newbery", "AEP", -34.5580305, -58.4170088, city2);

    // GET ALL BY ROAD
    @Test
    public void GetAllByRoad() {
        Cabin cabin = new Cabin(1L, "economica");
        Road road = new Road(3L, 323L, airport, airport2);
        List<CabinsForRoad> list = new ArrayList<>();
        list.add(new CabinsForRoad(4L, cabin, road));
        List<CabinsForRoad> list2 = new ArrayList<>();
        list2.add(new CabinsForRoad(4L, cabin, road));
        RoadWrapper wrapper = new RoadWrapper(airport.getIataCode(), airport2.getIataCode());
        when(service.findCabinsForRoadByRoad(road)).thenReturn(list);
        when(roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(wrapper.getOrigin(), wrapper.getDestiny()))
                .thenReturn(road);
        ResponseEntity response = controller.findCabinsForRoadByRoad(wrapper);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list2.size(), list.size());
        assertEquals(list2.get(0).getCabin().getName(), list.get(0).getCabin().getName());
        assertEquals(list2.get(0).getRoad().getAirportdestiny().getIataCode(), list.get(0).getRoad().getAirportdestiny().getIataCode());
    }

    //GET ALL EMPTY
    @Test
    public void GetAllByRoadEmpty() {
        List<CabinsForRoad> list = new ArrayList<>();
        Road road = new Road(3L, 323L, airport, airport2);
        RoadWrapper wrapper = new RoadWrapper(airport.getIataCode(), airport2.getIataCode());
        when(service.findCabinsForRoadByRoad(road)).thenReturn(list);
        ResponseEntity response = controller.findCabinsForRoadByRoad(wrapper);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    public void GetAllByRoadServerError(){
        Cabin cabin = new Cabin(1L, "economica");
        Road road = new Road(3L, 323L, airport, airport2);
        List<CabinsForRoad> list = new ArrayList<>();
        list.add(new CabinsForRoad(4L, cabin, road));
        RoadWrapper wrapper= new RoadWrapper(airport.getIataCode(),airport2.getIataCode());
        when(roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode(wrapper.getOrigin(),wrapper.getDestiny()))
        .thenReturn(road);
        when(service.findCabinsForRoadByRoad(road)).thenThrow(Exception.class);
        ResponseEntity response = controller.findCabinsForRoadByRoad(wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());

    }

    // Put
    @Test
    public void Put() {
        CabinsForRoadWrapper wrapper = new CabinsForRoadWrapper("economica","MDQ","AEP");
        cabinService.save("economica");
        Road road = new Road(1L, 323L, airport, airport2);
        roadService.save(road);
        ResponseEntity response = controller.SaveCabinsForRoad(wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }


    // Put Server Error
    @Test
    public void PutServerError() {
        CabinsForRoadWrapper wrapper = new CabinsForRoadWrapper("economica","MDQ","AEP");
        when(roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ","AEP")).thenThrow(Exception.class);
        ResponseEntity response = controller.SaveCabinsForRoad(wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
