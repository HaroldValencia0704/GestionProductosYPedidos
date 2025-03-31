package com.productosypedidos.gestion_productos_pedidos.service;

import com.productosypedidos.gestion_productos_pedidos.model.Pedido;
import com.productosypedidos.gestion_productos_pedidos.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private static final String PEDIDOS_JSON = "src/main/resources/data/pedidos.json";
    private List<Pedido> pedidos;
    private final JsonUtil jsonUtil;  // ✅ Se inyecta como dependencia

    @Autowired
    public PedidoService(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
        this.pedidos = cargarPedidos();
    }

    private List<Pedido> cargarPedidos() {
        return jsonUtil.leerDesdeJson(PEDIDOS_JSON, Pedido.class);  // ✅ Se usa jsonUtil en lugar de JsonUtil
    }

    private void guardarPedidos() {
        jsonUtil.escribirAJson(PEDIDOS_JSON, pedidos);  // ✅ Se usa jsonUtil en lugar de JsonUtil
    }

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidos;
    }

    public Optional<Pedido> obtenerPedidoPorId(int id) {
        return pedidos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Pedido agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
        guardarPedidos();
        return pedido;
    }

    public boolean eliminarPedido(int id) {
        boolean eliminado = pedidos.removeIf(p -> p.getId() == id);
        if (eliminado) {
            guardarPedidos();
        }
        return eliminado;
    }
}
