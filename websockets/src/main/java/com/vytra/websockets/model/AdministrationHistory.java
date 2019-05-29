package com.vytra.websockets.model;

import lombok.Getter;
import lombok.Setter;

public class AdministrationHistory {

    @Getter
    @Setter
    private String userId;

    @Getter
    @Setter
    private String history;

    public AdministrationHistory() {
    }

    public AdministrationHistory(String userId, String history) {
        this.userId = userId;
        this.history = history;
    }
}
