package com.utn.services;

import com.utn.models.Prices;
import com.utn.models.CabinsForRoad;
import com.utn.persistence.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class PricesService {

    @Autowired
    private PricesRepository repository;

    public Prices findPricesByCabinsforroadAndYearAndMonth(CabinsForRoad cabinsForRoad, int year, int month){
        return repository.findPricesByCabinsforroadAndYearAndMonth(cabinsForRoad, year, month);
    }

    public List<Prices> findPricesByCabinsforroad(CabinsForRoad cabinsForRoad){
        return repository.findPricesByCabinsforroad(cabinsForRoad);
    }

    public List<Prices> findPricesByCabinsforroadAndYear(CabinsForRoad cabinsForRoad, int year){
        return repositorio.findPricesByCabinsforroadAndYear(cabinsForRoad, year);
    }

    // Guardar
    public void  save(Float price, CabinsForRoad cabinsForRoad, int year, int month){
        Prices prices = new Prices();
        prices.setPrice(price);
        prices.setCabinsforroad(cabinsForRoad);
        prices.setYear(year);
        prices.setMonth(month);
        repositorio.save(prices);
    }

    // Borrar en cascada
    public void delete(Prices prices)
    {
        repositorio.delete(prices);
    }
}
