package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Matias on 17/05/2018.
 */
@Entity
@Table(name = "airports")
@Getter
@Setter
@EqualsAndHashCode
public class Airport {

    private long id;
    private String name;
    private String iataCode;
    private City city;
    private double latitude;
    private double longitude;
}
