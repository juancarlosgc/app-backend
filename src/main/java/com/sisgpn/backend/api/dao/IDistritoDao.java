package com.sisgpn.backend.api.dao;

import com.sisgpn.backend.api.model.Distrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDistritoDao extends JpaRepository<Distrito, Long> {
}
