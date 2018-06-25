package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Matias on 17/05/2018.
 */
@Entity
@Table(name = "airports", uniqueConstraints = {@UniqueConstraint(columnNames = {"iataCode"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idairport", nullable = false)
    private long id;

    @NotEmpty(message = "Name is required")
    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @NotEmpty(message = "iataCode is required.")
    @Column(name = "iataCode", columnDefinition = "varchar(3)", unique = true, nullable = false)
    private String iataCode;

    @Column(name = "lat", columnDefinition = "double(10,2)", nullable = false)
    private double latitude;

    @Column(name = "lon", columnDefinition = "double(10,2)", nullable = false)
    private double longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;

}
