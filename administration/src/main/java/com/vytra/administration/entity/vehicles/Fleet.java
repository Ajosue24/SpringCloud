package com.vytra.administration.entity.vehicles;

import com.vytra.administration.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fleet")
public class Fleet extends BaseEntity {

    @Column(name = "name")
    @Setter
    @Getter
    private String name;

    @Column(name = "description")
    @Setter
    @Getter
    private String description;

    @Column(name = "enabled")
    @Setter
    @Getter
    private Boolean enabled = true;


}
