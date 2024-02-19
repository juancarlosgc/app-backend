package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMantenimientoDao extends JpaRepository<Mantenimiento, Long> {
}
