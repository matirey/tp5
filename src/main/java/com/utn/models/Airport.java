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
 * Created by Matias on 17/05/2018.
 */
@Entity
@Table(name = "airports", uniqueConstraints = {@UniqueConstraint(columnNames = {"iataCode"})})
@Getter
@Setter
@EqualsAndHashCode
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idairport", nullable = false)
    private long id;

    @NotEmpty(message = "Name is required")
    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @NotEmpty(message = "iataCode is required.")
    @Column(name = "iataCode", columnDefinition = "varchar(3)", unique = true, nullable = false)
    private String iataCode;

    @NotEmpty(message = "Latitude is required")
    @Column(name = "lat", columnDefinition = "varchar(10)", nullable = false)
    private String latitude;

    @NotEmpty(message = "Longitude is required")
    @Column(name = "lon", columnDefinition = "varchar(10)", nullable = false)
    private String longitude;

    @OneToMany(mappedBy = "airport")
    private List<Road> roadList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;

}
