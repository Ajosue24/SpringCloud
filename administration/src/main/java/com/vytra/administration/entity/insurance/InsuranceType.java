package com.vytra.administration.entity.insurance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "insurance_types")
public class InsuranceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    @NotNull
    private String name;

    @Column(name = "description")
    @Getter
    @Setter
    @NotNull
    private String description;

    @Column(name = "duration")
    @Getter
    @Setter
    @NotNull
    private int duration;

    public InsuranceType() {
    }

    public InsuranceType(@NotNull String name, @NotNull String description, @NotNull int duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
    }
}
