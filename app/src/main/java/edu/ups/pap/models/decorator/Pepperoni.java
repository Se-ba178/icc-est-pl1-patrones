package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Pepperoni extends DecoradorIngredientes{
    
    // Costo adicional del pepperoni.
    private static final double COSTO = 2.00;

    // Recibe un producto, que también puede tener otros ingredientes.
    public Pepperoni(ProductoBase producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        // Conserva el nombre anterior y añade pepperoni.
        return producto.getNombre() + " con pepperoni";
    }

    @Override
    public double getPrecio() {
        // Suma el costo del pepperoni al precio acumulado.
        return producto.getPrecio() + COSTO;
    }

    @Override
    public Pepperoni clonar() {
        // Clona el producto interior, incluidos sus ingredientes,
        // y envuelve esa copia con pepperoni.
        return new Pepperoni(producto.clonar());
    }
    
}
