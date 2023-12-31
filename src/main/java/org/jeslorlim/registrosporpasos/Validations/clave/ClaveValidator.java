package org.jeslorlim.registrosporpasos.Validations.clave;

import jakarta.validation.*;
import org.jeslorlim.registrosporpasos.Model.Usuario;

class ClaveValidator implements ConstraintValidator<ValidarClave, Usuario> {
    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        if (usuario.getClave() == null || usuario.getConfirmarClave() == null) {
            return false;
        }
        return usuario.getClave().equals(usuario.getConfirmarClave());
    }
}