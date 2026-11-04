package edu.ups.pap.models.prototype;
import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Pizza  extends ProductoBase{
    //ORIGINAL
    public  Pizza(String id){
        super(id, TipoProducto.PIZZA , "Piza simple" ,  new BigDecimal("5.00"));
    }

    //copia
    public Pizza(Pizza original, String nuevoId){
        super(original, nuevoId);
    }

    @Override 
    public Pizza copiar(String nuevoId){
        return  new Pizza(this, nuevoId);

    }


    
}
