package com.solidarity.api.services.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EntidadeInsertValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)

public @interface EntidadeInsert {
    String message() default "Erro de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}