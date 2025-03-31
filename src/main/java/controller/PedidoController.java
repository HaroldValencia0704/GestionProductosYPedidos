package com.productosypedidos.gestion_productos_pedidos.controller;

import com.productosypedidos.gestion_productos_pedidos.model.Pedido;
import com.productosypedidos.gestion_productos_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPedidoPorId(@PathVariable int id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping
    public Pedido agregarPedido(@RequestBody Pedido pedido) {
        return pedidoService.agregarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public String eliminarPedido(@PathVariable int id) {
        return pedidoService.eliminarPedido(id) ? "Pedido eliminado correctamente" : "Pedido no encontrado";
    }
}
