package com.vytra.notification.entity.security;


import com.vytra.notification.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity implements Serializable {

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
}
