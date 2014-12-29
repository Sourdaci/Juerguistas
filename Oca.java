import java.util.ArrayList;
/**
 * Tablero para jugar a la Oca. Version de 1 dado.
 */
public class Oca{
    // Numero de casillas del tablero. Por hacerlo bonito.
    private int casillas;
    // Jugadores que se apuntaran a la partida
    private ArrayList<Bebedor> jugadores;
    // Numero maximo de jugadores de la partida
    private int maximoJugadores;
    // Posicion de la ficha de cada jugador
    private int ficha[];
    // Turnos restantes sin tirar para cada jugador. Posada, pozo y carcel
    private int restantes[];
    // Dado para jugar. Es virtual, con uno basta. Y no lo perdera algun manazas
    private Dado dadito;
    // Indica si la partida ha comenzado
    private boolean iniciada; // Para evitar sumar jugadores a una partida empezada
    // Indica si la partida ha finalizado, para evitar mostrar tiradas si alguien ya ha acabado
    private int finalizada; // Se usa un int para almacenar el indice del jugador ganador
                            // Se inicializa a -1

    /**
     * Nuevo tablero de la oca
     */
    public Oca(){
        casillas = 63; // La oca tiene 63 casillas
        iniciada = false; // Tablero limpio, la partida no ha empezado
        jugadores = new ArrayList<Bebedor>();
        maximoJugadores = 4;
        ficha = new int[maximoJugadores];
        restantes = new int[maximoJugadores];
        dadito = new Dado(6); // El dado de la victoria... o la fatalidad
        finalizada = -1; // Tablero limpio, la partida no ha acabado
    }

    /**
     * Suma jugadores a la partida
     * Para que no sea un descontrol absoluto.. maximo "maximoJugadores" jugadores
     */
    public void addBebedor(Bebedor jugador){
        String rival = jugador.getNombre(); // Guardamos el nombre, lo vamos a usar luego
        if(iniciada){ // Comprueba si la partida ha iniciado, para no agregar jugadores habiendo empezado
            System.out.println(rival + " no puede sumarse a la partida, ya ha empezado");
        }else if(jugadores.size() < maximoJugadores){ // Comprueba si todavia hay espacio para que se unan jugadores
            ficha[jugadores.size()] = 1;
            // Asignar la casilla antes del jugador puede parecer lioso y problematico
            // Pero es lo mismo que agregar al jugador y luego poner: casilla[jugadores.size() - 1] = 1;
            jugadores.add(jugador);
            System.out.println(rival + " va a jugar a la Oca");
        }else{ // Ya no hay espacio para mas jugadores
            System.out.println(rival + " no puede sumarse a la partida, numero maximo de jugadores alcanzado");
        }
    }
    
    /**
     * Hace una ronda de tiradas
     */
    public void tirada(){
        int numeroJugadores = jugadores.size(); // Almacenamos el numero actual de jugadores
        switch (numeroJugadores) {
            case 0: // No hay jugadores
                System.out.println("Partida sin jugadores? Estamos locos o que?");
                break;
            case 1: // Solo hay 1 jugador
                System.out.println(jugadores.get(0).getNombre() + ", has ganado. Lo raro seria que perdieras jugando solo tu...");
                break;
            default: // Al menos 2 jugadores
                iniciada = true; // Se declara la partida como iniciada
                for(int i=0; i<numeroJugadores; i++){ // Bucle de tiradas para movimiento de fichas y turnos parado restantes
                    if(finalizada == -1){ // Se comprueba si alguien ya ha llegado a la ultima casilla, para no seguir moviendo las demas
                        if(restantes[i] == 0){ // Se comprueba si el jugador puede mover la ficha
                            boolean otraTirada; // Se declara un comprobador de tirada extra por Oca o por Dados
                            do{ // Inicio bucle de tirada de dado
                                int elDadoDice = dadito.getTirada(); //Guardamos la tirada para mostrarla luego
                                ficha[i] += elDadoDice; // Suma la tirada del dado a la posicion de la ficha
                                switch (ficha[i]){ // Operacion a realizar segun la casilla donde caiga la ficha
                                    // Hay 12 + 1 casillas de Oca, tirada extra
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
                                    // Hay 2 casillas de Dado, tirada extra
                                    case 26:
                                    case 53:
                                        System.out.print("Dados (" + elDadoDice + ")! ");
                                        otraTirada = true;
                                        break;
                                    // El puente de avanzar
                                    case 6:
                                        ficha[i] = 12;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") va de puente 6 a puente 12");
                                        otraTirada = false;
                                        break;
                                    // El puente de retroceder
                                    case 12:
                                        ficha[i] = 6;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") va de puente 12 a puente 6");
                                        otraTirada = false;
                                        break;
                                    // La posada, 2 turnos
                                    case 19:
                                        restantes[i] = 2;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se toma un descansito en la Posada");
                                        otraTirada = false;
                                        break;
                                    // El pozo, 2 turnos
                                    case 31:
                                        restantes[i] = 2;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se toma un traguito en el Pozo");
                                        otraTirada = false;
                                        break;
                                    // El laberinto, retrocede a la casilla 30
                                    case 42:
                                        ficha[i] = 30;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") se pierde en el laberinto y vuelve a la 30");
                                        otraTirada = false;
                                        break;
                                    // La carcel, 3 turnos
                                    case 52:
                                        restantes[i] = 3;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") esta encarcelado!!!");
                                        otraTirada = false;
                                        break;
                                    // La muerte, volver a la casilla 1
                                    case 58:
                                        ficha[i] = 1;
                                        System.out.println(jugadores.get(i).getNombre() + " (" + elDadoDice + ") visita a la parca y vuelve a empezar");
                                        otraTirada = false;
                                    // Las casillas que no tienen evento
                                    default:
                                        if(ficha[i] > casillas){ // Si la tirada te lleva mas alla de la ultima casilla
                                            // Retrocede las casillas que te pases
                                            ficha[i] = casillas - (ficha[i] - casillas);
                                        }
                                        System.out.println(jugadores.get(i).getNombre() + " se mueve (" + elDadoDice + ") a la casilla " + ficha[i]);
                                        otraTirada = false;
                                        break;
                                }
                            }while(otraTirada); // Solo las Ocas y los Dados hacen continuar en el bucle
                        }else{ // Si no puedes mover la ficha
                            // Se informa de ello
                            System.out.println(jugadores.get(i).getNombre() + ", todavia no puedes moverte. Casilla " + ficha[i]);
                            restantes[i]--;
                        }
                        if(ficha[i] == casillas){ // Despues de todo, comprueba si esa ficha esta en la ultima casilla
                            // Si lo esta, la declara como ganador de la partida
                            finalizada = i;
                        }
                    }
                }
                // Para hacer bonito y facilitar la lectura de los turnos
                System.out.println("-------------------------------------------------------------------\n");
                if(finalizada >= 0){ // Si ya hay un ganador, lo muestra y reinicia la partida
                            System.out.println(jugadores.get(finalizada).getNombre() + " ha ganado la partida!!!");
                            reiniciaPartida();
                            System.out.println("Partida reiniciada por si algun perdedor quiere la revancha\n");
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
        finalizada = -1;
    }
    
    /**
     * Reinicia el tablero
     * Nueva partida con nuevos jugadores
     */
    public void reiniciaTablero(){
        iniciada = false;
        finalizada = -1;
        jugadores = null;
        jugadores = new ArrayList<Bebedor>();
        ficha = null;
        ficha = new int[maximoJugadores];
        restantes = null;
        restantes = new int[maximoJugadores];
    }
}
