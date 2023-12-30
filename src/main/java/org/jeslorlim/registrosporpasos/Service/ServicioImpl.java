package org.jeslorlim.registrosporpasos.Service;

import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public Map<String,String> devuelveUsuarios() {return Colecciones.getMapa_usuarios();}
}
