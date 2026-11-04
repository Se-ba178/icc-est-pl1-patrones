package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;

public class Pollo extends DecoradorIngrediente{

    public Pollo(Pedido pedido){
        super(pedido);
    }

    @Override 
    public  String descripcion(){
        return pedido.descripcion() + " + Pollo";
    }

    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.50"));
    }
}
