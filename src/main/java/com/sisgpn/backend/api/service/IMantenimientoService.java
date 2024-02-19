package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMantenimientoService {

    public List<Mantenimiento> findAll();

    public Page<Mantenimiento> findAll(Pageable pageable);

    public Mantenimiento findById(Long idMantenimiento);

    public Mantenimiento save(Mantenimiento mantenimiento);

    public  void delete(Long idMantenimiento);
}
