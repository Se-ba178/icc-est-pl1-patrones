package edu.ups.pap.models.decorator;

import java.math.BigDecimal;

import edu.ups.pap.models.Pedido;

public class Pepperoni extends DecoradorIngrediente{
    
   public Pepperoni(Pedido pedido){
        super(pedido);
   }

   @Override 
   public String descripcion(){
        return pedido.descripcion() + " + Pepperoni";
   }
   @Override
   public BigDecimal precio(){
    return pedido.precio().add(new BigDecimal("1.10"));
   }
    
}
