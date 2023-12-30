package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
@RequestMapping("InicioSesion")
public class ControladorInicioSesion {
    @Autowired
    ServicioImpl mi_servicio;
    //--Usuario--------------------------------------------------------------------------------
    @GetMapping("Usuario")
    public String usuarioGet(){
        return "Login/usuario";
    }
    @PostMapping("Usuario")
    public String usuarioPost(HttpSession session,
                              @RequestParam String usuario){
        if (mi_servicio.devuelveUsuarios().containsKey(usuario)) {
            session.setAttribute("usuario", usuario);
            return "redirect:/InicioSesion/Clave";
        }
        return "redirect:/InicioSesion/Usuario";
    }
    //--Contraseña--------------------------------------------------------------------------------
    @GetMapping("Clave")
    public String claveGet(){
        return "Login/clave";
    }
    @PostMapping("Clave")
    public String clavePost(HttpSession session,
                            @RequestParam String clave){
        if (mi_servicio.devuelveUsuarios().get((String) session.getAttribute("usuario")).equals(clave)) {
            session.setAttribute("clave", clave);
            return "redirect:/InicioSesion/AreaPersonal";
        }
        return "redirect:/InicioSesion/Clave";
    }
    //--Area Personal------------------------------------------------------------------------------
    @GetMapping("AreaPersonal")
    public String areaPersonalGet(HttpSession session, @CookieValue(name = "usuarios", defaultValue = "") String contenido) {
        String contenidoCookie = "";
        ArrayList<String> usuarios = new ArrayList<>();
        if (contenido.isEmpty()) {
            int contador = 0;
            contenidoCookie = session.getAttribute("usuario") + "|" + contador;
        } else {
            String[] partes = contenido.split("#");
            usuarios.addAll(Arrays.asList(partes));
        }
        for (String usuario : usuarios) {
            if (usuario.equals(session.getAttribute("usuario"))) {
                int contador = Integer.parseInt(usuarios.get(1)) + 1;
                contenidoCookie = session.getAttribute("usuario") + "|" + contador;
                break;
            }
        }
        Cookie mi_cookie = new Cookie("usuario", contenidoCookie);
        return "Login/areaPersonal";
    }
    @GetMapping("Logout")
    public String logout(HttpSession session){

        return "redirect:/InicioSesion/Usuario";
    }
}
