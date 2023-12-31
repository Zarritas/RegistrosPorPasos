package org.jeslorlim.registrosporpasos.Service;

import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ServicioImpl implements Servicio{
    @Override
    public List<String> devuelveGeneros() {
        return Colecciones.getListaGeneros();
    }

    @Override
    public List<String> devuelveDepartamentos() {
        return Colecciones.getListaDepartamentos();
    }

    @Override
    public List<String> devuelveNacionalidades() {
        return Colecciones.getListaNacionalidades();
    }

    @Override
    public List<String> devuelveTratamientos() {
        return Colecciones.getListaTratamientos();
    }

    @Override
    public Map<String,Usuario> devuelveUsuarios() {return Colecciones.getMapa_usuarios();}

    @Override
    public Set<ObjectError> devuelveErrores() {
        return Colecciones.getListaErrores();
    }
}
