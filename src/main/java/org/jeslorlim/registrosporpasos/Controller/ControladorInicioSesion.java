package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("InicioSesion")
public class ControladorInicioSesion {
    @Autowired
    ServicioImpl mi_servicio;
    //--Usuario--------------------------------------------------------------------------------
    @GetMapping("Usuario")
    public String usuarioGet(Model modelo,
                             ArrayList<String> usuarios_registrados,
                             @CookieValue(value = "usuario_cookie", defaultValue = "") String contenidoCookie,
                             BindingResult errores) {
        if (!contenidoCookie.isEmpty()) {
            String[] partes = contenidoCookie.split("#");
            for (String parte : partes) {
                String[] datos = parte.split(":");
                usuarios_registrados.add(datos[0]);
            }
            modelo.addAttribute("usuarios_registrados", usuarios_registrados);
        }
        return "Login/usuario";
    }
    @PostMapping("Usuario")
    public String usuarioPost(HttpSession session,
                              @RequestParam String usuario,
                              BindingResult errores,
                              @RequestParam(value = "usuarios_registrados",required = false) String usuarios_registrados){
        if (usuario.isEmpty())
            usuario = usuarios_registrados;

        if (!mi_servicio.devuelveUsuarios().containsKey(usuario)) {
            errores.addError(errores.getFieldError());
            return "redirect:/InicioSesion/Usuario";
        }
        session.setAttribute("usuario", usuario);
        return "redirect:/InicioSesion/Clave";
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
        modelo.addAttribute("usuario", usuario);

        if (contenidoCookie.isEmpty()){
            contenidoCookie += session.getAttribute("usuario") + ":0#";
            session.setAttribute((String) session.getAttribute("usuario"), 0);
        }

        contenidoCookie = MetodosVarios.comprobarCookie(contenidoCookie, session);

        respuestaHttp.addCookie(new Cookie("usuario_cookie", contenidoCookie));
        modelo.addAttribute("nombre_usuario", session.getAttribute("usuario"));
        modelo.addAttribute("contador", session.getAttribute((String) session.getAttribute("usuario")));
        return "Login/areaPersonal";
    }
}
