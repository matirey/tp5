package com.utn.controllers;

import com.utn.Tp5Application;
import com.utn.models.City;
import com.utn.models.Country;
import com.utn.services.CityService;
import com.utn.services.CountryService;
import com.utn.wrappers.CityWrapper;
import com.utn.wrappers.CountryWrapper;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Marcosp on 27/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class LocationsControllerTest {

    @InjectMocks
    private LocationsController controller;

    @Mock
    private CityService cityService;

    @Mock
    private CountryService countryService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // Put Country
    @Test
    public void PutCountry() {
        CountryWrapper Wrapper = new CountryWrapper("Argentina","AR");
        ResponseEntity response = controller.SaveCountry(Wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Put city
    @Test
    public void PutCity() {
        CityWrapper Wrapper = new CityWrapper("Mar del Plata", "MDQ","Buenos Aires","AR");
        ResponseEntity response = controller.SaveCity(Wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Put Country Server Error
    @Test
    public void PutCountryServerError() {
        CountryWrapper Wrapper = new CountryWrapper("Argentina","AR");
        when(countryService.findCountryByIsoCode("AR")).thenThrow(Exception.class);
        ResponseEntity response = controller.SaveCountry(Wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // Put City Server Error
    @Test
    public void PutCityServerError() {
        CityWrapper Wrapper = new CityWrapper("Mar del Plata", "MDQ","Buenos Aires","AR");
        when(countryService.findCountryByIsoCode("AR")).thenThrow(Exception.class);
        ResponseEntity response = controller.SaveCity(Wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // GET BY IATACODE Country
    @Test
    public void GetByIataCodeCountry() {
        Country country = new Country(1L, "Argentina", "AR");
        CountryWrapper wrapper = new CountryWrapper("Argentina", "AR");

        when(countryService.findCountryByIsoCode("AR")).thenReturn(country);
        ResponseEntity response = controller.getLocationByCode("AR");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((Country) response.getBody()).getName(), wrapper.getName());
        assertEquals(((Country) response.getBody()).getIsoCode(), wrapper.getIsoCode());
    }

    // GET BY IATACODE City
    @Test
    public void GetByIataCodeCity() {
        Country country = new Country(1L, "Argentina", "AR");
        City city = new City(1L, "Mar del Plata", "MDQ", "Buenos Aires", country);
        CityWrapper wrapper = new CityWrapper("Mar del Plata", "MDQ", "Buenos Aires","AR");

        when(cityService.findCityByIataCode("MDQ")).thenReturn(city);
        ResponseEntity response = controller.findCityByIataCode("MDQ");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((City) response.getBody()).getName(), wrapper.getName());
        assertEquals(((City) response.getBody()).getState(), wrapper.getState());
        assertEquals(((City) response.getBody()).getIataCode(), wrapper.getIataCode());
        assertEquals(((City) response.getBody()).getCountry().getIsoCode(), wrapper.getIsoCode());
    }

    // GET BY IATACODE COUNTRY EMPTY
    @Test
    public void getLocationByCodeEmpty() {
        when(countryService.findCountryByIsoCode("AR")).thenReturn(null);
        ResponseEntity response = controller.getLocationByCode("AR");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    // GET BY IATACODE CITY EMPTY
    @Test
    public void findCityByIataCodeEmpty() {
        when(cityService.findCityByIataCode("MDQ")).thenReturn(null);
        ResponseEntity response = controller.findCityByIataCode("MDQ");
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }
}
