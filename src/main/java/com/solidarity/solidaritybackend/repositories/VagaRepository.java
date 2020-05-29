package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {


}
