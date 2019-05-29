package com.vytra.administration.entity.insurance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "insurers_insurance_types")
public class InsurerInsuranceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurers_id")
    @Getter
    @Setter
    @NotNull
    private Insurer insurer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "insurance_types_id")
    @Getter
    @Setter
    @NotNull
    private InsuranceType insuranceType;

    public InsurerInsuranceType() {
    }

    public InsurerInsuranceType(@NotNull Insurer insurer, @NotNull InsuranceType insuranceType) {
        this.insurer = insurer;
        this.insuranceType = insuranceType;
    }
}
