package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.ICircuitoDao;
import com.sisgpn.backend.api.dao.IDistritoDao;
import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.service.ICircuitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CircuitoServiceImpl implements ICircuitoService {

    @Autowired
    private ICircuitoDao circuitoDao;


    @Override
    @Transactional(readOnly = true)
    public Page<Circuito> findAll(Pageable pageable) {
        return circuitoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Circuito findById(Long idCircuito) {
        return circuitoDao.findById(idCircuito).orElse(null);
    }


    @Override
    @Transactional
    public Circuito save(Circuito circuito) {
        return circuitoDao.save(circuito);
    }

    @Override
    @Transactional
    public void delete(Long idPersona) {
        circuitoDao.deleteById(idPersona);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Distrito> findAllDistritos() {
        return circuitoDao.findAllDistritos();
    }
}
