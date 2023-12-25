package org.jeslorlim.registrosporpasos.ControllerTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ControladorTest {
    private static final String CONTROLADOR = "http://localhost:8080/RegistroPorPasos/";

    @Autowired
    private MockMvc mockMvc;
}
