package com.utn.services;

import com.utn.Tp5Application;
import com.utn.models.Cabin;
import com.utn.persistence.CabinsRepository;
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
public class CabinServiceTest {

    @InjectMocks
    private CabinService cabinService;

    @Mock
    private CabinsRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    // findByName Country
    @Test
    public void findByName() {
        Cabin cabin = new Cabin(1L, "economica");
        when(repository.findByName("economica")).thenReturn(cabin);
        Cabin response = cabinService.findByName("economica");
        assertEquals(response.getName(), cabin.getName());
    }

    // find all
    @Test
    public void findAll() {
        List<Cabin> list = new ArrayList<>();
        list.add(new Cabin(1L, "economica"));
        list.add(new Cabin(1L, "bussines"));
        when(repository.findAll()).thenReturn(list);
        List<Cabin> response = cabinService.findAll();
        assertEquals(response.size(), list.size());
    }

    // post
    @Test
    public void save() {
        Cabin cabin = new Cabin(1L, "economica");
        when(repository.save(cabin)).thenReturn(cabin);
        cabinService.save("economica");
        assertEquals("economica", cabin.getName());
    }
}
