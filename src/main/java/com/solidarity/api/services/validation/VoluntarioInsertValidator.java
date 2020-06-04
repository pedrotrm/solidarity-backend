package com.solidarity.api.services.validation;

import com.solidarity.api.dto.VoluntarioNewDTO;
import com.solidarity.api.model.Voluntario;
import com.solidarity.api.repositories.VoluntarioRepository;
import com.solidarity.api.resources.exception.FieldMessage;
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

