package com.solidarity.api.services.validation;

import com.solidarity.api.dto.EntidadeNewDTO;
import com.solidarity.api.model.Entidade;
import com.solidarity.api.repositories.EntidadeRepository;
import com.solidarity.api.resources.exception.FieldMessage;
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

