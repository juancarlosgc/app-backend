package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Vehiculo")
@Table(name="vehiculos")
public class Vehiculo implements Serializable {
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @NotBlank
    private String placa;

    @NotBlank
    private String chasis;

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    @NotBlank
    private String motor;

    @NotNull
    private Double kilometraje;

    @NotNull
    private Double cilindraje;

    @NotBlank
    private String capacidadCarga;

    @NotNull
    private Integer cantidadPasajeros;

    @NotNull
    @Enumerated
    private TipoVehiculoEnum tipoVehiculo;


    private String observaciones;


/*
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehiculo", cascade = CascadeType.ALL)
    private List<Persona> listaPersonas;*/


    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcircuito")
    private Subcircuito subcircuito;



}
