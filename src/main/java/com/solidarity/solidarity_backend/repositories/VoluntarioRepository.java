package com.solidarity.solidarity_backend.repositories;

import com.solidarity.solidarity_backend.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

        @Transactional
        Voluntario findByEmail(String email);

        }


