package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Subcircuito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISubcircuitoService {
    public Page<Subcircuito> findAll(Pageable pageable);

    public Subcircuito findById(Long idSubcircuito);

    public Subcircuito save(Subcircuito subcircuito);

    public  void delete(Long idSubcircuito);

    public List<Circuito> findAllCircuitos();
}
