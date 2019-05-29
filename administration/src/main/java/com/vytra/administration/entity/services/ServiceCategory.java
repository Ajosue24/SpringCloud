package com.vytra.administration.entity.services;

import com.vytra.administration.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "service_category")
@AllArgsConstructor
@NoArgsConstructor
public class ServiceCategory extends BaseEntity {

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
    private ServiceCategory serviceCategory;

    protected ServiceCategory(Long id) {
        super(id);
    }
}
