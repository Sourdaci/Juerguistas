
/**
 * Write a description of class Bebedor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bebedor
{
    // Cantidades de alcohol de la persona
    private int alcoholMaximo, alcoholActual;
    // Nombre de la persona
    private String nombre;
    

    /**
     * Un nuevo borrachuzo entra en nuestro garito de mala muerte
     */
    public Bebedor(String nombre, int alcoholMaximo)
    {
        this.alcoholMaximo = alcoholMaximo;
        alcoholActual = 0;
        this.nombre = nombre;
    }

    /**
     * Un nuevo borrachuzo entra en nuestro garito de mala muerte
     * Y VIENE COCIDO
     */
    public Bebedor(String nombre, int alcoholMaximo, int alcoholActual)
    {
        this.alcoholMaximo = alcoholMaximo;
        if(alcoholActual > 0 && alcoholActual < alcoholMaximo){
            this.alcoholActual = alcoholActual;
        }else{
            this.alcoholActual = ((this.alcoholMaximo * 8) / 10);
        }
        this.nombre = nombre;
    }
    
    /**
     * Ofrece al borrachuzo otra copa, y responde en consecuencia
     */
    public String otraCopita(Cubata copazo){
        String respuesta = "Si, tio!";
        if(quieroMas()){
            alcoholActual += copazo.getAlcohol();
        }else{
            respuesta = "No, tronco, ya esshtoy borrrassho...";
        }
        return respuesta;
    }
    
    /**
     * Pregunta cosas al borrachuzo y responde dependiendo de su estado y la pregunta
     */
    public String preguntaAlCliente(String pregunta){
        String respuesta = "Si";
        if(!quieroMas() || pregunta.contains(nombre)){
            respuesta = "¡" + pregunta + "!";
        }else if((pregunta.length() % 2) != 0){
            respuesta = "No";
        }
        return respuesta;
    }
    
    /**
     * Comprueba el estado del borrachuzo a ver si acepta mas alcohol
     */
    private boolean quieroMas(){
        Boolean otraMas = true;
        if(alcoholActual > alcoholMaximo){
            otraMas = false;
        }
        return otraMas;
    }
}
