package com.utn.services;

import com.utn.models.User;
import com.utn.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Marcosp on 18/6/2018.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repositorio;

    public User Login(String name, String pass){
        return repositorio.findByNameAndPass(name,pass);
    }
}
