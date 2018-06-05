package com.utn.persistence;

import com.utn.models.Prices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface PricesRepository extends CrudRepository<Prices, Long>  {

    Prices findPricesById(long id);

    Prices findPricesByCabinsforroadAAndYearAndMonth(long idcabinsforroad, int year, int month);
}
