package com.utn.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Marcosp on 20/6/2018.
 */
@Data
public class AirportReq {

    @JsonProperty("name")
    String name;

    @JsonProperty("iatacode")
    String iataCode;

    @JsonProperty("citycode")
    String cityCode;

    @JsonProperty("latitude")
    Double latitude;

    @JsonProperty("longitude")
    Double longitude;
}
