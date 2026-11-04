package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Ensalada extends ProductoBase{

    public Ensalada(String id ){
        super(id, TipoProducto.ENSALADA , "Enalada Sencilal" , new BigDecimal("3.00"));
    }

     //recbe el cafe original y crea una copia 
     private Ensalada (Ensalada original , String nuevoId){
        super(original, nuevoId);
    }
    @Override 
    public Ensalada copiar(String nuevoId){
        return new  Ensalada(this,nuevoId);
    }


    
}
