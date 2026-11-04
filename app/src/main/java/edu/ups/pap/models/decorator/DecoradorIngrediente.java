package edu.ups.pap.models.decorator;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;


//Clase padre de todos los ingreduientes ,implementa pedido igual q los poductos base 

public abstract class DecoradorIngrediente  implements  Pedido {


    //pedido q estamos decorando ya sea simple o con ingredientes 
    protected final Pedido pedido;

    public DecoradorIngrediente(Pedido pedido){
        this.pedido = pedido ;
    }

    @Override 
    public String id(){
        //Agregar un ingrediente conservamos el identificadordel pedido original
        return pedido.id();
    }

    @Override 
    public TipoProducto tipo(){
        return pedido.tipo();
    }


    
}
