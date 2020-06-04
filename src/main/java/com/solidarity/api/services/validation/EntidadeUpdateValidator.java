package com.solidarity.api.services.validation;

import com.solidarity.api.dto.EntidadeDTO;
import com.solidarity.api.model.Entidade;
import com.solidarity.api.repositories.EntidadeRepository;
import com.solidarity.api.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EntidadeUpdateValidator implements ConstraintValidator<EntidadeUpdate,  EntidadeDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EntidadeRepository entidadeRepository;


    public void initialize(EntidadeUpdateValidator constraintAnnotation) {
    }

    @Override
    public boolean isValid(EntidadeDTO entidadeDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long uriId = Long.parseLong(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Entidade aux = entidadeRepository.findByEmail(entidadeDTO.getEmail());
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

