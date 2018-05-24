package com.utn.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "vigency")
public class Vigency {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idvigency")
    private long id;

    @NotEmpty(message = "FromDate is required.")
    @Column(name = "fromdate")
    private Date fromdate;

    @NotEmpty(message = "ToDate is required.")
    @Column(name = "todate")
    private Date todate;
}
