package com.utn.controllers;

import com.utn.Tp5Application;
import com.utn.controllers.CabinController;
import com.utn.controllers.CabinsForRoadController;
import com.utn.models.*;
import com.utn.services.CabinsForRoadService;
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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Country country = new Country(1L, "Argentina", "AR");
    private City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
    private City city2 = new City(2L, "Buenos Aires", "BUE", "Buenos Aires", country);
    private Airport airport = new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city);
    private Airport airport2 = new Airport(2L, "Aeroparque Internacional Jorge Newbery", "AEP", -34.5580305, -58.4170088, city2);

    // GET ALL BY ROAD AND CABIN
    @Test
    public void GetAllByRoadAndCabin() {
        Cabin cabin = new Cabin(1L, "economica");
        Road road = new Road(1L, 323L, airport, airport2);
        CabinsForRoad cabinsForRoad = new CabinsForRoad(1L, cabin, road);
        RoadWrapper wrapper = new RoadWrapper(airport.getIataCode(),airport2.getIataCode());
        CabinsForRoadWrapper cabinsForRoadwrapper = new CabinsForRoadWrapper(cabin.getName(), road.getAirportorigin().getIataCode(),
                road.getAirportdestiny().getIataCode());

        when(service.findCabinsForRoadByRoadAndCabin(road, cabin)).thenReturn(cabinsForRoad);
        ResponseEntity response = controller.GetCabinsForRoadByRoadAndCabin(wrapper, cabin.getName());
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((CabinsForRoad) response.getBody()).getCabin().getName(), cabinsForRoadwrapper.getCabin());
        assertEquals(((CabinsForRoad) response.getBody()).getRoad().getAirportorigin().getIataCode(), cabinsForRoadwrapper.getOrigin());
        assertEquals(((CabinsForRoad) response.getBody()).getRoad().getAirportdestiny().getIataCode(), cabinsForRoadwrapper.getDestiny());
    }

    /*// GET ALL EMPTY
    @Test
    public void GetAllByRoadEmpty() {
        List<CabinsForRoad> list = new ArrayList<>();
        when(service.findCabinsForRoadByRoad(road)).thenReturn(list);
        ResponseEntity response = controller.GetCabinsForRoadByRoad(wrapper);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }*/

}
