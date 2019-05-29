package com.vytra.administration.entity.vehicles;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vytra.administration.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

    @Column(name = "identifier", unique = true)
    @NotNull
    @Setter
    @Getter
    private String identifier;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @Column(name = "front_image")
    @Setter
    @Getter
    private String frontImage;

    @Column(name = "back_image")
    @Setter
    @Getter
    private String backImage;

    @Column(name = "lateral_image")
    @Setter
    @Getter
    private String lateralImage;


    @Column(name = "enabled")
    @Setter
    @Getter
    private Boolean enabled = true;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "vehicle_brand_id")
    private VehicleBrand vehicleBrand;



    @OneToMany(mappedBy = "vehicleId", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Getter
    @Setter
    private Set<VehicleFeature> vehicleFeatureList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "fleet_vehicle", joinColumns = {
            @JoinColumn(name = "vehicle_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "fleet_id", referencedColumnName = "id")})
    @Getter
    @Setter
    @OrderBy
    private Set<Fleet> fleetSet = new HashSet<>();

}
