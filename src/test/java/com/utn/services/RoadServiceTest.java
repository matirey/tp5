package com.utn.services;

import com.utn.Tp5Application;
import com.utn.models.Airport;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.models.Road;
import com.utn.persistence.RoadRepository;
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
public class RoadServiceTest {

    @InjectMocks
    private RoadService roadService;

    @Mock
    private RoadRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // findRoadsByOrigin
    @Test
    public void findRoadsByOrigin() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        Airport airport2 = new Airport(2L,"Aeropuerto AEP", "AEP", -30F, -30F, city);

        List<Road> list = new ArrayList<>();
        list.add(new Road(1L, 350F, airport, airport2));
        when(repository.findRoadsByAirportorigin_IataCode("MDQ")).thenReturn(list);
        List<Road> response = roadService.findRoadsByOrigin("MDQ");
        assertEquals(response.size(), list.size());
    }

    // findRoadsByDestiny
    @Test
    public void findRoadsByDestiny() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        Airport airport2 = new Airport(2L,"Aeropuerto AEP", "AEP", -30F, -30F, city);

        List<Road> list = new ArrayList<>();
        list.add(new Road(1L, 350F, airport, airport2));
        when(repository.findRoadsByAirportdestiny_IataCode("AEP")).thenReturn(list);
        List<Road> response = roadService.findRoadsByDestiny("AEP");
        assertEquals(response.size(), list.size());
    }

    // find all
    @Test
    public void findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        Airport airport2 = new Airport(2L,"Aeropuerto AEP", "AEP", -30F, -30F, city);
        Road road = new Road(1L, 350F, airport, airport2);
        when(repository.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ","AEP")).thenReturn(road);
        Road response = roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ", "AEP");
        assertEquals(response.getDistance(), road.getDistance(), 0D);
        assertEquals(response.getAirportorigin(), road.getAirportorigin());
        assertEquals(response.getAirportdestiny(), road.getAirportdestiny());
    }

    // post
    @Test
    public void save() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        Airport airport2 = new Airport(2L,"Aeropuerto AEP", "AEP", -30F, -30F, city);
        Road road = new Road(1L, 350F, airport, airport2);
        when(repository.save(road)).thenReturn(road);
        roadService.save(road);
        assertEquals(350F, road.getDistance(), 0D);
        assertEquals("AEP", road.getAirportdestiny().getIataCode());
        assertEquals("MDQ", road.getAirportorigin().getIataCode());
    }

    // post
    @Test
    public void saveWithParams() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        Airport airport = new Airport(1L,"Aeropuerto MDQ", "MDQ", -30F, -30F, city);
        Airport airport2 = new Airport(2L,"Aeropuerto AEP", "AEP", -30F, -30F, city);
        Road road = new Road(1L, 350F, airport, airport2);
        when(repository.save(road)).thenReturn(road);
        roadService.save(airport,airport2);
        assertEquals(350F, road.getDistance(), 0D);
        assertEquals("AEP", road.getAirportdestiny().getIataCode());
        assertEquals("MDQ", road.getAirportorigin().getIataCode());
    }
}
