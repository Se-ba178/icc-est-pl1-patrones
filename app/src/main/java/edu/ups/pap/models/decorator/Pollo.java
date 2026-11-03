package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Pollo extends DecoradorIngredientes{

    // Costo adicional del pollo.
    private static final double COSTO = 2.50;

    // Recibe el producto al que agregaremos pollo.
    public Pollo(ProductoBase producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        // Conserva el nombre anterior y añade pollo.
        return producto.getNombre() + " con pollo";
    }

    @Override
    public double getPrecio() {
        // Añade el costo del pollo al precio acumulado.
        return producto.getPrecio() + COSTO;
    }

    @Override
    public Pollo clonar() {
        // Copia el producto interior con sus ingredientes
        // y envuelve la copia con pollo.
        return new Pollo(producto.clonar());
    }

    
}
