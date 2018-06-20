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
    private CountryRepository repository;

    public Country findCountryByIsoCode(String isocode){
        return repository.findCountryByIsoCode(isocode);
    }

    // Guardar
    public void  save(Country country){
        repository.save(country);
    }

    // Traer todo
    public List<Country> findAll(){
        return repository.findAll();
    }

    // Borrar en cascada
    public void delete(Country country)
    {
        repository.delete(country);
    }
}
