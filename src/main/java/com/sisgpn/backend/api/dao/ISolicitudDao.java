package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.SolicitudMantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISolicitudDao extends JpaRepository<SolicitudMantenimiento, Long> {

    @Query("from Vehiculo ")
    public List<Vehiculo> findAllVehiculos();

    @Query("from Persona ")
    public List<Persona> findAllPersonas();
}
