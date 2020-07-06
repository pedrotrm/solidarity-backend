package com.solidarity.api.repositories;

import com.solidarity.api.model.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Long> {

    @Transactional
    Entidade findByEmail(String email);

}
