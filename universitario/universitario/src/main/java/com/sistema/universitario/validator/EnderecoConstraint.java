package com.sistema.universitario.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = EnderecoValidator.class)
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)

public @interface EnderecoConstraint {
    String message() default "Endereço inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}