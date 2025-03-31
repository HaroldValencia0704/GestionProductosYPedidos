package com.productosypedidos.gestion_productos_pedidos.model;
import com.productosypedidos.gestion_productos_pedidos.model.Producto;

import java.util.List;

public class Pedido {
    private int id;
    private String cliente;
    private List<Producto> productos;
    private double total;

    public Pedido() {}

    public Pedido(int id, String cliente, List<Producto> productos, double total) {
        this.id = id;
        this.cliente = cliente;
        this.productos = productos;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
