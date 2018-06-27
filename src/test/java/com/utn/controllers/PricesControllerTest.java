package com.utn.controllers;

import com.utn.Tp5Application;
import com.utn.models.*;
import com.utn.services.CabinService;
import com.utn.services.CabinsForRoadService;
import com.utn.services.PricesService;
import com.utn.services.RoadService;
import com.utn.wrappers.PriceWrapper;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Marcosp on 27/6/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Tp5Application.class)
public class PricesControllerTest {

    @InjectMocks
    private PricesController controller;

    @Mock
    private PricesService service;

    @Mock
    private CabinService cabinService;

    @Mock
    private RoadService roadService;

    @Mock
    private CabinsForRoadService cabinsForRoadService;

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

    // Put Prices
    @Test
    public void PutPrices() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2018-08-01", formatter);
        LocalDate date2 = LocalDate.parse("2018-08-30", formatter);

        PriceWrapper wrapper = new PriceWrapper(500F, date, date2, "2018-08-15",
                                                "economica", "MDQ", "AEP");
        ResponseEntity response = controller.SavePrices(wrapper);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    // Put Country Server Error
    @Test
    public void PutPricesServerError() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2018-08-01", formatter);
        LocalDate date2 = LocalDate.parse("2018-08-30", formatter);

        PriceWrapper wrapper = new PriceWrapper(500F, date, date2, "2018-08-15",
                "economica", "MDQ", "AEP");
        when(cabinService.findByName("economica")).thenThrow(Exception.class);
        ResponseEntity response = controller.SavePrices(wrapper);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    // GET Prices By Road and Year and Month
    @Test
    public void GetPricesByRoadAndYearAndMonth() {
        Cabin cabin = new Cabin(1L,"economica");
        Road road = new Road(1L, 323L, airport, airport2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2018-08-01", formatter);
        LocalDate date2 = LocalDate.parse("2018-08-30", formatter);
        LocalDate traveldate = LocalDate.parse("2018-08-15", formatter);
        CabinsForRoad cabinsForRoad = new CabinsForRoad(1L, cabin, road);

        Prices prices = new Prices(1L, 500F, cabinsForRoad, date, date2);
        PriceWrapper wrapper = new PriceWrapper(500F, date, date2, "2018-08-15",
                "economica", "MDQ", "AEP");

        when(roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ","AEP")).thenReturn(road);
        when(cabinService.findByName("economica")).thenReturn(cabin);
        when(cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road, cabin)).thenReturn(cabinsForRoad);
        when(service.findByCabinsforroadAndFromdateGreaterThanEqualAndTodateLessThanEqual(cabinsForRoad,traveldate)).thenReturn(prices);
        ResponseEntity response = controller.GetPricesByRoadAndYearAndMonth(wrapper);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((Prices) response.getBody()).getPrice(), wrapper.getPrice());
        assertEquals(((Prices) response.getBody()).getFromdate(), wrapper.getFromdate());
        assertEquals(((Prices) response.getBody()).getTodate(), wrapper.getTodate());
        assertEquals(((Prices) response.getBody()).getCabinsforroad().getCabin().getName(), wrapper.getCabin());
        assertEquals(((Prices) response.getBody()).getCabinsforroad().getRoad().getAirportorigin().getIataCode(), wrapper.getOrigin());
        assertEquals(((Prices) response.getBody()).getCabinsforroad().getRoad().getAirportdestiny().getIataCode(), wrapper.getDestiny());
    }

    // GET Prices By Road and Year and MonthY EMPTY
    @Test
    public void GetPricesByRoadAndYearAndMonthEmpty() {
        Cabin cabin = new Cabin(1L,"economica");
        Road road = new Road(1L, 323L, airport, airport2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2018-08-01", formatter);
        LocalDate date2 = LocalDate.parse("2018-08-30", formatter);
        LocalDate traveldate = LocalDate.parse("2018-08-15", formatter);
        CabinsForRoad cabinsForRoad = new CabinsForRoad(1L, cabin, road);

        Prices prices = new Prices(1L, 500F, cabinsForRoad, date, date2);
        PriceWrapper wrapper = new PriceWrapper(500F, date, date2, "2018-08-15",
                "economica", "MDQ", "EZE");

        when(roadService.findRoadByAirportorigin_IataCodeAndAirportdestiny_IataCode("MDQ","AEP")).thenReturn(road);
        when(cabinService.findByName("economica")).thenReturn(cabin);
        when(cabinsForRoadService.findCabinsForRoadByRoadAndCabin(road, cabin)).thenReturn(cabinsForRoad);
        when(service.findByCabinsforroadAndFromdateGreaterThanEqualAndTodateLessThanEqual(cabinsForRoad,traveldate)).thenReturn(null);
        ResponseEntity response = controller.GetPricesByRoadAndYearAndMonth(wrapper);
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
