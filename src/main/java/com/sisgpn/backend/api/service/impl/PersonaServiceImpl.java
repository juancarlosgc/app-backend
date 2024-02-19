package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.IPersonaDao;
import com.sisgpn.backend.api.model.Persona;
import com.sisgpn.backend.api.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaDao personaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> findAll(Pageable pageable) {
        return personaDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long idPersona) {
        return personaDao.findById(idPersona).orElse(null);
    }

    @Override
    @Transactional
    public Persona save(Persona persona) {
        return personaDao.save(persona);
    }

    @Override
    @Transactional
    public void delete(Long idPersona) {
        personaDao.deleteById(idPersona);
    }


}
