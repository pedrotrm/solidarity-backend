package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {


}
