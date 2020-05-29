package com.solidarity.solidaritybackend.services.validation;

import com.solidarity.solidaritybackend.dto.VoluntarioDTO;
import com.solidarity.solidaritybackend.model.Voluntario;
import com.solidarity.solidaritybackend.repositories.VoluntarioRepository;
import com.solidarity.solidaritybackend.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class VoluntarioUpdateValidator implements ConstraintValidator<VoluntarioUpdate, VoluntarioDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Override
    public void initialize(VoluntarioUpdate constraintAnnotation) {

    }

    @Override
    public boolean isValid(VoluntarioDTO voluntarioDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Voluntario aux = voluntarioRepository.findByEmail(voluntarioDTO.getEmail());
        if (aux != null && !aux.getId().equals(uriId)){
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

