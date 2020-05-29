package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {


}
