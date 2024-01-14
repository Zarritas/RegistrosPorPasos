package org.jeslorlim.registrosporpasos.ControllerTest;

import org.jeslorlim.registrosporpasos.Model.Colecciones;
import org.jeslorlim.registrosporpasos.Model.Usuario;
import org.jeslorlim.registrosporpasos.Service.ServicioImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SuppressWarnings("ALL")
@SpringBootTest
@AutoConfigureMockMvc
public class ControladorInicioSesionTest {
    private static final String CONTROLADOR = "http://localhost:8080/InicioSesion/";

    @Autowired
    private MockMvc mockMvc;
    private static Usuario usuario_valido = new Usuario();
    static {{
    usuario_valido.setNombre("jesus");
    usuario_valido.setClave("a");

    Colecciones.agregarUsuario(usuario_valido);
    }}
    ServicioImpl mi_servicio;

    @Test
    public void when_typing_on_Usuario_inexist_user_then_get_Usuario() throws Exception {
        String textoSolicitud = CONTROLADOR+"/Usuario";
        String nombreVista = "/InicioSesion/Usuario";

        MockHttpServletRequestBuilder builder = post(textoSolicitud).param("usuario", "inexistente");
        this.mockMvc
                .perform(builder)
                .andExpect(redirectedUrl(nombreVista));
    }
    @Test
    public void when_typing_on_Usuario_exist_user_then_get_Usuario() throws Exception {
        String textoSolicitud = CONTROLADOR+"/Usuario";
        String nombreVista = "/InicioSesion/Clave";

        MockHttpServletRequestBuilder builder = post(textoSolicitud).param("usuario", "jesus");
        this.mockMvc
                .perform(builder)
                .andExpect(redirectedUrl(nombreVista));
    }


}
