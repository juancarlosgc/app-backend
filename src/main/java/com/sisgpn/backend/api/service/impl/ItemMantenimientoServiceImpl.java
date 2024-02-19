package com.sisgpn.backend.api.service.impl;

import com.sisgpn.backend.api.dao.IItemMantenimientoDao;
import com.sisgpn.backend.api.model.ItemMantenimiento;
import com.sisgpn.backend.api.model.Mantenimiento;
import com.sisgpn.backend.api.service.IItemMantenimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemMantenimientoServiceImpl implements IItemMantenimientoService {
    @Autowired
    private IItemMantenimientoDao itemMantenimientoDao;

    @Override
    @Transactional(readOnly = true)
    public List<ItemMantenimiento> findAll() {
        return (List<ItemMantenimiento>) itemMantenimientoDao.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Page<ItemMantenimiento> findAll(Pageable pageable) {
        return itemMantenimientoDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public ItemMantenimiento findById(Long idItem) {
        return itemMantenimientoDao.findById(idItem).orElse(null);
    }

    @Override
    @Transactional
    public ItemMantenimiento save(ItemMantenimiento itemMantenimiento) {
        return itemMantenimientoDao.save(itemMantenimiento);
    }

    @Override
    @Transactional
    public void delete(Long idItem) {
        itemMantenimientoDao.deleteById(idItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Mantenimiento> findAllMantenimientos() {
        return itemMantenimientoDao.findAllMantenimientos();
    }
}
