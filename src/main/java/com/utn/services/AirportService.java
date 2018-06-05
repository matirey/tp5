package com.utn.services;

import com.utn.models.Airport;
import com.utn.persistence.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class AirportService {

    @Autowired
    private AirportRepository repositorio;

    public Airport BuscarXID(long id){
        return repositorio.findAirportById(id);
    }

    public List<Airport> BuscarXCity(long id){
        return repositorio.findAirportByCity(id);
    }

    // Guardar
    public void  save(Airport reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(Airport airport)
    {
        repositorio.delete(airport);
    }
}
