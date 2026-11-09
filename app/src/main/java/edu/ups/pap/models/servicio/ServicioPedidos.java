package edu.ups.pap.models.servicio;

import java.util.ArrayList;

import edu.ups.pap.models.Pedido;
import edu.ups.pap.models.TipoProducto;
import edu.ups.pap.models.decorator.Azucar;
import edu.ups.pap.models.decorator.Crema;
import edu.ups.pap.models.decorator.Pepperoni;
import edu.ups.pap.models.decorator.Pollo;
import edu.ups.pap.models.decorator.Queso;
import edu.ups.pap.models.decorator.Tomate;
import edu.ups.pap.models.prototype.RegistroPrototipos;

public class ServicioPedidos {

    private final RegistroPrototipos registro; // ->Prodcuto original 

    private final ArrayList<Pedido> pedidos; // lista de pedidos creados por el usuario

    private int siguienteId; // Contador de distintos identificadores

    public ServicioPedidos(){
        registro = new RegistroPrototipos();
        pedidos = new ArrayList<>();
        siguienteId = 1;
    }

    public Pedido crearPedido(String clave){
        String nuevoId = "--> " + siguienteId;

        //si la clave no existe ,lanza una excepcion y no se mdfica la lista i el contador
        Pedido nuevo = registro.clonar(clave, nuevoId);
        pedidos.add(nuevo);
        siguienteId++;

        return nuevo;
    }

    private int buscarPosicion(String id){

        if(id == null || id.isBlank()){
            throw new IllegalArgumentException("Primero seleciona un pedido");

        }

        for(int i = 0 ; i < pedidos.size();i++){
            if(pedidos.get(i).id().equals(id)){
                return i;
            }
        }
        throw new IllegalArgumentException("No existe un pedido con el ID" + id);
    }


    public void agregarIngredientes(String id, String ingredientes){

        int posicion = buscarPosicion(id);

        Pedido actual = pedidos.get(posicion);

        if(!esCompatible(actual.tipo() , ingredientes)){
            throw new IllegalArgumentException("El ingrediente no es valido para este producto");

        }

        Pedido decorado;

        //cada ocpion crea un decorador
        switch (ingredientes) {
            case "azucar":
                decorado = new Azucar(actual);
                break;
            case "crema":
                decorado = new Crema(actual);
                break;
            case "tomate":
                decorado = new Tomate(actual);
                break;
            case "pollo":
                decorado = new Pollo(actual);
                break;
            case "queso":
                decorado = new Queso(actual);
                break;
            case "pepperoni":
                decorado = new Pepperoni(actual);
                break;
            default:
                throw new IllegalArgumentException("Ese ingrediente no hay");
        }
        pedidos.set(posicion, decorado);
    


    }
    
    private boolean esCompatible(TipoProducto tipo, String ingrediente){
        if(tipo == TipoProducto.CAFE){
            return "azucar".equals(ingrediente) || "crema".equals(ingrediente);
        }

        if(tipo == TipoProducto.ENSALADA){
            return "tomate".equals(ingrediente) || "pollo".equals(ingrediente);
        }

        if(tipo == TipoProducto.PIZZA){
            return "queso".equals(ingrediente) || "pepperoni".equals(ingrediente);
        }
            
        return false;

    }

    public  Pedido consultarPedido(String id){
        int posicion = buscarPosicion(id);
        return pedidos.get(posicion);
    }

    public ArrayList<Pedido> getPedidos(){
        //Una lista para evotar q de la lista original se puedan eliminar e la lista original 
        return new ArrayList<>(pedidos);
    }

    public void  reiniciarPedidos(){
        pedidos.clear();
    }

    

   
    
}
