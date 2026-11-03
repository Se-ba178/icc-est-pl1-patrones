package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public class Crema extends DecoradorIngredientes{

    private  static final double COSTO = 0.80; // -->Costo adicional

    // Recibe el producto al que agregaremos crema.
    public Crema (ProductoBase producto){
        super(producto);
    }

    @Override 
    public String getNombre(){
        // Conserva el nombre del prodcuto y añade el ingrediente 
        return  producto.getNombre() + "con crema";
    }

    @Override 
    public double getPrecio(){
        // Suma el costo de la crema al precio del producto.
        return  producto.getPrecio() + COSTO;
    }

    @Override 
    public Crema clonar(){
        //Copia el producto interior y lo envuelve con nueva crema.
        // Así también podemos clonar un producto ya decorado.

        return  new Crema(producto.clonar());
    }
    
    

    
}
