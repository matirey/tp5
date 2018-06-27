package com.utn.services;

import com.utn.Tp5Application;
import com.utn.models.Airport;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.persistence.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Marcosp on 27/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class AirportServiceTest {

    @InjectMocks
    private AirportService airportService;

    @Mock
    private AirportRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // findByName
    @Test
    public void findByName() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        when(repository.findAirportByIataCode("MDQ")).thenReturn(airport);
        Airport response = airportService.findByIataCode("MDQ");
        assertEquals(response.getName(), airport.getName());
        assertEquals(response.getIataCode(), airport.getIataCode());
        assertEquals(response.getLatitude(), airport.getLatitude(), 0D);
        assertEquals(response.getLongitude(), airport.getLongitude(), 0D);
        assertEquals(response.getCity(), airport.getCity());
    }

    // find all
    @Test
    public void findAll() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        List<Airport> list = new ArrayList<>();
        list.add(new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city));
        when(repository.findAll()).thenReturn(list);
        List<Airport> response = airportService.findall();
        assertEquals(response.size(), list.size());
    }

    // post
    @Test
    public void save() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        when(repository.save(airport)).thenReturn(airport);
        airportService.save(airport);
        assertEquals("Aeropuerto MDQ", airport.getName());
        assertEquals("MDQ", airport.getIataCode());
        assertEquals(-30F, airport.getLatitude(), 0D);
        assertEquals(-30F, airport.getLongitude(), 0D);
        assertEquals("MDQ", airport.getCity().getIataCode());
    }

    // post
    @Test
    public void saveWithParams() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        when(repository.save(airport)).thenReturn(airport);
        airportService.save("Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        assertEquals("Aeropuerto MDQ", airport.getName());
        assertEquals("MDQ", airport.getIataCode());
        assertEquals(-30F, airport.getLatitude(), 0D);
        assertEquals(-30F, airport.getLongitude(), 0D);
        assertEquals("MDQ", airport.getCity().getIataCode());
    }
}
