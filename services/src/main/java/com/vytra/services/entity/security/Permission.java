package com.vytra.services.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @Column(name="name",unique=true)
    @NotNull
    @Getter
    @Setter
    private String name;

    @Column(name="descripcion")
    @Getter @Setter
    private String description;

    @Column(name="url",unique=true)
    @NotNull
    @Getter @Setter
    private String url;


    @Column(name="method_type")
    @NotNull
    @Getter @Setter
    private String methodType;
}
