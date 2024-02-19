package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Circuito")
@Table(name="circuitos")
public class Circuito implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCircuito;

    @NotBlank
    private String codigoCircuito;

    @NotBlank
    private String nombreCircuito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_distrito")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    private Distrito distrito;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "circuito", cascade = CascadeType.ALL)
    private List<Subcircuito> listSubcircuito;
}
