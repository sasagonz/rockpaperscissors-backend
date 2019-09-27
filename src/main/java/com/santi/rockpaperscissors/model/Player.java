package com.santi.rockpaperscissors.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    private String name;
    private Shape value;

}
