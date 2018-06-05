package com.utn.persistence;

import com.utn.models.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 1/6/2018.
 */
@Repository
public interface StateRepository extends CrudRepository<State, Long>  {

    State findStateById(long id);

    List<State> findStateByCountry(long idcountry);
}