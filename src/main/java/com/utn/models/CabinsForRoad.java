package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcabinforroad")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabins_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Cabins cabin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Road road;
}
