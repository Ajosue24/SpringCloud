package com.vytra.administration.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "statuses")
public class Status extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    @NotNull
    private String name;

    @Column(name = "description", columnDefinition = "text")
    @Getter
    @Setter
    //@NotNull
    private String description;

    protected Status(Long id) {
        super(id);
    }
}