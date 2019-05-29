package com.vytra.authentication.entity.security;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vytra.authentication.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@TypeDef(
        name = "jsonb",
        typeClass = JsonBinaryType.class
)
public class Roles extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name="name",unique=true)
    @NotNull
    @Setter
    @Getter
    private String name;

    @Column(name="description")
    @Setter
    @Getter
    private String description;


    @ManyToMany(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    @JoinTable(name = "PERMISSION_ROLE", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id") })
    @Getter
    @Setter
    @OrderBy
    private Set<Permission> permissionSet = new HashSet<>();

    @Column(name = "features", columnDefinition = "jsonb")
    @Type(type = "jsonb")
    @Getter @Setter
    private String features;




}
