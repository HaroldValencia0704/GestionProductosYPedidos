package com.productosypedidos.gestion_productos_pedidos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testObtenerProductos() throws Exception {
        // Insertar un producto antes de probar GET
        Producto producto = new Producto(1, "Producto Test", 100.0);
        String productoJson = objectMapper.writeValueAsString(producto);

        mockMvc.perform(post("/productos")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isOk());

        // Ahora probar el GET
        mockMvc.perform(get("/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testAgregarProducto() throws Exception {
        Producto nuevoProducto = new Producto(99, "Producto de Prueba", 150.0);
        String productoJson = objectMapper.writeValueAsString(nuevoProducto);

        mockMvc.perform(post("/productos")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(99))
                .andExpect(jsonPath("$.nombre").value("Producto de Prueba"))
                .andExpect(jsonPath("$.precio").value(150.0));
    }
}
