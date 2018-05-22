package com.utn.models;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Matias on 21/05/2018.
 */
@Entity
@Table(name = "states")
public class State {
    private long id;
    private String nombre;
    private String iataCode;
}
