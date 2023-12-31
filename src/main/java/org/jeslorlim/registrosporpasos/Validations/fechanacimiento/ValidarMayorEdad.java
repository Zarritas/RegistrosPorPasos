package org.jeslorlim.registrosporpasos.Validations.fechanacimiento;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

class ValidarMayorEdad implements ConstraintValidator<Mayor18Anios, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext context) {
        if (localDate==null)
            return false;
        return Period.between(localDate,LocalDate.now()).getYears()>18;
    }
}

