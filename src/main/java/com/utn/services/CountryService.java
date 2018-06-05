package com.utn.services;

import com.utn.models.Country;
import com.utn.persistence.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Service
public class CountryService {

    @Autowired
    private CountryRepository repositorio;

    public Country BuscarXID(long id){
        return repositorio.findCountryById(id);
    }

    // Guardar
    public void  save(Country reg){
        repositorio.save(reg);
    }

    // Traer todo
    public List<Country> findAll(){
        return repositorio.findAll();
    }

    // Borrar en cascada
    public void delete(Country country)
    {
        repositorio.delete(country);
    }
}
