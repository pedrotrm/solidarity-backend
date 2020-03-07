package com.solidarity.solidarity_backend.services.validation;

import com.solidarity.solidarity_backend.DTO.VoluntarioDTO;
import com.solidarity.solidarity_backend.model.Voluntario;
import com.solidarity.solidarity_backend.repositories.VoluntarioRepository;
import com.solidarity.solidarity_backend.resources.exception.FieldMessage;
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
        Integer uriId = Integer.parseInt(map.get("id"));

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

