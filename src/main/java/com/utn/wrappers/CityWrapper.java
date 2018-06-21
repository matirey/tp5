package com.utn.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utn.models.City;
import com.utn.models.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Matias on 21/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityWrapper {
    @JsonProperty("name")
    String name;

    @JsonProperty("iatacode")
    String iataCode;

    @JsonProperty("state")
    String state;

    @JsonProperty("isocode")
    String isoCode;
}
