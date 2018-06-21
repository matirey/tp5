package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.NoArgsConstructor;
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

    @NotEmpty(message = "Latitude is required")
    @Column(name = "lat", columnDefinition = "double(10,2)", nullable = false)
    private double latitude;

    @NotEmpty(message = "Longitude is required")
    @Column(name = "lon", columnDefinition = "double(10,2)", nullable = false)
    private double longitude;

   /* @OneToMany(mappedBy = "airport")
    private List<Road> roadList;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private City city;

}
