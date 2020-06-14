package com.solidarity.api.repositories;

import com.solidarity.api.model.Vaga;
import com.solidarity.api.model.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {




        @Transactional
        Voluntario findByEmail(String email);

        }


