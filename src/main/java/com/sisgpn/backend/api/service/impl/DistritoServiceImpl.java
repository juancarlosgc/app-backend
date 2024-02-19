package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.IDistritoDao;
import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.service.IDistritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DistritoServiceImpl implements IDistritoService {

    @Autowired
    private IDistritoDao distritoDao;


    @Override
    @Transactional(readOnly = true)
    public Page<Distrito> findAll(Pageable pageable) {
        return distritoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Distrito findById(Long idDistrito) {
        return distritoDao.findById(idDistrito).orElse(null);
    }

    @Override
    @Transactional
    public Distrito save(Distrito distrito) {
        return distritoDao.save(distrito);
    }

    @Override
    @Transactional
    public void delete(Long idPersona) {
        distritoDao.deleteById(idPersona);
    }
}
