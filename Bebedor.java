
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
    // Indica si el bebedor esta consciente o no
    private Boolean consciente = true;

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
     * Devuelve el nombre del Bebedor
     * Para futuros y ludicos usos
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Ofrece al borrachuzo otra copa, y responde en consecuencia
     */
    public String otraCopita(Cubata copazo){
        String respuesta;
        if(!consciente){
            respuesta = nombre +" esta inconsciente";
        }else if(quieroMas()){
            alcoholActual += copazo.getAlcohol();
            respuesta = "Si, tio! Un " + copazo.getNombreCopa() + " p'al body!";
            desmayo();
        }else{
            respuesta = "No, tronco, ya esshtoy borrrassho...";
        }
        return respuesta;
    }
    
    /**
     * Pregunta cosas al borrachuzo y responde dependiendo de su estado y la pregunta
     */
    public String preguntaAlCliente(String pregunta){
        String respuesta;
        if(!consciente){
            respuesta = nombre +" esta inconsciente";
        }else if(!quieroMas() || pregunta.contains(nombre)){
            respuesta = pregunta + "!!!!!!";
        }else if((pregunta.length() % 2) != 0){
            respuesta = "No";
        }else{
            respuesta = "Si";
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
    
    
    /**
     * Deja pasar el tiempo las horas introducidas
     * Cada hora reduce un 10% de su límite el alcohol que existe actualmente en su organismo
     * Recuerda: el alcohol minimo existente en el organismo es 0, nunca sera negativo
     */
    public void pasarCogorza(int horas){
        if(horas > 0){
            alcoholActual -= ((alcoholMaximo * horas) / 10);
        }
        if(alcoholActual < 0){
            alcoholActual = 0;
        }
        recupera();
    }
    
    /**
     * El bebedor queda inconsciente si su alcohol actual es el 160% de su maximo
     */
    private void desmayo(){
        if(alcoholActual > (alcoholMaximo * 1.6)){
            consciente = false;
        }
    }
    
    /**
     * El bebedor recupera la consciencia si su alcohol actual es menor al 90% su alcohol maximo
     */
    private void recupera(){
        if(alcoholActual < (alcoholMaximo * 0.9)){
            consciente = true;
        }
    }
}
