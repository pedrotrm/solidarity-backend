package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {


}
