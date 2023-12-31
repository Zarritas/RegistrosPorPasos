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
        String[] partes = contenido.split("#");
        String parteAux="";
        String contenidoAux="";
        for (int i = 0; i < partes.length; i++) {
            String[] datos = partes[i].split(":");
            if (session.getAttribute("usuario").equals(datos[0])) {
                datos[1] = String.valueOf(Integer.parseInt(datos[1]) + 1);
                session.setAttribute((String) session.getAttribute("usuario"), datos[1]);
                partes[i] = datos[0] + ":" + datos[1];
            }else {
                parteAux = session.getAttribute("usuario") + ":1"; ;
                session.setAttribute((String) session.getAttribute("usuario"), 1);
            }
        }
        if (parteAux.isEmpty()) {
            for (String parte : partes) {
                contenidoAux += parte + "#";
            }
        }else{
            contenidoAux = contenido + parteAux + "#";
        }
        return contenidoAux;
    }
}
