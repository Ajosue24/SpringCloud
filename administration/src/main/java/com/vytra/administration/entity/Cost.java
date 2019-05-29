package com.vytra.administration.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "costs")
public class Cost extends BaseEntity {

    protected Cost(Long id) {
        super(id);
    }

}
