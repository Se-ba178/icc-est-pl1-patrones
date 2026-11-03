package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Queso extends DecoradorIngredientes{
    // Costo adicional del queso.
    private static final double COSTO = 1.50;

    // Recibe el producto al que agregaremos queso.
    public Queso(ProductoBase producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        // Conserva el nombre y añade el ingrediente.
        return producto.getNombre() + " con queso";
    }

    @Override
    public double getPrecio() {
        // Suma el queso al precio acumulado del producto.
        return producto.getPrecio() + COSTO;
    }

    @Override
    public Queso clonar() {
        // Copia el producto interior y lo envuelve con queso.
        return new Queso(producto.clonar());
    }
    
}
