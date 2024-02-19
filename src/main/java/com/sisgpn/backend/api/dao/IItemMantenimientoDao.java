package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.ItemMantenimiento;
import com.sisgpn.backend.api.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IItemMantenimientoDao extends JpaRepository<ItemMantenimiento, Long> {

    @Query("from Mantenimiento ")
    public List<Mantenimiento> findAllMantenimientos();
}
