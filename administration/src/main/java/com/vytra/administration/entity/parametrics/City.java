package com.vytra.administration.entity.parametrics;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "city")
public class City {

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
    @JoinColumn(name = "state_id")
    @JsonBackReference
    @Setter @Getter
    private State state;
}
