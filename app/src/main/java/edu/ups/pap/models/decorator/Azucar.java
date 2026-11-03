package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Azucar extends DecoradorIngredientes {

    // Costo adicional del azúcar.
    private static final double COSTO = 0.20;

    // Recibe un producto simple o uno que ya tenga ingredientes.
    public Azucar(ProductoBase producto) {
        super(producto);
    }

    @Override
    public String getNombre() {
        // Conserva el nombre anterior y añade azúcar.
        return producto.getNombre() + " con azúcar";
    }

    @Override
    public double getPrecio() {
        // Suma el azúcar al precio acumulado.
        return producto.getPrecio() + COSTO;
    }

    @Override
    public Azucar clonar() {
        // Clona el producto interior y vuelve a agregar azúcar.
        return new Azucar(producto.clonar());
    }

}
