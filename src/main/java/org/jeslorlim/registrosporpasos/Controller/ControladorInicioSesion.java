package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if (mi_servicio.devuelveUsuarios().get((String) session.getAttribute("usuario")).getClave().equals(clave)) {
            session.setAttribute("clave", clave);
            return "redirect:/InicioSesion/AreaPersonal";
        }
        return "redirect:/InicioSesion/Clave";
    }
    //--Area Personal------------------------------------------------------------------------------
    @GetMapping("AreaPersonal")
    public String areaPersonalGet(HttpSession session,
                                  Model modelo,
                                  @CookieValue(name = "usuario_cookie", defaultValue = "") String contenidoCookie,
                                  HttpServletResponse respuestaHttp){
        Usuario usuario = mi_servicio.devuelveUsuarios().get((String) session.getAttribute("usuario"));
        if (contenidoCookie.isEmpty()){
            contenidoCookie += session.getAttribute("usuario") + ":1#";
            session.setAttribute((String) session.getAttribute("usuario"), 1);
        }else {
            contenidoCookie = MetodosVarios.comprobarCookie(contenidoCookie, session);
        }
        respuestaHttp.addCookie(new Cookie("usuario_cookie", contenidoCookie));
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("nombre_usuario", session.getAttribute("usuario"));
        modelo.addAttribute("contador", session.getAttribute((String) session.getAttribute("usuario")));
        return "Login/areaPersonal";
    }
}
