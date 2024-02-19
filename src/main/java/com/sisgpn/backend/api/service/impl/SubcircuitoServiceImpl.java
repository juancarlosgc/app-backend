package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.ICircuitoDao;
import com.sisgpn.backend.api.dao.ISubcircuitoDao;
import com.sisgpn.backend.api.model.Circuito;
import com.sisgpn.backend.api.model.Subcircuito;
import com.sisgpn.backend.api.service.ISubcircuitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubcircuitoServiceImpl implements ISubcircuitoService {

    @Autowired
    private ISubcircuitoDao subcircuitoDao;


    @Override
    @Transactional(readOnly = true)
    public Page<Subcircuito> findAll(Pageable pageable) {
        return subcircuitoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Subcircuito findById(Long idCircuito) {
        return subcircuitoDao.findById(idCircuito).orElse(null);
    }


    @Override
    @Transactional
    public Subcircuito save(Subcircuito subcircuito) {
        return subcircuitoDao.save(subcircuito);
    }

    @Override
    @Transactional
    public void delete(Long idPersona) {
        subcircuitoDao.deleteById(idPersona);
    }
}
