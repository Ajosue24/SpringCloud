package com.vytra.administration.entity.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vytra.administration.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "services")
@NoArgsConstructor
@AllArgsConstructor
public class Service extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "description", columnDefinition = "text")
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "service_category_id")
    @JsonIgnore
    private ServiceCategory serviceCategory;


    protected Service(Long id) {
        super(id);
    }
}
