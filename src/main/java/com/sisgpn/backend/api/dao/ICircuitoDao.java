package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICircuitoDao extends JpaRepository<Circuito, Long> {

    @Query("from Distrito ")
    public List<Distrito> findAllDistritos();
}
