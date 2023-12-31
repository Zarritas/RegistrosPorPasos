package org.jeslorlim.registrosporpasos.Validations.clave;

import jakarta.validation.*;
import org.jeslorlim.registrosporpasos.Model.Usuario;

class ClaveValidator implements ConstraintValidator<ValidarClave, Usuario> {
    @Override
    public boolean isValid(Usuario usuario, ConstraintValidatorContext context) {
        return usuario.getClave().equals(usuario.getConfirmarClave());
    }
}