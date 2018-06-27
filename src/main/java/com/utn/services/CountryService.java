package com.utn.services;

import com.utn.models.Country;
import com.utn.persistence.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(String name, String isocode){
        Country country = new Country();
        country.setName(name);
        country.setIsoCode(isocode);
        repository.save(country);
    }

    /*public void delete(Country country)
    {
        repository.delete(country);
    }*/
}
