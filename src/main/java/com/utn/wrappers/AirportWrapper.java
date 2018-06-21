package com.utn.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Matias on 21/06/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportWrapper {

    @JsonProperty("name")
    String name;

    @JsonProperty("iatacode")
    String iataCode;

    @JsonProperty("latitude")
    double latitude;

    @JsonProperty("longitude")
    double longitude;

    @JsonProperty("citycode")
    String cityCode;
}

