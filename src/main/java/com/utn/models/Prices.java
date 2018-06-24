package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idprice")
    private long id;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cabinsforroad_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public CabinsForRoad cabinsforroad;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;
}
