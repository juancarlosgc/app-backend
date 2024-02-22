package com.sisgpn.backend.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name="Distrito")
@Table(name="distritos")
public class Distrito implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDistrito;

    @NotBlank
    private String codigoDistrito;

    @NotBlank
    private String nombreDistrito;

    @NotBlank
    private String parroquia;

    @NotNull
    @Enumerated
    private ProviciaEnum provincia;

   /* @OneToMany(fetch = FetchType.LAZY, mappedBy = "distrito", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    private List<Circuito> listCircuito;*/

}
