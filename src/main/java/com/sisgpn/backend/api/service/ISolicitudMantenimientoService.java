package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.SolicitudMantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISolicitudMantenimientoService {

    public SolicitudMantenimiento save(SolicitudMantenimiento solicitudMantenimiento);

    public Page<SolicitudMantenimiento> findAll(Pageable pageable);

    public SolicitudMantenimiento findById(Long idSolicitud);

    public List<Vehiculo> findAllVehiculos();

    public List<Persona> findAllPersonas();
}
