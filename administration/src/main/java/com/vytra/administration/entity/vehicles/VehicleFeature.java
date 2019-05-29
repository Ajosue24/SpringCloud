package com.vytra.administration.entity.vehicles;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_feature")
public class VehicleFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @Column(name = "key")
    @Setter
    @Getter
    private String key;

    @Column(name = "value")
    @Setter
    @Getter
    private String value;


    @Column(name = "enabled")
    @Setter
    @Getter
    private Boolean enabled = true;

    @Getter
    @Setter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleId;

    @Getter
    @Setter
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Getter
    @Setter
    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "deleted_at")
    @Getter
    @Setter
    private Date deletedAt;

}



