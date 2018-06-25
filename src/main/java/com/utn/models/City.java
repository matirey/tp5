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
 * Created by Matias on 21/05/2018.
 */
@Entity
@Table(name = "cities", uniqueConstraints = {@UniqueConstraint(columnNames = {"iataCode"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcity", nullable = false)
    private long id;

    @NotEmpty(message = "Name is required.")
    @Column(name = "name",  columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @NotEmpty(message = "iataCode is required.")
    @Column(name = "iataCode", columnDefinition = "varchar(3)", unique = true, nullable = false)
    private String iataCode;

    @NotEmpty(message = "State is required.")
    @Column(name = "state", columnDefinition = "varchar(40)", nullable = false)
    private String state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Country country;
}
