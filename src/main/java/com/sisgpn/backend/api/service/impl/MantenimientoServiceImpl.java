package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.IMantenimientoDao;
import com.sisgpn.backend.api.dao.IVehiculoDao;
import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Vehiculo;
import com.sisgpn.backend.api.service.IMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class MantenimientoServiceImpl implements IMantenimientoService {

    @Autowired
    private IMantenimientoDao mantenimientoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> findAll() {
        return (List<Mantenimiento>) mantenimientoDao.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Mantenimiento> findAll(Pageable pageable) {
        return mantenimientoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Mantenimiento findById(Long idMantenimiento) {
        return mantenimientoDao.findById(idMantenimiento).orElse(null);
    }

    @Override
    @Transactional
    public Mantenimiento save(Mantenimiento mantenimiento) {
        return mantenimientoDao.save(mantenimiento);
    }

    @Override
    @Transactional
    public void delete(Long idMantenimiento) {
        mantenimientoDao.deleteById(idMantenimiento);
    }
}
