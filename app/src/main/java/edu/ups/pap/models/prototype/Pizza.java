package edu.ups.pap.models.prototype;

public class Pizza  extends ProductoBase{

    //Constructor para crear una pizza con los datos iniciles 
    public  Pizza(String nombre , double precio){
        super(nombre, precio);
    }

    //Copia los datos de la pizza original 
    private Pizza(Pizza original ){
        super(original);
    }

    //Crea y devuelve una nueva pizza a partir de la actual
    @Override 
    public Pizza clonar(){
        return new Pizza(this);
    }

    
}
