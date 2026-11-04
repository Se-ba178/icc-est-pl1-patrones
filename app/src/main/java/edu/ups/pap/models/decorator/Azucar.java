package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;


public class Azucar extends DecoradorIngrediente {

    public Azucar(Pedido pedido){
       // entrega el pedido al constructor on el decorador padre
       super(pedido);
    }
    @Override 
    public String descripcion(){
        //CONSERVA LA DESCRIPCION ANTERIOR Y AGREGA AZUCAR 
        return pedido.descripcion() + " + Azucar " ;

    }

    @Override 
    public  BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("0.50"));
    }

    

}
