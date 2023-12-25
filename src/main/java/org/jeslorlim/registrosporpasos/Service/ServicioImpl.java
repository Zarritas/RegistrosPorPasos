package org.jeslorlim.registrosporpasos.Service;

import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
