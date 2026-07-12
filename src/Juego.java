import java.util.Random;
import java.util.Scanner;
public class Juego {
    private Jugador jugador;
    private Tablero tablero;
    private boolean juegoTerminado;
    private Scanner scanner;
    private int turno;


    public Juego() {
        this.juegoTerminado = false;
        this.scanner = new Scanner(System.in);
        this.turno = 0;
    }


    public Jugador getJugador() {
        return jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    public int getTurno() {
        return turno;
    }




    public void iniciarJuego() {
        mostrarMenu();
        String entrada = scanner.nextLine().trim();

        if (entrada.equals("2")) {
            System.out.println("Hasta luego!");

        } else if (entrada.equals("1")) {
            Scanner lector = new Scanner(System.in);
            System.out.println("Creando el mundo...");
            System.out.println("añade dimension tablero filas");
            int filaT = lector.nextInt();
            System.out.println("añade dimension tablero columnas");
            int columnaT = lector.nextInt();
        //    this.jugador = new Jugador("leonardo",5,5);

            while(!juegoTerminado) {
                Tablero tabla = new Tablero(filaT,columnaT,filaT+5,5,0);
                tabla.generarTablero();
                Enemigo2 aleatorio = new Enemigo2(2,4);
                tabla.colocarEnemigos2(aleatorio);
                Jugador Leonardo = new Jugador("leonardo",2,2);
                tabla.colocarJugador(Leonardo);
                tabla.mostrarTablero();
                lector.nextInt();
                System.out.println("APRETA TECLA");
                String direccion = lector.nextLine();
                Leonardo.mover(direccion);
                actualizarTablero();
                mostrarEstado();
                ejecutarTurno();
                verificarFinJuego();
            }
        } else {
            System.out.println("Opción no válida. Reinicia el juego.");
        }
    }

    public void mostrarEstado() {

    }
    public void actualizarTablero() {

    }
    public void ejecutarTurno() {

    }
    public void verificarFinJuego() {

    }

    private void mostrarMenu() {

        System.out.println("   PAC-MAN                ");
        System.out.println("   1. Jugar               ");
        System.out.println("   2. Salir               ");
        System.out.println("--------------------------");
        System.out.print("Elige una opcion: ");
    }
}

class pruebajuego {
    public static void main(String[] args) {
        Tablero miTablero = new Tablero(10, 10, 5, 0, 0);
        miTablero.generarTablero();
        Enemigo2 aleatorio = new Enemigo2(3,5);
        miTablero.colocarEnemigos2(aleatorio);
        miTablero.mostrarTablero();

        aleatorio.mostrarEstado();
    }
}