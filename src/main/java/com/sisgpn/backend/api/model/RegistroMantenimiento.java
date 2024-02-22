package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Data
@Entity(name="RegistroMantenimiento")
@Table(name="registros")
public class RegistroMantenimiento implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;

    @NotNull
    private LocalTime horaIngreso;

    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud")
    private SolicitudMantenimiento solicitudMantenimiento;


    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mantenimiento")
    private Mantenimiento mantenimiento;







}
