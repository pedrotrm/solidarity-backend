package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, Long> {


}
