package com.utn.services;

import com.utn.models.Prices;
import com.utn.persistence.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class PricesService {

    @Autowired
    private PricesRepository repositorio;

    public Prices BuscarXID(long id){
        return repositorio.findPricesById(id);
    }

    public Prices BuscarXCabinsYYearYMonth(long id, int year, int month){
        return repositorio.findPricesByCabinsforroadAndYearAndMonth(id, year, month);
    }

    // Guardar
    public void  save(Prices reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(Prices prices)
    {
        repositorio.delete(prices);
    }
}
