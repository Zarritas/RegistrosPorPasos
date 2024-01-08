package org.jeslorlim.registrosporpasos.Controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.jeslorlim.registrosporpasos.Validations.Groups.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

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
        if (session.getAttribute("DatosUsuario") != null) {
            usuario.agrergarDatosUsuario((Usuario) session.getAttribute("DatosUsuario"));
        }

        if (!mi_servicio.devuelveErrores().isEmpty()){
            for (ObjectError error : mi_servicio.devuelveErrores().values()) {
                if (error.getCodes() != null && error.getCodes()[1].contains("ValidarClave")) {
                    resultadoVinculadoParametros.addError(new FieldError("usuario", "confirmarClave", "Las claves no coinciden."));
                }else {
                    resultadoVinculadoParametros.addError(error);
                }
            }


            for (ObjectError error : resultadoVinculadoParametros.getAllErrors()) {
                if (error.getCodes() != null && error.getCodes()[1].contains("ValidarClave")) {
                    Colecciones.getListaErrores().remove("confirmarClave");
                }else if (mi_servicio.devuelveErrores().containsKey(((FieldError) error).getField())) {
                    Colecciones.getListaErrores().remove(((FieldError) error).getField());
                }
            }
        }

        return "Registro/datosusuario";
    }
    @PostMapping("DatosUsuario")
    public String datosUsuarioPost(HttpSession session,
                                   @Validated({GrupoDatosUsuario.class}) @ModelAttribute("usuario") Usuario usuario,
                                   BindingResult resultadoVinculadoParametros){
        if (resultadoVinculadoParametros.hasErrors()) {
            for (ObjectError error : resultadoVinculadoParametros.getAllErrors())
                if (error.getCodes() != null && error.getCodes()[1].contains("ValidarClave")) {
                    Colecciones.agregarError("confirmarClave", error);
                }else {
                    Colecciones.agregarError(((FieldError)error).getField(), error);
                }
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
        if (session.getAttribute("DatosPersonales") != null)
            usuario.agrergarDatosPersonales((Usuario) session.getAttribute("DatosPersonales"));

        if (!mi_servicio.devuelveErrores().isEmpty()) {
            for (ObjectError error : mi_servicio.devuelveErrores().values()) {
                if (error.getCodes() != null && error.getCodes()[1].contains("ValidarClave")) {
                    resultadoVinculadoParametros.addError(new FieldError("usuario", "confirmarClave", "Las claves no coinciden."));
                } else {
                    resultadoVinculadoParametros.addError(error);
                }
            }
            for (ObjectError error2 : resultadoVinculadoParametros.getAllErrors()) {
                if (mi_servicio.devuelveErrores().containsKey(((FieldError) error2).getField())) {
                    Colecciones.getListaErrores().remove(((FieldError) error2).getField());
                }
            }
        }

        modelo.addAttribute("lista_generos",mi_servicio.devuelveGeneros());
        modelo.addAttribute("lista_nacionalidades",mi_servicio.devuelveNacionalidades());
        modelo.addAttribute("lista_tratamientos",mi_servicio.devuelveTratamientos());

        return "Registro/datospersonales";
    }
    @PostMapping("DatosPersonales")
    public String datosPersonalesPost(HttpSession session,
                                      @Validated({GrupoDatosPersonales.class}) @ModelAttribute("usuario") Usuario usuario,
                                      BindingResult resultadoVinculadoParametros) {
        if (resultadoVinculadoParametros.hasErrors()){
            for (ObjectError error : resultadoVinculadoParametros.getAllErrors()) {
                Colecciones.agregarError(((FieldError)error).getField(), error);
            }
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
        MetodosVarios.agregarUsuarios(session, usuario);
        if (session.getAttribute("DatosProfesionales") != null)
            usuario.agrergarDatosProfesionales((Usuario) session.getAttribute("DatosProfesionales"));

        if (!mi_servicio.devuelveErrores().isEmpty()) {
            for (ObjectError error : mi_servicio.devuelveErrores().values()) {
                if (error.getCodes() != null && error.getCodes()[1].contains("ValidarClave")) {
                    resultadoVinculadoParametros.addError(new FieldError("usuario", "confirmarClave", "Las claves no coinciden."));
                } else {
                    resultadoVinculadoParametros.addError(error);
                }
            }
            for (ObjectError error2 : resultadoVinculadoParametros.getAllErrors()) {
                if (mi_servicio.devuelveErrores().containsKey(((FieldError) error2).getField())) {
                    Colecciones.getListaErrores().remove(((FieldError) error2).getField());
                }
            }
        }

        modelo.addAttribute("lista_departamentos",mi_servicio.devuelveDepartamentos());

        return "Registro/datosprofesionales";
    }
    @PostMapping("DatosProfesionales")
    public String datosProfesionalesPost(HttpSession session,
                                         @Validated({GrupoDatosProfesionales.class}) @ModelAttribute("usuario") Usuario usuario,
                                         BindingResult resultadoVinculadoParametros){
        if (resultadoVinculadoParametros.hasErrors()) {
            for (ObjectError error : resultadoVinculadoParametros.getAllErrors()) {
                Colecciones.agregarError(((FieldError)error).getField(), error);
            }
            return "redirect:/RegistroPorPasos/DatosProfesionales";
        }else {
            session.setAttribute("DatosProfesionales", usuario);
            return "redirect:/RegistroPorPasos/Resumen";
        }
    }

//--Resumen---------------------------------------------------------------------------------------
    @GetMapping("Resumen")
    public String resumen(HttpSession session,
                          @ModelAttribute("usuario") Usuario usuario,
                          BindingResult resultadoVinculadoParametros){




        if (session.isNew()){
            return "Registro/resumen";
        }
        MetodosVarios.agregarUsuarios(session, usuario);

//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
//        Set<ConstraintViolation<Usuario>> violations =
//                validator.validate(usuario, GrupoDatosPersonales.class, GrupoDatosProfesionales.class,GrupoDatosUsuario.class);
//
//        for (ConstraintViolation<Usuario> violation : violations) {
//            resultadoVinculadoParametros.addError(new ObjectError("usuario", violation.getMessage()));
//        }
        if(usuario.getNombre() == null){
            resultadoVinculadoParametros.addError(new FieldError("usuario", "nombre", "Campo obligatorio"));
        }
        if(usuario.getClave() == null){
            resultadoVinculadoParametros.addError(new FieldError("usuario", "clave", "Campo obligatorio"));
        }
        if(usuario.getFechaNacimiento() == null){
            resultadoVinculadoParametros.addError(new FieldError("usuario", "fechaNacimiento", "Campo obligatorio"));
        }
        if(usuario.getNacionalidades() == null || usuario.getNacionalidades().isEmpty() || usuario.getNacionalidades().size() < 2){
            resultadoVinculadoParametros.addError(new FieldError("usuario", "nacionalidades", "Campo obligatorio"));
        }
        if(usuario.getSalario() == 0.0){
            resultadoVinculadoParametros.addError(new FieldError("usuario", "salario", "Campo obligatorio"));
        }
        for (ObjectError error : resultadoVinculadoParametros.getAllErrors()) {
            Colecciones.agregarError(((FieldError)error).getField(), error);
        }
        return "Registro/resumen";
    }
    @GetMapping("VueltaInicio")
    public String Limpiar(HttpSession session){
        session.invalidate();
        Colecciones.limpiarErrores();
        return "redirect:/RegistroPorPasos/DatosUsuario";
    }
    @GetMapping("NuevoUsuario")
    public String nuevoUsuario(HttpSession session, Usuario usuario){
        MetodosVarios.agregarUsuarios(session, usuario);
        if (session.getAttribute("DatosPersonales") != null &&
                session.getAttribute("DatosProfesionales") != null &&
                session.getAttribute("DatosUsuario") != null){
            Colecciones.agregarUsuario(usuario);
            Colecciones.limpiarErrores();
            session.invalidate();
            return "redirect:/RegistroPorPasos/DatosUsuario";
        }
        return "redirect:/RegistroPorPasos/Resumen";
    }
}
