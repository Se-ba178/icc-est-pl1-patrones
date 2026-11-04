package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;

public class Queso extends DecoradorIngrediente{

    public Queso(Pedido pedido){
        super(pedido);
    }

    @Override 
    public String descripcion(){
        return pedido.descripcion() + " + Queso";
    }

    @Override 
    public  BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.00"));
    }

}
