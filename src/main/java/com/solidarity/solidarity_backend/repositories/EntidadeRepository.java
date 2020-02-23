package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {


}
