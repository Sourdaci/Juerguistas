
/**
 * Write a description of class Cubata here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cubata
{
    // Contenido en alcohol de la copa
    private int alcohol;
    // Nombre de la copa
    private String nombreCopa;

    /**
     * Esta copa es Cubata Normal
     */
    public Cubata()
    {
        alcohol = 10;
        nombreCopa = "Cubata Normal";
    }
    
    /**
     * Esta copa es Cubata Personalizado
     * Si el alcohol no es valido, sera un Cubata Virgen (0%)
     * Si el alcohol es 100, sera un Veneno puro
     */
    public Cubata(int alcohol)
    {
        if(alcohol > 0 && alcohol < 100){
            this.alcohol = alcohol;
            nombreCopa = "Cubata Personalizado";
        }else if(alcohol == 100){
            this.alcohol = 100;
            nombreCopa = "Veneno puro";
        }else{
            this.alcohol = 0;
            nombreCopa = "Cubata Virgen";
        }
    }
    
    /**
     * Devuelve el contenido en alcohol del cubata
     */
    public int getAlcohol(){
        return alcohol;
    }
}