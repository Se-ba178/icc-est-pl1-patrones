package edu.ups.pap.models.decorator;

import edu.ups.pap.models.prototype.ProductoBase;

public abstract class DecoradorIngredientes  extends ProductoBase{

    //Prodcuto q vamos a decorar 
    //protected permite que las clases hijas puedan utilizarlo;

    protected final ProductoBase producto;


    //recibe el producto al q agregemos un igrediente 
    protected DecoradorIngredientes(ProductoBase producto ){

        // Inicializamos los datos que exige la clase padre 
        super(producto.getNombre(), producto.getPrecio());

        this.producto = producto; // Guaramosel prodcuto para ver sus datos 
    }

    @Override 
    public String getNombre(){
        //devuelve elnombre del proucto recibido 

        return producto.getNombre();
    }
    @Override 
    public double getPrecio(){
        // Por ahora devuelve el precio del producto recibido
        //cada ingrediente añadira su costo
        return producto.getPrecio();
    }
    
}
