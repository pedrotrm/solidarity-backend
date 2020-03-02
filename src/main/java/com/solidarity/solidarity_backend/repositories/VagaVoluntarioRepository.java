package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.VagaVoluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaVoluntarioRepository extends JpaRepository<VagaVoluntario, Long> {


}
