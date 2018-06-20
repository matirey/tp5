package com.utn.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by Marcosp on 20/6/2018.
 */
@Data
public class RoadReq {


    @JsonProperty("origin")
    String origin;

    @JsonProperty("destiny")
    String destiny;
}
