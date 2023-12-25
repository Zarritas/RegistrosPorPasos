package org.jeslorlim.registrosporpasos.Model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Colecciones {
    @Getter
    private static final List<String> listaGeneros = new ArrayList<>();
    @Getter
    private static final List<String> listaDepartamentos = new ArrayList<>();
    @Getter
    private static final List<String> listaNacionalidades = new ArrayList<>();
    static {{
        listaGeneros.add("Masculino");
        listaGeneros.add("Femenino");
        listaGeneros.add("Otro");

        listaDepartamentos.add("Marketing");
        listaDepartamentos.add("Publicidad");
        listaDepartamentos.add("Informática");
        listaDepartamentos.add("Administración");

        listaNacionalidades.add("Española");
        listaNacionalidades.add("Francesa");
        listaNacionalidades.add("Italiana");
        listaNacionalidades.add("Portuguesa");
    }}
}
