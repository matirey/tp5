package com.utn.persistence;

import com.utn.models.Cabin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface CabinsRepository extends CrudRepository<Cabin, Long> {

    List<Cabin> findAll();

    Cabin findByName(String name);
}
