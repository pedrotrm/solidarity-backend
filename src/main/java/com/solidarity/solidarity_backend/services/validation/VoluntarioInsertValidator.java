package com.solidarity.solidarity_backend.services.validation;

import com.solidarity.solidarity_backend.DTO.VoluntarioNewDTO;
import com.solidarity.solidarity_backend.model.Voluntario;
import com.solidarity.solidarity_backend.repositories.VoluntarioRepository;
import com.solidarity.solidarity_backend.resources.VoluntarioResource;
import com.solidarity.solidarity_backend.resources.exception.FieldMessage;
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

