package org.jeslorlim.registrosporpasos.Validations.nombre;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = UsuarioNotExist.class)
@Documented
public @interface NotExist {

    String message() default "El usuario ya existe.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
