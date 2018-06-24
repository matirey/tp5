package com.utn.persistence;

import com.utn.models.Cabins;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Marcosp on 2/6/2018.
 */
@Repository
public interface CabinsRepository extends CrudRepository<Cabins, Long> {

    List<Cabins> findAll();

    Cabins findByName(String name);
}
