package com.vytra.authentication.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Getter @Setter
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Getter @Setter
    private Date deletedAt;

    @Column(name = "enabled")
    @Getter @Setter
    private Boolean enabled = true;




}
