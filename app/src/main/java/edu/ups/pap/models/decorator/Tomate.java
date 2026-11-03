package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Tomate extends DecoradorIngredientes{

    // Precio adicional del tomate.
    private static final double COSTO = 0.50;

    // Recibe el producto al que agregaremos tomate.
    public Tomate(ProductoBase producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        // Conserva el nombre anterior y agrega tomate.
        return producto.getNombre() + " con tomate";
    }

    @Override
    public double getPrecio() {
        // Suma el tomate al precio del producto.
        return producto.getPrecio() + COSTO;
    }

    @Override
    public Tomate clonar() {
        // Copia el producto interior y agrega tomate a esa copia.
        return new Tomate(producto.clonar());
    }


    
}
