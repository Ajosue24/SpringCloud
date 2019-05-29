package com.vytra.notification.entity.services;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @Column(name = "id")
    @Getter
    @Setter
    private long id;

    @Column(name = "longitude")
    @Setter
    @Getter
    @NotNull
    private String longitude;

    @Column(name = "latitude")
    @Setter
    @Getter
    @NotNull
    private String latitude;

    @Column(name = "order")
    @Setter
    @Getter
    private Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id")
    @NotNull
    @Getter
    @Setter
    private ServiceRequest serviceRequestId;

}
