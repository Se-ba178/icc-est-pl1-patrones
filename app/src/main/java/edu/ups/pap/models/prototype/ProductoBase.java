package edu.ups.pap.models.prototype;
//Clse padre de Todos los prodcutos finales 
//Abstarcta para crear prodcutos concretos 

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;

public abstract class ProductoBase implements Pedido, Prototipo<ProductoBase> {
    
    private final String id;
    private final TipoProducto tipo;
    private final String descripcion;
    private final BigDecimal precioBase;

    //Constructor para crear un prodcuto original
    protected ProductoBase(String id, TipoProducto tipo , String descripcion , BigDecimal precioBase){
        if(id == null || id.isBlank() ){
            throw new IllegalArgumentException("El identificador estavacio");
        }

        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
    }

    protected  ProductoBase(ProductoBase original , String nuevoId){
        this(nuevoId , original.tipo , original.descripcion, original.precioBase);
    }

    @Override 
    public String id(){
        return id;
    }

    @Override 
    public TipoProducto tipo(){
        return tipo;
    }

    @Override 
    public String descripcion(){
        return descripcion;
    }

    @Override 
    public BigDecimal precio(){
        return precioBase;
    }
   
    @Override 
    public abstract ProductoBase copiar(String nuevoId); //Cada prodcuto  concreto implementara su propia copia 
 

    
}
