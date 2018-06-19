package com.utn.persistence;

import org.springframework.stereotype.Repository;
import com.utn.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Marcosp on 18/6/2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User,String>{

    User findByNameAndPass(String name, String pass);
}
