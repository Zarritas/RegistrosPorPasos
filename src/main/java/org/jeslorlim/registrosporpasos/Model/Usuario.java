package org.jeslorlim.registrosporpasos.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    //--Datos Personales
    String nombre;
    String apellidos;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate fechaNacimiento;
    String genero;
    boolean casado;
    boolean hijos;
    ArrayList<String> nacionalidades;
    //--Datos Profesionales
    String departamento;
    double salario;
    String comentarios;
    //--Datos Bancarios
    String cuentacorriente;

    public void agrergarDatosPersonales(Usuario datosPersonales) {
        this.setNombre(datosPersonales.getNombre());
        this.setApellidos(datosPersonales.getApellidos());
        this.setFechaNacimiento(datosPersonales.getFechaNacimiento());
        this.setGenero(datosPersonales.getGenero());
        this.setCasado(datosPersonales.isCasado());
        this.setHijos(datosPersonales.isHijos());
        this.setNacionalidades(datosPersonales.getNacionalidades());
    }

    public void agrergarDatosProfesionales(Usuario datosProfesionales) {
        this.setDepartamento(datosProfesionales.getDepartamento());
        this.setSalario(datosProfesionales.getSalario());
        this.setComentarios(datosProfesionales.getComentarios());
    }

    public void agrergarDatosBancarios(Usuario datosBancarios) {
        this.setCuentacorriente(datosBancarios.getCuentacorriente());
    }
}

