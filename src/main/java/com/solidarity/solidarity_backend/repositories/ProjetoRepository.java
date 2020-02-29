package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


}
