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
    // Turnos restantes sin tirar para cada jugador. Posada, pozo y carcel
    private int restantes[];
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
        restantes = new int[4];
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
            System.out.println(rival + " va a jugar a la Oca");
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
                    if(restantes[i] == 0){
                        boolean otraTirada;
                        do{
                            int elDadoDice = dadito.getTirada();
                            ficha[i] += elDadoDice;
                            switch (ficha[i]){
                                case 5:
                                case 9:
                                case 14:
                                case 18:
                                case 23:
                                case 27:
                                case 32:
                                case 36:
                                case 41:
                                case 45:
                                case 50:
                                case 54:
                                case 59:
                                    System.out.print("Oca (" + elDadoDice + ")! ");
                                    otraTirada = true;
                                    break;
                                case 26:
                                case 53:
                                    System.out.print("Dados (" + elDadoDice + ")! ");
                                    otraTirada = true;
                                    break;
                                case 6:
                                    ficha[i] = 12;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") va de puente 6 a puente 12");
                                    otraTirada = false;
                                    break;
                                case 12:
                                    ficha[i] = 6;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") va de puente 12 a puente 6");
                                    otraTirada = false;
                                    break;
                                case 19:
                                    restantes[i] = 2;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se toma un descansito en la Posada");
                                    otraTirada = false;
                                    break;
                                case 31:
                                    restantes[i] = 2;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se toma un traguito en el Pozo");
                                    otraTirada = false;
                                    break;
                                case 42:
                                    ficha[i] = 30;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se pierde en el laberinto y vuelve a la 30");
                                    otraTirada = false;
                                    break;
                                case 52:
                                    restantes[i] = 3;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") esta encarcelado!!!");
                                    otraTirada = false;
                                    break;
                                case 58:
                                    ficha[i] = 1;
                                    System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") visita a la parca y vuelve a empezar");
                                    otraTirada = false;
                                default:
                                    if(ficha[i] > casillas){
                                        ficha[i] = casillas - (ficha[i] - casillas);
                                    }
                                    System.out.println(jugadores.get(i).getNombre() + " se mueve (" + elDadoDice + ") a la casilla " + ficha[i]);
                                    otraTirada = false;
                                    break;
                            }
                        }while(otraTirada);
                    }else{
                        System.out.println(jugadores.get(i).getNombre() + ", todavia no puedes moverte. Casilla " + ficha[i]);
                        restantes[i]--;
                    }
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
            restantes[i] = 0;
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
