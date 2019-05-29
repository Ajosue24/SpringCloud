package com.vytra.administration.entity.vehicles;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_brand")
public class VehicleBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @Column(name = "enable")
    @Getter
    @Setter
    private Boolean enable = true;

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
