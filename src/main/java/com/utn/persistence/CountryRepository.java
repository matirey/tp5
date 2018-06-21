package com.utn.persistence;

import com.utn.models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 1/6/2018.
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long>  {

    Country findCountryByIsoCode(String isocode);

    List<Country> findAll();


}
