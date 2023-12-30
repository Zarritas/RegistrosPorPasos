package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Usuario;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<String> partes = new ArrayList<>(List.of(contenido.split("#")));
        String contenidoAux="";
        for (String parte : partes) {
            String[] datos = parte.split(":");
            if (!session.getAttribute("usuario").equals(datos[0])) {
                partes.add(session.getAttribute("usuario") + ":1");
                session.setAttribute((String) session.getAttribute("usuario"), 1);
            }else{
                datos[1] = String.valueOf(Integer.parseInt(datos[1]) + 1);
                session.setAttribute((String) session.getAttribute("usuario"), datos[1]);
                partes.remove(parte);
                partes.add( datos[0] + ":" + datos[1]);
            }
            break;
        }
        for (String parte : partes) {
            contenidoAux += parte + "#";
        }
        return contenidoAux;
    }
}
