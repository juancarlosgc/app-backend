package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.Distrito;
import com.sisgpn.backend.api.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDistritoService {


    public Page<Distrito> findAll(Pageable pageable);

    public Distrito findById(Long idDistrito);

    public Distrito save(Distrito distrito);

    public  void delete(Long idDistrito);
}
