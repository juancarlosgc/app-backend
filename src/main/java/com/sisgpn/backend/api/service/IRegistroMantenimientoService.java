package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.*;

import java.util.List;

public interface IRegistroMantenimientoService {

    public RegistroMantenimiento save(RegistroMantenimiento registroMantenimiento);

    public List<Mantenimiento> findAllMantenimientos();
}
