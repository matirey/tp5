package com.utn;

import com.utn.controllers.AirportController;
import com.utn.models.Airport;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.services.AirportService;
import com.utn.services.CityService;
import com.utn.wrappers.AirportWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Marcosp on 25/6/2018.
 */
public class AirportControllerTest {

    @Mock
    private CityService cityService;

    @InjectMocks
    private AirportController controller;

    @Mock
    private AirportService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Country country = new Country(1L, "Argentina", "AR");
    private City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
    private City city2 = new City(1L, "Buenos Aires", "BUE", "Buenos Aires", country);

    // GET BY IATACODE
    @Test
    public void GetByIataCode() {
        Airport airport = new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city);
        AirportWrapper wrapper = new AirportWrapper("Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city.getIataCode());

        when(service.findByIataCode("MDQ")).thenReturn(airport);
        ResponseEntity response = controller.findAirportByIataCode("MDQ");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((Airport) response.getBody()).getIataCode(), wrapper.getIataCode());
        assertEquals(((Airport) response.getBody()).getName(), wrapper.getName());
        assertEquals(((Airport) response.getBody()).getLatitude(), wrapper.getLatitude(), 0F);
        assertEquals(((Airport) response.getBody()).getLongitude(), wrapper.getLongitude(), 0F);
        assertEquals(((Airport) response.getBody()).getCity().getIataCode(), wrapper.getCityCode());
    }

    // GET BY IATACODE EMPTY
    @Test
    public void GetByIataCodeEmpty() {
        when(service.findByIataCode("")).thenReturn(null);
        ResponseEntity response = controller.findAirportByIataCode("");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // GET ALL
    @Test
    public void GetAll() {
        List<Airport> list = new ArrayList<>();
        list.add(new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city));
        list.add(new Airport(2L, "Aeroparque Internacional Jorge Newbery", "AEP", -34.5580305, -58.4170088, city2));
        list.add(new Airport(3L, "Aeropuerto Internacional Ministro Pistarini", "EZE", -34.8150044, -58.5348284, city2));

        List<AirportWrapper> lista = new ArrayList<>();
        lista.add(new AirportWrapper("Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city.getIataCode()));
        lista.add(new AirportWrapper("Aeroparque Internacional Jorge Newbery", "AEP", -34.5580305, -58.4170088, city2.getIataCode()));
        lista.add(new AirportWrapper("eropuerto Internacional Ministro Pistarini", "EZE", -34.8150044, -58.5348284, city2.getIataCode()));

        when(service.findall()).thenReturn(list);
        ResponseEntity response = controller.findAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<AirportWrapper>) response.getBody()).size(), lista.size());
    }

    // GET ALL EMPTY
    @Test
    public void GetAllEmpty() {
        List<Airport> list = new ArrayList<>();
        when(service.findall()).thenReturn(list);
        ResponseEntity response = controller.findAll();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // POST
    @Test
    public void Post() {
        AirportWrapper wrapper = new AirportWrapper("Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city.getIataCode());
        Airport airport = new Airport(1L, "Aeropuerto Internacional Astor Piazolla", "MDQ", -37.9332052, -57.5815181, city);
        when(service.findByIataCode(any(String.class))).thenReturn(null);
        when(cityService.findCityByIataCode(any(String.class))).thenReturn(city);
        service.save(airport);
        ResponseEntity response = controller.SaveAirport(wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // POST SERVER ERROR
    @Test
    public void PostInternalServerError() {
        when(cityService.findCityByIataCode(any(String.class))).thenReturn(city);
        try {
            controller.SaveAirport(new AirportWrapper("Aeropuerto Internacional Astor Piazolla", "MDQ",-37.9332052, -57.5815181, city.getIataCode()));
            Assert.fail();
        } catch (Exception e) {
            assertTrue(e.getMessage(),true);
        }
    }

}
