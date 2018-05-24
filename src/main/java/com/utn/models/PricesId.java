package com.utn.models;

import javax.persistence.*;

/**
 * Created by Marcosp on 23/5/2018.
 */
@Embeddable
public class PricesId {

    @Column(name = "vigency_id")
    private long idvigency;

    @Column(name = "cabinsforroad_id")
    private long cabinsforroadid;
}
