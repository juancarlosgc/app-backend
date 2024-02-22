package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IVehiculoDao extends JpaRepository<Vehiculo, Long> {
    @Query("from Subcircuito ")
    public List<Subcircuito> findAllSubcircuitos();
}
