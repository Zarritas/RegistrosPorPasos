package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.HttpSession;
import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.jeslorlim.registrosporpasos.Validations.Groups.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("RegistroPorPasos")
public class ControladorRegistro {
    @Autowired
    ServicioImpl mi_servicio;

//--Datos Usuario-------------------------------------------------------------------------------
    @GetMapping("DatosUsuario")
    public String datosUsuarioGet(HttpSession session,
                                  @ModelAttribute("usuario") Usuario usuario,
                                  BindingResult resultadoVinculadoParametros){
        if (session.getAttribute("ErroresUsuario") != null) {
            List<ObjectError> errores = (List<ObjectError>) session.getAttribute("ErroresUsuario");
            for (ObjectError error : errores) {
                if (error.getCodes()[1].contains("ValidarClave")) {
                    resultadoVinculadoParametros.addError(new FieldError("usuario", "clave", "Las claves no coinciden."));
                }else {
                    resultadoVinculadoParametros.addError(error);
                }
            }
            session.removeAttribute("ErroresUsuario");
        }

        if (session.getAttribute("DatosUsuario") != null)
            usuario.agrergarDatosUsuario((Usuario) session.getAttribute("DatosUsuario"));
        return "Registro/datosusuario";
    }
    @PostMapping("DatosUsuario")
    public String datosUsuarioPost(HttpSession session,
                                   @Validated({GrupoDatosUsuario.class}) @ModelAttribute("usuario") Usuario usuario,
                                   BindingResult resultadoVinculadoParametros){
        if (resultadoVinculadoParametros.hasErrors()) {
            session.setAttribute("ErroresUsuario",resultadoVinculadoParametros.getAllErrors());
            return "redirect:/RegistroPorPasos/DatosUsuario";
        }else {
            session.setAttribute("DatosUsuario", usuario);
            return "redirect:/RegistroPorPasos/DatosPersonales";
        }
    }

//--Datos Personales------------------------------------------------------------------------------
    @GetMapping("DatosPersonales")
    public String datosPersonalesGet(Model modelo,
                                     HttpSession session,
                                     @ModelAttribute("usuario") Usuario usuario,
                                     BindingResult resultadoVinculadoParametros){
        if (session.getAttribute("ErroresPersonales") != null) {
            List<ObjectError> errores = (List<ObjectError>) session.getAttribute("ErroresPersonales");
            for (ObjectError error : errores) {
                resultadoVinculadoParametros.addError(error);
            }
            session.removeAttribute("ErroresPersonales");
        }
        modelo.addAttribute("lista_generos",mi_servicio.devuelveGeneros());
        modelo.addAttribute("lista_nacionalidades",mi_servicio.devuelveNacionalidades());
        modelo.addAttribute("lista_tratamientos",mi_servicio.devuelveTratamientos());
        if (session.getAttribute("DatosPersonales") != null)
            usuario.agrergarDatosPersonales((Usuario) session.getAttribute("DatosPersonales"));
        return "Registro/datospersonales";
    }
    @PostMapping("DatosPersonales")
    public String datosPersonalesPost(HttpSession session,
                                      @Validated({GrupoDatosPersonales.class}) @ModelAttribute("usuario") Usuario usuario,
                                      BindingResult resultadoVinculadoParametros) {
        if (resultadoVinculadoParametros.hasErrors()){
            session.setAttribute("ErroresPersonales", resultadoVinculadoParametros.getAllErrors());
            return "redirect:/RegistroPorPasos/DatosPersonales";
        }else{
            session.setAttribute("DatosPersonales", usuario);
            return "redirect:/RegistroPorPasos/DatosProfesionales";
            }
    }

//--Datos Profesionales---------------------------------------------------------------------------
    @GetMapping("DatosProfesionales")
    public String datosProfesionalesGet(Model modelo,
                                        HttpSession session,
                                        @ModelAttribute("usuario") Usuario usuario,
                                        BindingResult resultadoVinculadoParametros){
        if (session.getAttribute("ErroresProfesionales") != null) {
            List<ObjectError> errores = (List<ObjectError>) session.getAttribute("ErroresProfesionales");
            for (ObjectError error : errores) {
                resultadoVinculadoParametros.addError(error);
            }
            session.removeAttribute("ErroresProfesionales");
        }
        modelo.addAttribute("lista_departamentos",mi_servicio.devuelveDepartamentos());
        if (session.getAttribute("DatosProfesionales") != null)
            usuario.agrergarDatosProfesionales((Usuario) session.getAttribute("DatosProfesionales"));
        return "Registro/datosprofesionales";
    }
    @PostMapping("DatosProfesionales")
    public String datosProfesionalesPost(HttpSession session,
                                         @Validated({GrupoDatosProfesionales.class}) @ModelAttribute("usuario") Usuario usuario,
                                         BindingResult resultadoVinculadoParametros){
        if (resultadoVinculadoParametros.hasErrors()) {
            session.setAttribute("ErroresProfesionales", resultadoVinculadoParametros.getAllErrors());
            return "redirect:/RegistroPorPasos/DatosProfesionales";
        }else {
            session.setAttribute("DatosProfesionales", usuario);
            return "redirect:/RegistroPorPasos/Resumen";
        }
    }

//--Resumen---------------------------------------------------------------------------------------
    @GetMapping("Resumen")
    public String resumen(HttpSession session,
                          @ModelAttribute("usuario") Usuario usuario){
        if (session.isNew()){
            return "Registro/resumen";
        }
        MetodosVarios.agregarUsuarios(session, usuario);
        if (session.getAttribute("DatosPersonales") != null ||
                session.getAttribute("DatosProfesionales") != null ||
                session.getAttribute("DatosUsuario") != null){
            Colecciones.agregarUsuario(usuario);
        }
        return "Registro/resumen";
    }
    @GetMapping("VueltaInicio")
    public String vueltaInicio(HttpSession session){
        session.invalidate();
        return "redirect:/RegistroPorPasos/DatosUsuario";
    }
}
