package edu.ups.pap.controller;

import java.rmi.server.ServerCloneException;
import java.util.ArrayList;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.decorator.Azucar;
import edu.ups.pap.models.decorator.Crema;
import edu.ups.pap.models.decorator.Pepperoni;
import edu.ups.pap.models.decorator.Pollo;
import edu.ups.pap.models.decorator.Queso;
import edu.ups.pap.models.decorator.Tomate;
import edu.ups.pap.models.prototype.ProductoBase;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class ControladorPedidos {
    
    // Servicio que administra los pototipos 
    private  final ServicioPedidos servicio;

    private final Pedido pedido ;

    public ControladorPedidos(){
        servicio = new ServicioPedidos();
        pedido = new Pedido();
    }

    public void agregarProducto(
            String tipo,
            boolean crema,
            boolean azucar,
            boolean queso,
            boolean pepperoni,
            boolean pollo,
            boolean tomate) {

        // Prototype: obtenemos una copia del producto seleccionado.
        ProductoBase producto = servicio.crearProducto(tipo);

        // Decorator: envolvemos el producto con cada
        // ingrediente que el usuario haya seleccionado.

        if (crema) {
            producto = new Crema(producto);
        }

        if (azucar) {
            producto = new Azucar(producto);
        }

        if (queso) {
            producto = new Queso(producto);
        }

        if (pepperoni) {
            producto = new Pepperoni(producto);
        }

        if (pollo) {
            producto = new Pollo(producto);
        }

        if (tomate) {
            producto = new Tomate(producto);
        }

        // Guardamos el producto personalizado en el pedido.
        pedido.agregarProducto(producto);
    }
    // Devuelve los productos para mostrarlos en la ventana.
    public ArrayList<ProductoBase> getProductos() {
        return pedido.getProductos();
    }

    // Devuelve el total del pedido.
    public double getTotal() {
        return pedido.calcularTotal();
    }

    // Devuelve cuántos productos hay.
    public int getCantidadProductos() {
        return pedido.getCantidadProductos();
    }

    // Elimina el producto seleccionado por su posición.
    public void eliminarProducto(int posicion) {
        pedido.eliminarProducto(posicion);
    }

    // Vacía el pedido para comenzar nuevamente.
    public void vaciarPedido() {
        pedido.vaciarPedido();
    }

}
