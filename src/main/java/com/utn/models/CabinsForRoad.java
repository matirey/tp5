package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;


/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabinsforroad")
public class CabinsForRoad {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcabinforroad")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cabins_fk")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Cabin cabin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "road_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Road road;
}
