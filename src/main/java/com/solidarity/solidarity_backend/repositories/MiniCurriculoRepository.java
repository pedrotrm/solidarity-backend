package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.MiniCurriculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MiniCurriculoRepository extends JpaRepository<MiniCurriculo, Long> {


}