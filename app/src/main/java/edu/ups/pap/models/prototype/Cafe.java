package edu.ups.pap.models.prototype;

import java.math.BigDecimal;

import edu.ups.pap.models.TipoProducto;

public class Cafe extends ProductoBase{

    //cafe con la base
    public Cafe(String id){
        super(id,TipoProducto.CAFE, "Cafe" , new BigDecimal("2.20"));
    }

    //recbe el cafe original y crea una copia 
    private Cafe(Cafe original , String nuevoId){
        super(original, nuevoId);
    }

    @Override 
    public Cafe copiar(String nuevoId){
        return new Cafe(this , nuevoId);//this crea un cafe diferente con ls mismos datos
    }


    
}
