package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVehiculoDao extends JpaRepository<Vehiculo, Long> {
}
