package com.vytra.notification.entity.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vytra.notification.entity.BaseEntity;
import com.vytra.notification.entity.security.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "service_request")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "services_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("services_id")
    private Service serviceId;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "application_date")
    @Getter
    @Setter
    @JsonProperty("application_date")
    private Date applicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("responsible_id")
    private User responsibleId;

    @Column(name = "cancel_at")
    @Getter
    @Setter
    @JsonProperty("cancel_at")
    private Date cancelAt;

    @Column(name = "complete_at")
    @Getter
    @Setter
    @JsonProperty("complete_at")
    private Date completeAt;

}