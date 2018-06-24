package com.utn.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Marcosp on 23/6/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabinsForRoadWrapper {

    @JsonProperty("cabin")
    String cabin;

    @JsonProperty("origin")
    String origin;

    @JsonProperty("destiny")
    String destiny;
}
