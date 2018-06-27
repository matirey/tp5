package com.utn.controllers;

import com.utn.Tp5Application;
import com.utn.models.Airport;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.models.Road;
import com.utn.services.AirportService;
import com.utn.services.RoadService;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Marcosp on 26/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class RoadControllerTest {

    @InjectMocks
    private RoadController controller;

    @Mock
    private RoadService service;

    @Mock
    private AirportService airportService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Country country = new Country(1L, "Argentina", "AR");
    private City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
    private City city2 = new City(1L, "Buenos Aires", "BUE", "Buenos Aires", country);
    private Airport airport = new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city);
    private Airport airport2 = new Airport(2L, "Aeroparque Internacional Jorge Newbery", "AEP", -34.5580305, -58.4170088, city2);
    private Airport airport3 = new Airport(3L, "Aeroparque Internacional Ministro Pistarini", "EZE", -35.5580305, -57.4170088, city2);

    // GET ALL BY ORIGIN
    @Test
    public void GetAllByOrigin() {
        List<Road> list = new ArrayList<>();
        list.add(new Road(1L, 323L, airport, airport2));
        list.add(new Road(2L, 323L, airport, airport3));
        List<RoadWrapper> lista = new ArrayList<>();
        lista.add(new RoadWrapper(airport.getIataCode(),airport2.getIataCode()));
        lista.add(new RoadWrapper(airport.getIataCode(),airport3.getIataCode()));
        when(service.findRoadsByOrigin("MDQ")).thenReturn(list);
        ResponseEntity response = controller.GetRoadsByOriginIata("MDQ");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<RoadWrapper>) response.getBody()).size(), lista.size());
    }

    // GET ALL BY ORIGIN EMPTY
    @Test
    public void GetAllByOriginEmpty() {
        List<Road> list = new ArrayList<>();
        when(service.findRoadsByOrigin("MDQ")).thenReturn(list);
        ResponseEntity response = controller.GetRoadsByOriginIata("MDQ");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // GET ALL BY DESTINY
    @Test
    public void GetAllByDestiny() {
        List<Road> list = new ArrayList<>();
        list.add(new Road(1L, 323L, airport, airport2));
        list.add(new Road(2L, 323L, airport, airport3));
        List<RoadWrapper> lista = new ArrayList<>();
        lista.add(new RoadWrapper(airport.getIataCode(),airport2.getIataCode()));
        lista.add(new RoadWrapper(airport.getIataCode(),airport3.getIataCode()));
        when(service.findRoadsByDestiny("AEP")).thenReturn(list);
        ResponseEntity response = controller.GetRoadsByDestinyIata("AEP");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<RoadWrapper>) response.getBody()).size(), lista.size());
    }

    // GET ALL BY DESTINY EMPTY
    @Test
    public void GetAllByDestinyEmpty() {
        List<Road> list = new ArrayList<>();
        when(service.findRoadsByDestiny("AEP")).thenReturn(list);
        ResponseEntity response = controller.GetRoadsByDestinyIata("AEP");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // GET ALL BY ORIGIN AND DESTINY
    @Test
    public void GetAllByOriginAndDestiny() {
        Road road = new Road(1L, 323L, airport, airport2);
        RoadWrapper roadWrapper = new RoadWrapper(airport.getIataCode(),airport2.getIataCode());
        when(service.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ", "AEP")).thenReturn(road);
        ResponseEntity response = controller.GetRoadsByOriginAndDestiny(roadWrapper);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((Road) response.getBody()).getAirportorigin().getIataCode(), roadWrapper.getOrigin());
        assertEquals(((Road) response.getBody()).getAirportdestiny().getIataCode(), roadWrapper.getDestiny());
    }

    // GET ALL BY ORIGIN AND DESTINY EMPTY
    @Test
    public void GetAllByOriginAndDestinyEmpty() {
        Road road = new Road(1L, 323L, airport, airport2);
        RoadWrapper roadWrapper = new RoadWrapper(airport.getIataCode(),airport2.getIataCode());
        when(service.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ", "EZE")).thenReturn(road);
        ResponseEntity response = controller.GetRoadsByOriginAndDestiny(roadWrapper);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // GET ALL BY ORIGIN AND DESTINY Internal Server Error
    @Test
    public void GetAllByOriginAndDestinyServerError() {
        RoadWrapper roadWrapper = new RoadWrapper("MDQ", "AEP");
        when(service.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ", "AEP")).thenThrow(Exception.class);
        ResponseEntity response = controller.GetRoadsByOriginAndDestiny(roadWrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Put
    @Test
    public void Put() {
        RoadWrapper Wrapper = new RoadWrapper("MDQ", "AEP");
        Airport airportorigin = new Airport();
        Airport airportdestiny = new Airport();
        when(airportService.findByIataCode("MDQ")).thenReturn(airportorigin);
        when(airportService.findByIataCode("AEP")).thenReturn(airportdestiny);
        ResponseEntity response = controller.SaveRoad(Wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Put No Content
    @Test
    public void PutNoContent() {
        RoadWrapper Wrapper = new RoadWrapper("MDQ", "AEP");
        when(airportService.findByIataCode("MDQ")).thenReturn(null);
        when(airportService.findByIataCode("AEP")).thenReturn(null);
        ResponseEntity response = controller.SaveRoad(Wrapper);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    // Put Server Error
    @Test
    public void PutServerError() {
        RoadWrapper Wrapper = new RoadWrapper("MDQ", "QEP");
        when(airportService.findByIataCode("MDQ")).thenThrow(Exception.class);
        ResponseEntity response = controller.SaveRoad(Wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}
