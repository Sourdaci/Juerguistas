import java.util.ArrayList;
/**
 * Tablero para jugar a la Oca. Version de 1 dado.
 */
public class Oca{
    // Numero de casillas del tablero. Por hacerlo bonito.
    private int casillas;
    // Jugadores que se apuntaran a la partida
    private ArrayList<Bebedor> jugadores;
    // Posicion de la ficha de cada jugador
    private int ficha[];
    // Dado para jugar. Es virtual, con uno basta. Y no lo perdera algun manazas
    private Dado dadito;
    // Indica si la partida ha comenzado
    private boolean iniciada; // Para evitar sumar jugadores a una partida empezada

    /**
     * Nuevo tablero de la oca
     */
    public Oca(){
        casillas = 63;
        iniciada = false;
        jugadores = new ArrayList<Bebedor>();
        ficha = new int[4];
        dadito = new Dado(6);
    }

    /**
     * Suma jugadores a la partida
     * Para que no sea un descontrol absoluto.. maximo 4 jugadores
     */
    public void addBebedor(Bebedor jugador){
        String rival = jugador.getNombre();
        if(iniciada){
            System.out.println(rival + " no puede sumarse a la partida, ya ha empezado");
        }else if(jugadores.size() < 4){
            ficha[jugadores.size()] = 1;
            // Asignar la casilla antes del jugador puede parecer lioso y problematico
            // Pero es lo mismo que agregar al jugador y luego poner: casilla[jugadores.size() - 1] = 1;
            jugadores.add(jugador);
            System.out.println(rival + " va a jugar a la Oca contigo");
        }else{
            System.out.println(rival + " no puede sumarse a la partida, numero maximo de jugadores alcanzado");
        }
    }
    
    /**
     * Hace una ronda de tiradas
     */
    public void tirada(){
        int numeroJugadores = jugadores.size();
        switch (numeroJugadores) {
            case 0:
                System.out.println("Partida sin jugadores? Estamos locos o que?");
                break;
            case 1:
                System.out.println(jugadores.get(0).getNombre() + ", has ganado. Lo raro seria que perdieras jugando solo tu...");
                break;
            default:
                iniciada = true;
                for(int i=0; i<numeroJugadores; i++){
                    // Suma la tirada del dado a la posicion de la ficha
                    ficha[i] += dadito.getTirada();
                    // Comprueba si la ficha se ha pasado de la casilla final
                    if(ficha[i] > casillas){
                        // Retrocede la ficha las casillas que se excede
                        ficha[i] -= (ficha[i] % casillas);
                    }
                    System.out.println(jugadores.get(i).getNombre() + " se mueve a la casilla " + ficha[i]);
                }
                System.out.println("-------------------------------------------------------------------\n");
                for(int i=0; i<numeroJugadores; i++){
                    if(ficha[i] == casillas){
                        System.out.println(jugadores.get(i).getNombre() + " ha ganado la partida!!!");
                        reiniciaPartida();
                        System.out.println("Partida reiniciada por si algun perdedor quiere la revancha\n");
                    }
                }
                break;
        }
    }
    
    /**
     * Reinicia la partida con los mismos jugadores
     * Por si a mitad se arrepienten y quieren volver a empezar
     */
    public void reiniciaPartida(){
        for(int i=0; i<jugadores.size(); i++){
            ficha[i] = 1;
        }
        iniciada = false;
    }
    
    /**
     * Reinicia el tablero
     * Nueva partida con nuevos jugadores
     */
    public void reiniciaTablero(){
        iniciada = false;
        jugadores = null;
        jugadores = new ArrayList<Bebedor>();
        ficha = null;
        ficha = new int[4];
    }
}
