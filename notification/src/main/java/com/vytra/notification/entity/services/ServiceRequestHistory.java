package com.vytra.notification.entity.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vytra.notification.entity.BaseEntity;
import com.vytra.notification.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "service_request_history")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    @NotNull
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    @Getter
    @Setter
    @NotNull
    private Service serviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    @Getter
    @Setter
    @NotNull
    private User responsibleId;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "status")
    @Getter
    @Setter
    private String status;

    @Column(name = "date_service")
    @Getter
    @Setter
    @CreationTimestamp
    private Date dateService;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    @Getter
    @Setter
    @OrderBy
    private Set<Location> locations = new HashSet<>();





}
