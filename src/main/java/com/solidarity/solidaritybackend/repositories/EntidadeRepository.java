package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {


}
