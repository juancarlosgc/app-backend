package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Mantenimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICircuitoService {

    public Page<Circuito> findAll(Pageable pageable);

    public Circuito findById(Long idCircuito);

    public Circuito save(Circuito circuito);

    public  void delete(Long idCircuito);

    public List<Distrito> findAllDistritos();

}
