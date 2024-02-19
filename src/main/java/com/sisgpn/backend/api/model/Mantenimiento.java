package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Mantenimiento")
@Table(name="mantenimientos")
public class Mantenimiento implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMantenimiento;

    @NotBlank
    private String codigoMantenimiento;

    @NotBlank
    private  String nombreMantenimiento;

    @NotNull
    private Double costoMantenimiento;

  /*  @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mantenimiento", cascade = CascadeType.ALL)
    private List<ItemMantenimiento> listaItems;*/
}
