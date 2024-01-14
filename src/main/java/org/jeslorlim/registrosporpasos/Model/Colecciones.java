package org.jeslorlim.registrosporpasos.Model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.ObjectError;

import java.time.LocalDate;
import java.util.*;

@Setter
public class Colecciones {
    @Getter
    private static final List<String> listaGeneros = new ArrayList<>();
    @Getter
    private static final List<String> listaTratamientos = new ArrayList<>();
    @Getter
    private static final List<String> listaDepartamentos = new ArrayList<>();
    @Getter
    private static final List<String> listaNacionalidades = new ArrayList<>();
    @Getter
    private static Map<String,Usuario> Mapa_usuarios = new HashMap<>();
    @Getter
    private static Map<String,ObjectError> listaErrores = new HashMap<>();
    static {{
            listaGeneros.add("Masculino");
            listaGeneros.add("Femenino");
            listaGeneros.add("Otro");

            listaTratamientos.add("Señor");
            listaTratamientos.add("Señora");
            listaTratamientos.add("Señorita");
            listaTratamientos.add("Senorito");
            listaTratamientos.add("Caballero");
            listaTratamientos.add("Dama");

            listaDepartamentos.add("Marketing");
            listaDepartamentos.add("Publicidad");
            listaDepartamentos.add("Informática");
            listaDepartamentos.add("Administración");

            listaNacionalidades.add("Española");
            listaNacionalidades.add("Francesa");
            listaNacionalidades.add("Italiana");
            listaNacionalidades.add("Portuguesa");

            Mapa_usuarios.put("admin",new Usuario("Juan Alberto", "admin","admin", "Señor","García", LocalDate.now(), 0, "Masculino", false, false, new ArrayList<>(), "Marketing", 0.0, ""));
            Mapa_usuarios.put("alfa",new Usuario("Esteban", "alfa","alfa", "Señor","Gutierrez", LocalDate.now(), 0, "Femenino", false, false, new ArrayList<>(), "Administración", 0.0, ""));
            Mapa_usuarios.put("beta",new Usuario("Manolo", "beta","beta", "Señor","Lopez", LocalDate.now(), 0, "Masculino", false, false, new ArrayList<>(), "Publicidad", 0.0, ""));
    }}
    public static void agregarUsuario(Usuario usuario){
        Mapa_usuarios.put(usuario.getNombre(), usuario);
    }
    public static void agregarError(String campo, ObjectError error) {
        listaErrores.put(campo, error);
    }
    public static void limpiarErrores(){
        listaErrores.clear();
    }

}