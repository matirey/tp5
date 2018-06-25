package com.utn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.time.LocalDate;
import javax.persistence.*;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
public class Prices {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idprice")
    private long id;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cabinsforroad_fk", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public CabinsForRoad cabinsforroad;

    @Column(name = "fromdate")
    private LocalDate fromdate;

    @Column(name = "todate")
    private LocalDate todate;
}
