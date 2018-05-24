package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "cabinsforroad")
public class CabinsForRoad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcabinforroad")
    private long id;

    @ManyToOne
    @JoinColumn(name="road_id")
    public Road road;

    @ManyToOne
    @JoinColumn(name="cabins_id")
    public Cabins cabin;
}
