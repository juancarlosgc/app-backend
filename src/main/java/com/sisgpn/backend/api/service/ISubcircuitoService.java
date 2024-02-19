package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Subcircuito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISubcircuitoService {
    public Page<Subcircuito> findAll(Pageable pageable);

    public Subcircuito findById(Long idSubcircuito);

    public Subcircuito save(Subcircuito subcircuito);

    public  void delete(Long idSubcircuito);
}
