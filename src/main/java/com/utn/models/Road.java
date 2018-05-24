package com.utn.models;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idroad", nullable = false)
    private long id;

    @NotEmpty(message = "Distance is required.")
    @Column(name = "distance", columnDefinition = "int", nullable = false)
    private long distance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportorigin_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport airportorigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airportdestiny_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Airport airportdestiny;
}
