package com.vytra.administration.entity.vehicles;


import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vytra.administration.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vehicle_type")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class VehicleType extends BaseEntity {

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

    @Column(name = "features", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    @Getter
    @Setter
    private String features;

    @Column(name = "enabled")
    @Setter
    @Getter
    private Boolean enabled = true;

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
