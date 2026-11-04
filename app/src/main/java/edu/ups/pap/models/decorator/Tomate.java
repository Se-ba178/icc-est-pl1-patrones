package edu.ups.pap.models.decorator;
import java.math.BigDecimal;
import edu.ups.pap.models.Pedido;


public class Tomate extends DecoradorIngrediente{


    public Tomate(Pedido pedido){
        super(pedido);
    }

    @Override 
    public String descripcion(){
        return pedido.descripcion() + " + Tomate";
    }

    @Override 
    public BigDecimal precio(){
        return pedido.precio().add(new BigDecimal("1.10"));
    }
}
