package edu.ups.pap.models.prototype;

public class Cafe extends ProductoBase{

    //CONSTRUCTOR para crear un cafe un cafe 
    public  Cafe(String nombre,  double precio){
        super(nombre, precio);

    }


    // recibe el cafe original y utiliza el constructor 
    // de copia de productoBase para copiar , nombre y precio 
    private Cafe(Cafe original ){
        super(original);
    }

    //Implementacion del patron Protype
    @Override 
    public   Cafe clonar(){
        //Cra un objeto nuevo copianda este cafe 
        return new Cafe(this);
    }


    
}
