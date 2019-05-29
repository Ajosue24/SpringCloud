package com.vytra.administration.entity.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vytra.administration.entity.parametrics.CancellationReason;
import com.vytra.administration.entity.security.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_request_cancelled")
public class ServiceRequestCancelled {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    protected Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    @Getter
    @Setter
    @NotNull
    @JsonProperty("responsible_id")
    private User responsibleId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_history_id")
    @Getter
    @Setter
    @NotNull
    private ServiceRequestHistory serviceRequestHistory;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_request_id")
    @Getter
    @Setter
    @NotNull
    private ServiceRequest serviceRequest;


    @Column(name = "cancelled_at", updatable = false)
    @CreationTimestamp
    @Getter
    @Setter
    private Date cancelledAt;


    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cancellation_reason_id")
    private CancellationReason cancellationReasonId;










}
