package com.solidarity.solidaritybackend.services.validation;

import com.solidarity.solidaritybackend.dto.VoluntarioNewDTO;
import com.solidarity.solidaritybackend.model.Voluntario;
import com.solidarity.solidaritybackend.repositories.VoluntarioRepository;
import com.solidarity.solidaritybackend.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class VoluntarioInsertValidator implements ConstraintValidator<VoluntarioInsert, VoluntarioNewDTO> {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Override
    public void initialize(VoluntarioInsert constraintAnnotation) {

    }

    @Override
    public boolean isValid(VoluntarioNewDTO voluntarioNewDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();


        Voluntario aux = voluntarioRepository.findByEmail(voluntarioNewDTO.getEmail());
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

