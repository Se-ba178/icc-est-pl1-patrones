package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;


public class Crema extends DecoradorIngrediente{

    public Crema(Pedido pedido){
        super(pedido);
    }

    @Override 
    public  String descripcion(){
        //AGrega crema a la descripcion acumulada
        return pedido.descripcion() + " + Crema";
    }

    @Override 
    public BigDecimal precio(){
        //Agrega el dolar 20 al precio acumulado
        return pedido.precio().add(new BigDecimal("1.20"));

    }
    
    

    
}
