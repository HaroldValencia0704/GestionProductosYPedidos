package com.productosypedidos.gestion_productos_pedidos.service;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import com.productosypedidos.gestion_productos_pedidos.utils.JsonUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.productosypedidos.gestion_productos_pedidos.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    private ProductoService productoService;
    private JsonUtil jsonUtilMock;

    @BeforeEach
    void setUp() {
        jsonUtilMock = mock(JsonUtil.class);
        List<Producto> productosSimulados = new ArrayList<>();
        productosSimulados.add(new Producto(1, "Producto1", 100.0));
        productosSimulados.add(new Producto(2, "Producto2", 200.0));

        Mockito.when(jsonUtilMock.leerDesdeJson(Mockito.anyString(), Mockito.eq(Producto.class)))
                .thenReturn(productosSimulados);

        productoService = new ProductoService(jsonUtilMock);
    }

    @Test
    void testObtenerProductos() {
        List<Producto> productos = productoService.obtenerProductos();
        assertEquals(2, productos.size(), "Se deben devolver 2 productos.");
        assertEquals("Producto1", productos.get(0).getNombre(), "El primer producto debe ser 'Producto1'.");
    }

    @Test
    void testAgregarProducto() {
        Producto nuevoProducto = new Producto(3, "Producto3", 300.0);
        productoService.agregarProducto(nuevoProducto);
        verify(jsonUtilMock).escribirAJson(Mockito.anyString(), Mockito.anyList());
        List<Producto> productos = productoService.obtenerProductos();
        assertEquals(3, productos.size(), "Debe haber 3 productos después de agregar uno nuevo.");
    }

    @Test
    void testObtenerProductoPorId() {
        Producto producto = productoService.obtenerProductoPorId(1);
        assertNotNull(producto, "El producto con ID 1 debe existir.");
        assertEquals("Producto1", producto.getNombre(), "El producto debe ser 'Producto1'.");
    }

    @Test
    void testObtenerProductoPorId_NoExistente() {
        Producto producto = productoService.obtenerProductoPorId(99);
        assertNull(producto, "El producto con ID 99 no debe existir.");
    }

    @Test
    void testEliminarProducto() {
        boolean resultado = productoService.eliminarProducto(1);
        assertTrue(resultado, "El producto con ID 1 debe eliminarse correctamente.");
        verify(jsonUtilMock).escribirAJson(Mockito.anyString(), Mockito.anyList());
    }

    @Test
    void testEliminarProducto_NoExistente() {
        boolean resultado = productoService.eliminarProducto(99);
        assertFalse(resultado, "El producto con ID 99 no existe y no debe eliminarse.");
    }

    @Test
    void testActualizarProducto() {
        Producto productoActualizado = new Producto(1, "Producto1 Modificado", 150.0);
        boolean resultado = productoService.actualizarProducto(productoActualizado);
        assertTrue(resultado, "El producto con ID 1 debe actualizarse correctamente.");
        verify(jsonUtilMock).escribirAJson(Mockito.anyString(), Mockito.anyList());
    }

    @Test
    void testActualizarProducto_NoExistente() {
        Producto productoInexistente = new Producto(99, "Producto99", 999.0);
        boolean resultado = productoService.actualizarProducto(productoInexistente);
        assertFalse(resultado, "No se debe actualizar un producto que no existe.");
    }
}
