package com.vytra.websockets.model;

import lombok.Getter;
import lombok.Setter;

public class Position {

    @Getter
    @Setter
    private String user;

    @Getter
    @Setter
    private String latitude;

    @Getter
    @Setter
    private String longitude;

}
