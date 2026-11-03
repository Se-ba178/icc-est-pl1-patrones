package edu.ups.pap.models;

import java.util.ArrayList;

import edu.ups.pap.models.prototype.ProductoBase;

public  class Pedido {

    
    // Lista de productos agregados al pedido.
    // Cada producto puede tener ingredientes adicionales.
    private final ArrayList<ProductoBase> productos;

    // Al crear un pedido, su lista comienza vacía.
    public Pedido() {
        productos = new ArrayList<>();
    }

    // Agrega un producto al pedido.
    public void agregarProducto(ProductoBase producto) {

        // Evitamos agregar un producto vacío.
        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede ser null"
            );
        }

        productos.add(producto);
    }

    // Elimina un producto según su posición.
    // Las posiciones de la lista comienzan en cero.
    public void eliminarProducto(int posicion) {

        if (posicion < 0 || posicion >= productos.size()) {
            throw new IllegalArgumentException(
                "La posición del producto no existe"
            );
        }

        productos.remove(posicion);
    }

    // Suma los precios de todos los productos,
    // incluidos los ingredientes agregados.
    public double calcularTotal() {
        double total = 0;

        for (ProductoBase producto : productos) {
            total = total + producto.getPrecio();
        }

        return total;
    }

    // Devuelve la cantidad de productos del pedido.
    public int getCantidadProductos() {
        return productos.size();
    }

    // Devuelve una copia de la lista.
    // Los productos que contiene siguen siendo los mismos objetos.
    public ArrayList<ProductoBase> getProductos() {
        return new ArrayList<>(productos);
    }

    // Elimina todos los productos del pedido.
    public void vaciarPedido() {
        productos.clear();
    }


     
}
