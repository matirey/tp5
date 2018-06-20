package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * Created by Marcosp on 22/5/2018.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "roads")
public class Road {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idroad", nullable = false)
    private long id;

    @NotEmpty(message = "Distance is required.")
    @Column(name = "distance", columnDefinition = "double(10,2)", nullable = false)
    private double distance;

    @OneToMany(mappedBy = "road")
    private List<CabinsForRoad> cabinsforroadList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportorigin_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport airportorigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportdestiny_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport airportdestiny;
}
