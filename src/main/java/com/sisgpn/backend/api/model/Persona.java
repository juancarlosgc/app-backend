package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Date;
@Data
@Entity(name="Persona")
@Table(name="personas")
public class Persona implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idPersona;

    @Pattern(regexp = "\\d{10}")
    @NotBlank
    @Column(unique = true)
    private String cedula;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String nombres;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotBlank
    private String tipoSangre;

    @NotBlank
    private String ciudadNacimiento;

    @NotBlank
    private String telefono;

    @NotNull
    @Enumerated
    private RangoEnum rango;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_subcircuito")
    private Subcircuito subcircuito;

}
