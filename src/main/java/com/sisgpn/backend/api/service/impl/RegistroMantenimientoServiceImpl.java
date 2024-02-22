package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.IRegistroMantenimientoDao;
import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.model.RegistroMantenimiento;
import com.sisgpn.backend.api.service.IRegistroMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegistroMantenimientoServiceImpl implements IRegistroMantenimientoService {

    @Autowired
    IRegistroMantenimientoDao registroMantenimientoDao;

    @Override
    @Transactional
    public RegistroMantenimiento save(RegistroMantenimiento registroMantenimiento) {

        return registroMantenimientoDao.save(registroMantenimiento);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> findAllMantenimientos() {

        return registroMantenimientoDao.findAllMantenimientos();
    }
}
