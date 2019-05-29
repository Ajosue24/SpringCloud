package com.vytra.websockets.model;

import lombok.Getter;
import lombok.Setter;

public class Positionated {

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String latitud;

    @Getter
    @Setter
    private String longitud;

    public Positionated(String type, String id, String latitud, String longitud) {
        this.type = type;
        this.id = id;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Positionated() {
    }
}
