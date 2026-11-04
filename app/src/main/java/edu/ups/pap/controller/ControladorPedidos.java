package edu.ups.pap.controller;

import java.util.ArrayList;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.servicio.ServicioPedidos;

public class ControladorPedidos {

    private final ServicioPedidos servicio;

    public ControladorPedidos(){
        servicio = new ServicioPedidos();
    }

    //una copia del prodcuto registrado
    public Pedido crearPedido(String clave){
        return servicio.crearPedido(clave);
    }

    //agregar un ingrediente al pedido seleccionado 
    public void agregarIngredientes(String id, String ingrediente){
        servicio.agregarIngredientes(id, ingrediente);
    }

    //consulta la versiona ctual del pedido 
    public Pedido consultarPedido(String id){
        return servicio.consultarPedido(id);
    }

    //obtiene lso pedidos para mostrarlos en la ventana
    public ArrayList<Pedido> getPedidos(){
        return servicio.getPedidos();
    }

    //lipia ls prototipos sin borrar los productos
    public void reiniciarPedidos(){
        servicio.reiniciarPedidos();
    }



    

   

}
