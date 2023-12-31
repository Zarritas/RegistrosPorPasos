package org.jeslorlim.registrosporpasos.Validations.nombre;

import jakarta.validation.*;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;

public class UsuarioNotExist implements ConstraintValidator<NotExist, String> {
    private final ServicioImpl mi_servicio = new ServicioImpl();
    @Override
    public boolean isValid(String usuario, ConstraintValidatorContext context) {
        return !mi_servicio.devuelveUsuarios().containsKey(usuario);
    }
}
