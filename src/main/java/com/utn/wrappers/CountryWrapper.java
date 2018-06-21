package com.utn.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.models.City;
import com.utn.models.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Matias on 21/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryWrapper {
    @JsonProperty("name")
    String name;

    @JsonProperty("isocode")
    String isoCode;
}
