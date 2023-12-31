package org.jeslorlim.registrosporpasos.Service;

import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Servicio {

    public List<String> devuelveGeneros();
    public List<String> devuelveDepartamentos();
    public List<String> devuelveNacionalidades();
    public List<String> devuelveTratamientos();
    public Map<String,Usuario> devuelveUsuarios();
    public Set<ObjectError> devuelveErrores();
}
