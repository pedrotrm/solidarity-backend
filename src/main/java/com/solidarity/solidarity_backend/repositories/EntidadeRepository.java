package com.solidarity.solidarity_alpha.repositories;

import com.solidarity.solidarity_alpha.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {


}
