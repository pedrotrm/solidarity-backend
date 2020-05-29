package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {


}
