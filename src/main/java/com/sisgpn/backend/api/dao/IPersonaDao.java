package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPersonaDao extends JpaRepository<Persona, Long> {
}
