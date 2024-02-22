package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.ISolicitudDao;
import com.sisgpn.backend.api.dao.ISubcircuitoDao;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.SolicitudMantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.ISolicitudMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolicitudMantenimientoServiceImpl implements ISolicitudMantenimientoService {


    @Autowired
    private ISolicitudDao solicitudDao;

    @Override
    @Transactional(readOnly = true)
    public Page<SolicitudMantenimiento> findAll(Pageable pageable) {
        return solicitudDao.findAll(pageable);
    }

    @Override
    @Transactional
    public SolicitudMantenimiento save(SolicitudMantenimiento solicitudMantenimiento) {
        return solicitudDao.save(solicitudMantenimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public SolicitudMantenimiento findById(Long idSolicitud) {

        return solicitudDao.findById(idSolicitud).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehiculo> findAllVehiculos() {
        return solicitudDao.findAllVehiculos();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAllPersonas() {
        return solicitudDao.findAllPersonas();
    }
}
