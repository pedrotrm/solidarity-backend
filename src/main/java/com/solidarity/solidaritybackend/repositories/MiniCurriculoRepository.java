package com.solidarity.solidaritybackend.repositories;

import com.solidarity.solidaritybackend.model.MiniCurriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniCurriculoRepository extends JpaRepository<MiniCurriculo, Long> {


}
