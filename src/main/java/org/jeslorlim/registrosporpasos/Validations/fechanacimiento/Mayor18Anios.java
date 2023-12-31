package org.jeslorlim.registrosporpasos.Validations.fechanacimiento;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidarMayorEdad.class)
@Documented
public @interface Mayor18Anios {

    String message() default "La edad es menor a 18 años.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
