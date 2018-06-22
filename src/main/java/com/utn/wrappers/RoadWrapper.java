package com.utn.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Matias on 22/06/2018.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoadWrapper {

    @JsonProperty("origin")
    String origin;

    @JsonProperty("destiny")
    String destiny;
}
