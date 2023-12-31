package org.jeslorlim.registrosporpasos.Validations.clave;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ClaveValidator.class)
@Documented
public @interface ValidarClave {

    String message() default "Error en el funcionamiento de la aplicación";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
