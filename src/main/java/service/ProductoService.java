package com.productosypedidos.gestion_productos_pedidos.service;

import com.productosypedidos.gestion_productos_pedidos.model.Producto;
import com.productosypedidos.gestion_productos_pedidos.utils.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

@Service
public class ProductoService {
    private static final String FILE_PATH = "src/main/resources/data/productos.json";
    private List<Producto> productos;
    private final JsonUtil jsonUtil;

    // Usa @Autowired para inyectar JsonUtil correctamente
    @Autowired
    public ProductoService(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
        this.productos = this.jsonUtil.leerDesdeJson(FILE_PATH, Producto.class);
        if (this.productos == null) {
            this.productos = new ArrayList<>();
        }
    }

    public List<Producto> obtenerProductos() {
        return productos;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        this.jsonUtil.escribirAJson(FILE_PATH, productos);
    }

    public boolean eliminarProducto(int id) {
        boolean eliminado = productos.removeIf(p -> p.getId() == id);
        if (eliminado) {
            jsonUtil.escribirAJson(FILE_PATH, productos);
        }
        return eliminado;
    }

    public boolean actualizarProducto(Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId() == productoActualizado.getId()) {
                productos.set(i, productoActualizado);
                jsonUtil.escribirAJson(FILE_PATH, productos);
                return true;
            }
        }
        return false;
    }

    public Producto obtenerProductoPorId(int id) {
        return productos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
