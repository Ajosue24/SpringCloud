package com.vytra.administration.entity.parametrics;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @Column(name = "name")
    @NotNull
    @Getter @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    @Setter @Getter
    private Country country;
}
