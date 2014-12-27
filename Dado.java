import java.util.Random;
/**
 * Clase Dado. Un dado. Para juegos de mesa, rol, apuestas, examenes...
 * Lo que te apetezca.
 */
public class Dado
{
    // El tipo de dado creado
    private int caras;
    // El valor de la tirada de dado
    private Random tirada;
    // Los objetos de la clase Random tienen varios metodos para devolver valores
    // Esos valores se escogen aleatoriamente, y pueden ser de diferentes tipos

    /**
     * Numero de caras del dado. Nada complicado.
     * Si el numero es menor de 2, se creara un dado de 6 caras estandar
     */
    public Dado(int caras)
    {
        if(caras > 1){
            this.caras = caras;
        }else{
            this.caras = 6;
        }
        tirada = new Random();
    }

    /**
     * Devuelve el numero de caras del dado
     */
    public int getCaras()
    {
        return caras;
    }
    
    /**
     * Devuelve en formato texto el tipo de dado creado
     * Por ejemplo, un dado de 6 caras devolveria "1d6"
     */
    public String getTipoDado(){
        return "1d" + caras;
    }
    
    /**
     * Tirada de dado
     */
    public int getTirada(){
        return tirada.nextInt(caras)+1;
        // El metodo nextInt devuelve un entero
        // Como en los indices, JAVA empieza a contar desde cero
        // Asi que un metodo nextInt que reciba como parametro el numero 6...
        // podra devolver un valor comprendido entre cero y cinco (incluidos)
    }
}
