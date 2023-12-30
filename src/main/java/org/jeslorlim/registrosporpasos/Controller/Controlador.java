package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("RegistroPorPasos")
public class Controlador {
    @Autowired
    ServicioImpl mi_servicio;

//--Datos Usuario-------------------------------------------------------------------------------
    @GetMapping("DatosUsuario")
    public String datosUsuarioGet(@ModelAttribute("usuario") Usuario usuario){
        return "datosusuario";
    }
    @PostMapping("DatosUsuario")
    public String datosUsuarioPost(HttpSession session, @ModelAttribute("usuario") Usuario usuario){
        session.setAttribute("DatosUsuario", usuario);
        return "redirect:/RegistroPorPasos/DataPersonales";
    }

//--Datos Personales------------------------------------------------------------------------------
    @GetMapping("DatosPersonales")
    public String datosPersonalesGet(Model modelo,
                                     @ModelAttribute("usuario") Usuario usuario){
        modelo.addAttribute("lista_generos",mi_servicio.devuelveGeneros());
        modelo.addAttribute("lista_nacionalidades",mi_servicio.devuelveNacionalidades());
        modelo.addAttribute("lista_tratamientos",mi_servicio.devuelveTratamientos());
        return "datospersonales";
    }
    @PostMapping("DatosPersonales")
    public String datosPersonalesPost(HttpSession session,@ModelAttribute("usuario") Usuario usuario){
        session.setAttribute("DatosPersonales", usuario);
        return "redirect:/RegistroPorPasos/DatosProfesionales";
    }

//--Datos Profesionales---------------------------------------------------------------------------
    @GetMapping("DatosProfesionales")
    public String datosProfesionalesGet(Model modelo,
                                        @ModelAttribute("usuario") Usuario usuario){
        modelo.addAttribute("lista_departamentos",mi_servicio.devuelveDepartamentos());
        return "datosprofesionales";
    }
    @PostMapping("DatosProfesionales")
    public String datosProfesionalesPost(HttpSession session,@ModelAttribute("usuario") Usuario usuario){
        session.setAttribute("DatosProfesionales", usuario);
        return "redirect:/RegistroPorPasos/Resumen";
    }

//--Resumen---------------------------------------------------------------------------------------
    @GetMapping("Resumen")
    public String resumen(HttpSession session,
                          @ModelAttribute("usuario") Usuario usuario){
        if (session.isNew()){
            return "resumen";
        }
        if (session.getAttribute("DatosUsuario") != null){
            usuario.agrergarDatosUsuario((Usuario) session.getAttribute("DatosUsuario"));
        }
        if (session.getAttribute("DatosPersonales") != null){
            usuario.agrergarDatosPersonales((Usuario) session.getAttribute("DatosPersonales"));
        }
        if (session.getAttribute("DatosProfesionales") != null){
            usuario.agrergarDatosProfesionales((Usuario) session.getAttribute("DatosProfesionales"));
        }
        if (session.getAttribute("DatosPersonales") != null ||
                session.getAttribute("DatosProfesionales") != null ||
                session.getAttribute("DatosUsuario") != null){
            Colecciones.agregarUsuario(usuario);
        }
        return "resumen";
    }
    @GetMapping("VueltaInicio")
    public String vueltaInicio(HttpSession session){
        session.invalidate();
        return "redirect:/RegistroPorPasos/DatosPersonales";
    }
}
