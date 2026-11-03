package edu.ups.pap.models.servicio;

import java.util.TreeMap;
import java.util.TreeSet;

import edu.ups.pap.models.decorator.Azucar;
import edu.ups.pap.models.decorator.Crema;
import edu.ups.pap.models.decorator.Pepperoni;
import edu.ups.pap.models.decorator.Pollo;
import edu.ups.pap.models.decorator.Queso;
import edu.ups.pap.models.decorator.Tomate;
import edu.ups.pap.models.prototype.Cafe;
import edu.ups.pap.models.prototype.Ensalada;
import edu.ups.pap.models.prototype.Pizza;
import edu.ups.pap.models.prototype.ProductoBase;

public class ServicioPedidos {

    private final TreeMap<String, ProductoBase> prototipo;

    public ServicioPedidos(){
        prototipo = new TreeMap<>();

        prototipo.put("cafe" , new Cafe("cafe expreso", 3.50));
        prototipo.put("pizza", new Pizza("Pizza artesanal " , 9.50));
        prototipo.put("ensalada", new Ensalada("Ensalada Cesar", 7.00));
    }

    public TreeSet<String> getTiposDisponibles(){

        return  new TreeSet<>(prototipo.keySet());// extraemso las claves 
    }

    // busca el producto solicitado y devuelve una copia 
    public ProductoBase crearProducto(String tipo){
        ProductoBase original = prototipo.get(tipo);

        //Comprobamos que el prodcuto este registrado
        if(original == null ){
            throw new IllegalArgumentException("Prodcuto no registrado" + tipo);
        }
        //prototype --> copiamos el producto original 
        return  original.clonar();
    }

    public ProductoBase crearCafeConExtras(){

        //Primero obligamos a copia del cafe 
        ProductoBase cafe = crearProducto("cafe");

        //lugo personalizamos la copia con dos decoradores
        cafe = new Crema(cafe);
        cafe = new Azucar(cafe);

        return  cafe;
    }
    public ProductoBase crearPizzaConExtras() {

        // Primero obtenemos una copia de la pizza.
        ProductoBase pizza = crearProducto("pizza");

        // Agregamos queso y pepperoni.
        pizza = new Queso(pizza);
        pizza = new Pepperoni(pizza);

        return pizza;
    }

    public ProductoBase crearEnsaladaConExtras() {

        // Primero obtenemos una copia de la ensalada.
        ProductoBase ensalada = crearProducto("ensalada");

        // Agregamos pollo y tomate.
        ensalada = new Pollo(ensalada);
        ensalada = new Tomate(ensalada);

        return ensalada;
    }
    
}
