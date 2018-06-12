package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "cabin")
public class Cabins {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcabin")
    private long id;

    @NotEmpty(message = "Name is required.")
    @Column(name = "name", columnDefinition = "varchar(10)", nullable = false)
    private String name;

    @OneToMany(mappedBy = "cabin")
    private List<CabinsForRoad> cabinsforroadList;
}
