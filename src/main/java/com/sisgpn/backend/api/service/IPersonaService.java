package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.model.Vehiculo;
import org.aspectj.weaver.patterns.PerObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPersonaService {
    public List<Persona> findAll();
    public Page<Persona> findAll(Pageable pageable);

    public Persona findById(Long idPersona);

    public Persona save(Persona persona);

    public  void delete(Long idPersona);

    public List<Subcircuito> findAllSubcircuitos();

    public List<Vehiculo> findAllVehiculos();
}
