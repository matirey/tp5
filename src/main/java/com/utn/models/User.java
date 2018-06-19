package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Marcosp on 18/6/2018.
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @NotEmpty(message = "Name is required.")
    @Column(name = "name", columnDefinition = "varchar(20)", unique = true, nullable = false)
    private String name;

    @NotEmpty(message = "Pass is required.")
    @Column(name = "pass", columnDefinition = "varchar(20)", nullable = false)
    private String pass;
}
