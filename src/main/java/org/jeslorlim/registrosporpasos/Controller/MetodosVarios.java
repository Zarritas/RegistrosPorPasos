package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.springframework.validation.ObjectError;

import java.util.*;

public interface MetodosVarios {

    public static void agregarUsuarios(HttpSession session, Usuario usuario) {
        if (session.getAttribute("DatosUsuario") != null) {
            usuario.agrergarDatosUsuario((Usuario) session.getAttribute("DatosUsuario"));
        }
        if (session.getAttribute("DatosPersonales") != null) {
            usuario.agrergarDatosPersonales((Usuario) session.getAttribute("DatosPersonales"));
        }
        if (session.getAttribute("DatosProfesionales") != null) {
            usuario.agrergarDatosProfesionales((Usuario) session.getAttribute("DatosProfesionales"));
        }
    }
    public static String comprobarCookie(String contenido, HttpSession session) {
        String contenidoAux = "";
        Map<String, Integer> usuarios = new HashMap<>();
        for (String parte : contenido.split("#")) {
            String[] datos = parte.split(":");
            usuarios.put(datos[0], Integer.parseInt(datos[1]));
        }
        if (!usuarios.containsKey((String) session.getAttribute("usuario"))) {
            usuarios.put((String) session.getAttribute("usuario"), 1);
        }else {
            usuarios.put((String) session.getAttribute("usuario"), usuarios.get((String) session.getAttribute("usuario")) + 1);
        }
        for (Map.Entry<String, Integer> usuario : usuarios.entrySet()) {
            contenidoAux += usuario.getKey() + ":" + usuario.getValue() + "#";
            session.setAttribute(usuario.getKey(), usuario.getValue());
        }
        session.setAttribute("usuarios_registrados", usuarios);
        return contenidoAux;
    }
}
