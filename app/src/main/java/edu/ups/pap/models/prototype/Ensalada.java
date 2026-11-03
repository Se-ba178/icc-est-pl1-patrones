package edu.ups.pap.models.prototype;

public class Ensalada extends ProductoBase{

    //Constructor para crear una ensalada con datos iniciales
    public Ensalada(String nombre, double precio){
        super(nombre, precio);
    }

    //Copia los datos de la ensalada original 
    private  Ensalada(Ensalada original){
        super(original);
    }

    //Crea y develve una nueva ensalada a partir de la actual 
    @Override 
    public Ensalada clonar(){
        return new Ensalada(this);
    }

    
}
