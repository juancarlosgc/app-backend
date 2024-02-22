package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.ISubcircuitoDao;
import com.sisgpn.backend.api.dao.IVehiculoDao;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.IVehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehiculoServiceImpl implements IVehiculoService {
    @Autowired
    private IVehiculoDao vehiculoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAll() {
        return (List<Vehiculo>) vehiculoDao.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Vehiculo> findAll(Pageable pageable) {
        return vehiculoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Vehiculo findById(Long idVehiculo) {
        return vehiculoDao.findById(idVehiculo).orElse(null);
    }


    @Override
    @Transactional
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoDao.save(vehiculo);
    }

    @Override
    @Transactional
    public void delete(Long idVehiculo) {
        vehiculoDao.deleteById(idVehiculo);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subcircuito> findAllSubcircuitos() {
        return vehiculoDao.findAllSubcircuitos();
    }
}
