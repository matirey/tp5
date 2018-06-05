package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idprice")
    private long id;

    @NotEmpty(message = "Price is required.")
    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name="cabinsforroad_id")
    public CabinsForRoad cabinsforroad;

    @NotEmpty(message = "Year is required.")
    @Column(name = "year")
    private int year;

    @NotEmpty(message = "Month is required.")
    @Column(name = "month")
    private int month;
}
