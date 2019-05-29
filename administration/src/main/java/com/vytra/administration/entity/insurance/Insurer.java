package com.vytra.administration.entity.insurance;

import com.vytra.administration.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "insurers")
public class Insurer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "nit")
    @Getter
    @Setter
    @NotNull
    private String nit;

    @Column(name = "bussines_name")
    @Getter
    @Setter
    @NotNull
    private String bussinesName;

    @Column(name = "company_name")
    @Getter
    @Setter
    @NotNull
    private String companyName;


    protected Insurer(Long id) {
        super(id);
    }

    public Insurer() {
    }

    public Insurer(@NotNull String nit, @NotNull String bussinesName, @NotNull String companyName) {
        this.nit = nit;
        this.bussinesName = bussinesName;
        this.companyName = companyName;
    }
}