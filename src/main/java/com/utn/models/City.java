package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Matias on 21/05/2018.
 */
@Entity
@Table(name = "cities")
@Getter
@Setter
@EqualsAndHashCode
public class City {
    private long id;
    private String name;
    private String iataCode;
}
