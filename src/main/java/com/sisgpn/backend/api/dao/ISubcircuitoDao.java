package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Subcircuito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ISubcircuitoDao extends JpaRepository<Subcircuito, Long> {

    @Query("from Circuito ")
    public List<Circuito> findAllCircuitos();
}
