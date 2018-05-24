package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "prices")
public class Prices {

    @EmbeddedId
    private PricesId id;

    @NotEmpty(message = "Price is required.")
    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name="vigency_id")
    public Vigency vigency;

    @ManyToOne
    @JoinColumn(name="cabinsforroad_id")
    public CabinsForRoad cabinsforroad;
}
