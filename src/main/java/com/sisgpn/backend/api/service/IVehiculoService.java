package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.model.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IVehiculoService {

    public List<Vehiculo> findAll();

    public Page<Vehiculo> findAll(Pageable pageable);

    public Vehiculo findById(Long idSubcircuito);

    public Vehiculo save(Vehiculo vehiculo);

    public  void delete(Long idVehiculo);
}
