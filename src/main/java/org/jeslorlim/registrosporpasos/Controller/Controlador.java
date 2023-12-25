package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Enumeration;

@Controller
@RequestMapping("RegistroPorPasos")
public class Controlador {
    @Autowired
    ServicioImpl mi_servicio;
//--Datos Personales------------------------------------------------------------------------------
    @GetMapping("DatosPersonales")
    public String datosPersonalesGet(Model modelo,
                                     @ModelAttribute("usuario") Usuario usuario){
        modelo.addAttribute("lista_generos",mi_servicio.devuelveGeneros());
        modelo.addAttribute("lista_nacionalidades",mi_servicio.devuelveNacionalidades());
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
        return "redirect:/RegistroPorPasos/DatosBancarios";
    }
//--Datos Bancarios-------------------------------------------------------------------------------
    @GetMapping("DatosBancarios")
    public String datosBancariosGet(Model modelo,
                                    @ModelAttribute("usuario") Usuario usuario){
        modelo.addAttribute("cuenta","cuenta");
        return "datosbancarios";
    }
    @PostMapping("DatosBancarios")
    public String datosBancariosPost(HttpSession session,@ModelAttribute("usuario") Usuario usuario){
        session.setAttribute("DatosBancarios", usuario);
        return "redirect:/RegistroPorPasos/Resumen";
    }
//--Resumen---------------------------------------------------------------------------------------
    @GetMapping("Resumen")
    public String resumen(HttpSession session,
                          @ModelAttribute("usuario") Usuario usuario){
        if (session.isNew()){
            return "resumen";
        }
        if (session.getAttribute("DatosPersonales") != null){
            usuario.agrergarDatosPersonales((Usuario) session.getAttribute("DatosPersonales"));
        }
        if (session.getAttribute("DatosProfesionales") != null){
            usuario.agrergarDatosProfesionales((Usuario) session.getAttribute("DatosProfesionales"));
        }
        if (session.getAttribute("DatosBancarios") != null){
            usuario.agrergarDatosBancarios((Usuario) session.getAttribute("DatosBancarios"));
        }
        return "resumen";
    }
    @GetMapping("VueltaInicio")
    public String vueltaInicio(HttpSession session){
        session.invalidate();
        return "redirect:/RegistroPorPasos/DatosPersonales";
    }
}
