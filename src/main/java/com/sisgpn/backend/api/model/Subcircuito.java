package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Subcircuito")
@Table(name="subcircuitos")
public class Subcircuito implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubcircuito;

    @NotBlank
    private String codigoSubcircuito;

    @NotBlank
    private String nombreSubcircuito;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_circuito")
    private Circuito circuito;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subcircuito", cascade = CascadeType.ALL)
    private List<Persona> listaPersonas;



}
