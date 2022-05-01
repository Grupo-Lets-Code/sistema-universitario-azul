package com.sistema.universitario.validator;

import com.sistema.universitario.models.Endereco;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnderecoValidator implements ConstraintValidator<EnderecoConstraint, Endereco> {
    @Override
    public void initialize(EnderecoConstraint endereco) {
    }

    @Override
    public boolean isValid(Endereco endereco, ConstraintValidatorContext constraintValidatorContext) {
        return endereco != null &&
                endereco.getBairro().matches("^[A-Za-zÀ-ÿ0-9 ]+$") &&
                endereco.getNum().matches("\\d+\\w") &&
                endereco.getCep().matches("\\d{5}-\\d{3}|\\d{5}\\d{3}") &&
                endereco.getCidade().matches("[A-Za-zÀ-ÿ ]+");

    }

}
