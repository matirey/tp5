package com.utn;

import com.utn.controllers.CabinController;
import com.utn.controllers.CabinsForRoadController;
import com.utn.models.Cabin;
import com.utn.models.CabinsForRoad;
import com.utn.models.Road;
import com.utn.services.CabinService;
import com.utn.services.CabinsForRoadService;
import com.utn.services.CabinsForRoadService;
import com.utn.wrappers.CabinWrapper;
import com.utn.wrappers.CabinsForRoadWrapper;
import com.utn.wrappers.CabinsForRoadWrapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by Marcosp on 26/6/2018.
 */
public class CabinsForRoadControllerTest {

    @InjectMocks
    private CabinsForRoadController controller;

    @Mock
    private CabinsForRoadService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // GET ALL
    @Test
    public void GetAll() {
        List<Cabin> list = new ArrayList<>();
        list.add(new Cabin(1L, "economica"));
        list.add(new Cabin(2L,"ejecutiva"));
        list.add(new Cabin(3L, "bussines"));

        List<CabinWrapper> lista = new ArrayList<>();
        lista.add(new CabinWrapper("economica"));
        lista.add(new CabinWrapper("ejecutiva"));
        lista.add(new CabinWrapper("bussines"));

        when(service.findAll()).thenReturn(list);
        ResponseEntity response = controller.findAll();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(((List<CabinWrapper>) response.getBody()).size(), lista.size());
    }

    // GET ALL EMPTY
    @Test
    public void GetAllEmpty() {
        List<Cabin> list = new ArrayList<>();
        when(service.findAll()).thenReturn(list);
        ResponseEntity response = controller.findAll();
        assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}
