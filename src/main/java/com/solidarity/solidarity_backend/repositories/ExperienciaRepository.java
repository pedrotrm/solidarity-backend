package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Experiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {


}
