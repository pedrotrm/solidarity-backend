package com.solidarity.solidaritybackend.services.validation;

import com.solidarity.solidaritybackend.dto.EntidadeNewDTO;
import com.solidarity.solidaritybackend.dto.VoluntarioNewDTO;
import com.solidarity.solidaritybackend.model.Entidade;
import com.solidarity.solidaritybackend.model.Voluntario;
import com.solidarity.solidaritybackend.repositories.EntidadeRepository;
import com.solidarity.solidaritybackend.repositories.VoluntarioRepository;
import com.solidarity.solidaritybackend.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class EntidadeInsertValidator implements ConstraintValidator<EntidadeInsert, EntidadeNewDTO> {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Override
    public void initialize(EntidadeInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(EntidadeNewDTO entidadeNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        Entidade aux = entidadeRepository.findByEmail(entidadeNewDTO.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }


}

