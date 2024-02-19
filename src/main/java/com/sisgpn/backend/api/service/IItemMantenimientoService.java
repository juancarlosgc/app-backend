package com.sisgpn.backend.api.service;

import com.sisgpn.backend.api.model.ItemMantenimiento;
import com.sisgpn.backend.api.model.Mantenimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IItemMantenimientoService {
    public List<ItemMantenimiento> findAll();



    public Page<ItemMantenimiento> findAll(Pageable pageable);

    public ItemMantenimiento findById(Long idItem);

    public ItemMantenimiento save(ItemMantenimiento itemMantenimiento);

    public  void delete(Long idItem);

    public List<Mantenimiento> findAllMantenimientos();


}
