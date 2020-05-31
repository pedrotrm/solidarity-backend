package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Entidade;
import com.solidarity.solidaritybackend.model.Estado;
import com.solidarity.solidaritybackend.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {


}


