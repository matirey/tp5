package com.utn.services;

import com.utn.Tp5Application;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.persistence.CityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Marcosp on 27/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class CityServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // GET BY IATACODE Country
    @Test
    public void findCityByIataCode() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        when(repository.findCityByIataCode("MDQ")).thenReturn(city);
        City response = cityService.findCityByIataCode("MDQ");
        assertEquals(response.getName(), city.getName());
        assertEquals(response.getIataCode(), city.getIataCode());
        assertEquals(response.getState(), city.getState());
        assertEquals(response.getCountry(), city.getCountry());
    }

    // post
    @Test
    public void save() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        when(repository.save(city)).thenReturn(city);
        cityService.save(city);
        assertEquals("Mar del Plata", city.getName());
        assertEquals("Buenos Aires", city.getState());
        assertEquals("Argentina", city.getCountry().getName());
        assertEquals("MDQ", city.getIataCode());
    }

    // post
    @Test
    public void saveWithParams() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        when(repository.save(city)).thenReturn(city);
        cityService.save(city.getName(), city.getIataCode(), city.getState(), city.getCountry());
        assertEquals("Mar del Plata", city.getName());
        assertEquals("Buenos Aires", city.getState());
        assertEquals("Argentina", city.getCountry().getName());
        assertEquals("MDQ", city.getIataCode());
    }
}
