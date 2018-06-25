package com.utn.persistence;

import com.utn.models.Prices;
import com.utn.models.CabinsForRoad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface PricesRepository extends CrudRepository<Prices, Long>  {

    List<Prices> findPricesByCabinsforroad(CabinsForRoad cabinsForRoad);
    Prices findByCabinsforroadAndFromdateLessThanEqualAndTodateGreaterThanEqual(CabinsForRoad cabinsForRoad, LocalDate fromdate,  LocalDate todate);
}
