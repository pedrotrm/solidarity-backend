package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Formacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormacaoRepository extends JpaRepository<Formacao, Long> {


}
