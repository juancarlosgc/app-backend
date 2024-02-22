package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.RegistroMantenimiento;
import com.sisgpn.backend.api.model.Subcircuito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRegistroMantenimientoDao extends JpaRepository<RegistroMantenimiento, Long> {

    @Query("from Mantenimiento ")
    public List<Mantenimiento> findAllMantenimientos();
}
