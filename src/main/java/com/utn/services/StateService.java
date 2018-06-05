package com.utn.services;

import com.utn.models.State;
import com.utn.persistence.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marcosp on 3/6/2018.
 */
@Service
public class StateService {

    @Autowired
    private StateRepository repositorio;

    public State BuscarXID(long id){
        return repositorio.findStateById(id);
    }

    public List<State> BuscarXCountry(long id){
        return repositorio.findStateByCountry(id);
    }

    // Guardar
    public void  save(State reg){
        repositorio.save(reg);
    }

    // Borrar en cascada
    public void delete(State state)
    {
        repositorio.delete(state);
    }
}
