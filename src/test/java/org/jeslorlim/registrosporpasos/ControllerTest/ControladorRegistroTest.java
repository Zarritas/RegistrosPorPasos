package org.jeslorlim.registrosporpasos.ControllerTest;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeFactory;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SuppressWarnings("ALL")
@SpringBootTest
@AutoConfigureMockMvc
public class ControladorRegistroTest {
    private static final String CONTROLADOR = "http://localhost:8080/RegistroPorPasos/";

    @Autowired
    private MockMvc mockMvc;
    private Usuario usuario_valido;

    @Test
    public void when_sending_DatosUsuario_without_usuario_then_getting_DatosUsuario() throws Exception {
        String textoSolicitud = CONTROLADOR+"/DatosUsuario";
        String nombreVista = "/RegistroPorPasos/DatosUsuario";

        MockHttpServletRequestBuilder builder = post(textoSolicitud);
        this.mockMvc
                .perform(builder)
                .andExpect(redirectedUrl(nombreVista));
    }
    @Test
    public void when_sending_DatosUsuario_with_usuario_valido_then_getting_DatosPersonales() throws Exception {
        String textoSolicitud = CONTROLADOR+"/DatosUsuario";
        String nombreVista = "/RegistroPorPasos/DatosPersonales";

        usuario_valido.setNombre("jesus");
        usuario_valido.setClave("a");
        usuario_valido.setConfirmarClave("a");
        MockHttpServletRequestBuilder builder = post(textoSolicitud).flashAttr("usuario", usuario_valido);
        this.mockMvc
                .perform(builder)
                .andExpect(redirectedUrl(nombreVista));
    }

    @Test
    public void when_post_DatosUsuario_with_error_password_then_getting_DatosUsuario() throws Exception {
        String textoSolicitud = CONTROLADOR+"/DatosUsuario";
        String nombreVista = "/RegistroPorPasos/DatosUsuario";

        Usuario usuario_valido = new Usuario();
        HttpSession session = new MockHttpSession();
        usuario_valido.setNombre("jesus");
        usuario_valido.setClave("a");
        usuario_valido.setConfirmarClave("b");
        usuario_valido.setFechaNacimiento(LocalDate.parse("2000-01-01"));

        MockHttpServletRequestBuilder builder = post(textoSolicitud).flashAttr("usuario", usuario_valido);
        this.mockMvc
                .perform(builder)
                .andExpect(redirectedUrl(nombreVista));

    }
}
