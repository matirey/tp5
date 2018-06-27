package com.utn.services;

import com.utn.Tp5Application;
import com.utn.controllers.LocationsController;
import com.utn.models.Country;
import com.utn.persistence.CountryRepository;
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
public class CountryServiceTest {

    @InjectMocks
    private CountryService countryService;

    @Mock
    private CountryRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // GET BY IATACODE Country
    @Test
    public void findCountryByIsoCode() {
        Country country = new Country(1L, "Argentina", "AR");
        when(repository.findCountryByIsoCode("AR")).thenReturn(country);
        Country response = countryService.findCountryByIsoCode("AR");
        assertEquals(response.getName(), country.getName());
        assertEquals(response.getIsoCode(), country.getIsoCode());
    }

    // post
    @Test
    public void save() {
        Country country = new Country(1L, "Argentina", "AR");
        when(repository.save(country)).thenReturn(country);
        countryService.save("Argentina", "AR");
        assertEquals("Argentina", country.getName());
        assertEquals("AR", country.getIsoCode());
    }

}
