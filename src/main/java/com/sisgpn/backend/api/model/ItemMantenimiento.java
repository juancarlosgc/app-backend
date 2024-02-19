package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity(name="ItemMantenimiento")
@Table(name="items")
public class ItemMantenimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;

    private String codigoItem;

    private String nombreItem;

    private String descripcionItem;

    @NotNull
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mantenimiento")
    private Mantenimiento mantenimiento;

}
