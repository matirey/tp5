package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by Matias on 21/05/2018.
 */
@Entity
@Table(name = "countries", uniqueConstraints = {@UniqueConstraint(columnNames = {"isoCode"})})
@Data
public class Country {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcountry", nullable = false)
    private long id;

    @NotEmpty(message = "Name is required.")
    @Column(name = "name", columnDefinition = "varchar(100)", nullable = false)
    private String name;

    @NotEmpty(message = "isoCode is required.")
    @Column(name = "isoCode", columnDefinition = "varchar(2)", unique = true, nullable = false)
    private String isoCode;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "country")
    private List<State> stateList;
}
