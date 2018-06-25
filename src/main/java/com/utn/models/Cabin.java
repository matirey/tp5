package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cabin")
public class Cabin {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcabin")
    private long id;

    @NotEmpty(message = "Name is required.")
    @Column(name = "name", columnDefinition = "varchar(10)", nullable = false)
    private String name;

}
