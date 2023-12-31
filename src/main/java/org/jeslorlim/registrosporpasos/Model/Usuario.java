package org.jeslorlim.registrosporpasos.Model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.jeslorlim.registrosporpasos.Validations.clave.ValidarClave;
import org.jeslorlim.registrosporpasos.Validations.Groups.GrupoDatosPersonales;
import org.jeslorlim.registrosporpasos.Validations.Groups.GrupoDatosProfesionales;
import org.jeslorlim.registrosporpasos.Validations.Groups.GrupoDatosUsuario;
import org.jeslorlim.registrosporpasos.Validations.fechanacimiento.Mayor18Anios;
import org.jeslorlim.registrosporpasos.Validations.nacionalidades.AlmenosDos;
import org.jeslorlim.registrosporpasos.Validations.nombre.NotExist;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ValidarClave(groups = GrupoDatosUsuario.class)
public class Usuario {
//--Datos Usuario-------------------------------------------------------------------------
    @NotBlank(groups = GrupoDatosUsuario.class)
    @NotExist(groups = GrupoDatosUsuario.class)
    String nombre;
    @NotBlank(groups = GrupoDatosUsuario.class)
    String clave;
    @NotBlank(groups = GrupoDatosUsuario.class)
    String confirmarClave;
    public void agrergarDatosUsuario(Usuario datosUsuario) {
        this.setNombre(datosUsuario.getNombre());
        this.setClave(datosUsuario.getClave());
        this.setConfirmarClave(datosUsuario.getConfirmarClave());
    }

//--Datos Personales---------------------------------------------------------
    String tratamiento_deseado;
    String apellido;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Mayor18Anios(groups = GrupoDatosPersonales.class)
    @NotNull(groups = GrupoDatosPersonales.class)
    LocalDate fechaNacimiento;
    int edad;
    String genero;
    boolean casado;
    boolean hijos;
    @NotNull(groups = GrupoDatosPersonales.class)
    @AlmenosDos(groups = GrupoDatosPersonales.class)
    ArrayList<String> nacionalidades;
    public void agrergarDatosPersonales(Usuario datosPersonales) {
        this.setTratamiento_deseado(datosPersonales.getTratamiento_deseado());
        this.setApellido(datosPersonales.getApellido());
        this.setFechaNacimiento(datosPersonales.getFechaNacimiento());
        this.setEdad(datosPersonales.getEdad());
        this.setGenero(datosPersonales.getGenero());
        this.setCasado(datosPersonales.isCasado());
        this.setHijos(datosPersonales.isHijos());
        this.setNacionalidades(datosPersonales.getNacionalidades());
    }

//--Datos Profesionales---------------------------------------------------------
    String departamento;
    @Min(value = 1080, groups = GrupoDatosProfesionales.class)
    double salario;
    String comentarios;
    public void agrergarDatosProfesionales(Usuario datosProfesionales) {
        this.setDepartamento(datosProfesionales.getDepartamento());
        this.setSalario(datosProfesionales.getSalario());
        this.setComentarios(datosProfesionales.getComentarios());
    }
}

