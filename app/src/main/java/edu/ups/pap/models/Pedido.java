package edu.ups.pap.models;

import java.math.BigDecimal;


public  interface Pedido {
    //Nombre del prodcuto y sus ingredientes 
    String descripcion();

    //el precio acumulado
    BigDecimal precio();

    //Devuelve el identificador el id del producto 
    String id();

    //devuelve q es , afe,ensalada o pizza
    TipoProducto tipo();
     
}
