package com.solidarity.solidarity_alpha.repositories;

import com.solidarity.solidarity_alpha.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {


}
