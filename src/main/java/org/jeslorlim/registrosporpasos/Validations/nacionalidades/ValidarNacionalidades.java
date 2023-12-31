package org.jeslorlim.registrosporpasos.Validations.nacionalidades;

import jakarta.validation.*;

import java.util.ArrayList;

public class ValidarNacionalidades implements ConstraintValidator<AlmenosDos, ArrayList<String>> {

    @Override
    public boolean isValid(ArrayList<String> nacionalidad, ConstraintValidatorContext context) {
        if (nacionalidad == null) {
            return false;
        }
        return nacionalidad.size() >= 2;
    }
}
