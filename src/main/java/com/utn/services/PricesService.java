package com.utn.services;

import com.utn.models.Prices;
import com.utn.models.CabinsForRoad;
import com.utn.persistence.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class PricesService {

    @Autowired
    private PricesRepository repositorio;

    public List<Prices> findPricesByCabinsforroad(CabinsForRoad cabinsForRoad){
        return repositorio.findPricesByCabinsforroad(cabinsForRoad);
    }

    public Prices findByCabinsforroadAndFromdateGreaterThanEqualAndTodateLessThanEqual(CabinsForRoad cabinsForRoad, LocalDate traveldate){
        return repositorio.findByCabinsforroadAndFromdateLessThanEqualAndTodateGreaterThanEqual(cabinsForRoad,traveldate, traveldate);
    }

    // Guardar
    public void  save(Float price, CabinsForRoad cabinsForRoad, LocalDate fromdate, LocalDate todate){
        Prices prices = new Prices();
        prices.setPrice(price);
        prices.setCabinsforroad(cabinsForRoad);
        prices.setFromdate(fromdate);
        prices.setTodate(todate);
        repositorio.save(prices);
    }

    // Borrar en cascada
    public void delete(Prices prices)
    {
        repositorio.delete(prices);
    }
}
