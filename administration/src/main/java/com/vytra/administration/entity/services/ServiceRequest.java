package com.vytra.administration.entity.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vytra.administration.entity.BaseEntity;
import com.vytra.administration.entity.security.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "service_request")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("user_id")
/*    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})*/
    @JsonIgnore
    private User userId;

    @ManyToOne
    @JoinColumn(name = "services_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("services_id")
    @JsonIgnore
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

    @ManyToOne
    @JoinColumn(name = "responsible_id")
    @Getter
    @Setter
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

    @OneToMany(mappedBy = "serviceRequestId", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private Set<Location> locations;


}