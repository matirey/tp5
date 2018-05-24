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
 * Created by Matias on 21/05/2018.
 */
@Entity
@Table(name = "states", uniqueConstraints = {@UniqueConstraint(columnNames = {"iataCode"})})
@Getter
@Setter
@EqualsAndHashCode
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idstate", nullable = false)
    private long id;

    @NotEmpty(message = "Name is required.")
    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @NotEmpty(message = "iataCode is required.")
    @Column(name = "iataCode", columnDefinition = "varchar(3)", unique = true, nullable = false)
    private String iataCode;

    @OneToMany(mappedBy = "state")
    private List<City> citiesList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Country country;
}