package org.jeslorlim.registrosporpasos.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public class Colecciones {
    @Getter
    private static final List<String> listaGeneros = new ArrayList<>();
    @Getter
    private static final List<String> listaDepartamentos = new ArrayList<>();
    @Getter
    private static final List<String> listaNacionalidades = new ArrayList<>();
    @Getter
    private static List<Usuario> lista_usuarios = new ArrayList<>();
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
    public static void agregarUsuario(Usuario usuario){
        lista_usuarios.add(usuario);
    }
}
