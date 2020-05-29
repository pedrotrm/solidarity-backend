package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


}
