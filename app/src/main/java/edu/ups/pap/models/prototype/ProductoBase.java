package edu.ups.pap.models.prototype;
//Clse padre de Todos los prodcutos finales 
//Abstarcta para crear prodcutos concretos 

public abstract class ProductoBase implements Prototipo {
    //Atributos q vana  tener todos los prodcutos 
    private String nombre;
    private double precio;

    //Constructor para crear un prodcuto con datosiniciales 
    public ProductoBase(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Constructor con las copias 
    protected  ProductoBase(ProductoBase original ){
        this.nombre = original.nombre;
        this.precio = original.precio;
    }

    //Devuelve el precio del prodcuto 
    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return  precio;
    }


    // Permite cambiar el precio 
    // Lo usaremos para comprobar que modificar una copia 
    // no modifica el prodcuto original.
    public void setPrecio(double precio){
        this.precio = precio;
    }

    @Override 
    public abstract ProductoBase clonar();



    
    
    


    
}
