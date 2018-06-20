package com.utn.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * Created by Matias on 19/06/2018.
 */
@Data
public class LocationReq {

    @JsonProperty("name")
    String name;

    @JsonProperty("iatacode")
    String iataCode;

    @JsonProperty("isocode")
    String isoCode;
}
