package com.solidarity.api.repositories;

import com.solidarity.api.model.VagaVoluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaVoluntarioRepository extends JpaRepository<VagaVoluntario, Long> {


}
