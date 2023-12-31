package org.jeslorlim.registrosporpasos.Validations.nacionalidades;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = ValidarNacionalidades.class)
@Documented
public @interface AlmenosDos {

    String message() default "Almenos 2 nacionalidades";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
