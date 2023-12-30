package org.jeslorlim.registrosporpasos.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
//--Datos Usuario-------------------------------------------------------------------------
    String nombre;
    String clave;
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
    LocalDate fechaNacimiento;
    int edad;
    String genero;
    boolean casado;
    boolean hijos;
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
    double salario;
    String comentarios;
    public void agrergarDatosProfesionales(Usuario datosProfesionales) {
        this.setDepartamento(datosProfesionales.getDepartamento());
        this.setSalario(datosProfesionales.getSalario());
        this.setComentarios(datosProfesionales.getComentarios());
    }
}

