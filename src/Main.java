import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner lector=new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario");
        String nombreJugador = lector.nextLine();
        Jugador nomJugador = new Jugador(nombreJugador);

        Jugador Posicion = new Jugador(1,1);
        Jugador Puntajeyvelocidad = new Jugador(1,1,1);
        Jugador PoderyVelocidad =
        jugadorUnico.mostrarEstado();
    }
}