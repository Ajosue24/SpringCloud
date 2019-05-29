package com.vytra.administration.entity.parametrics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "document_type")
@AllArgsConstructor
@NoArgsConstructor
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    private String name;
    @Getter @Setter
    private String Description;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull
    @JsonIgnore
    @Getter @Setter
    private Country country;

    public DocumentType(Long id) {
        this.id = id;
    }
}
