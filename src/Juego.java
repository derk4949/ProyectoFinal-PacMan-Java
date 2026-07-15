import java.util.Random;
import java.util.Scanner;
public class Juego {
    private Jugador jugador;
    private Tablero tablero;
    private boolean juegoTerminado;
    private Scanner scanner;
    private int turno;
    private Enemigo2 aleatorio;


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
            System.out.println("Creando el mundo...");
            System.out.println("añade dimension tablero filas");
            int filaT = scanner.nextInt();
            System.out.println("añade dimension tablero columnas");
            int columnaT = scanner.nextInt();
        //    this.jugador = new Jugador("leonardo",5,5);

            this.tablero = new Tablero(filaT,columnaT,filaT+5,5,0);
            this.tablero.generarTablero();


            int []posicionLibre = this.tablero.obtenerPosicionLibreAleatoria();
            int filaFantasma = posicionLibre[0];
            int colFantasma = posicionLibre[1];
            this.aleatorio = new Enemigo2(filaFantasma,colFantasma);
            this.tablero.colocarEnemigos2(aleatorio);


            this.jugador = new Jugador("leonardo",2,2);
            this.tablero.colocarJugador(jugador);
            this.tablero.mostrarTablero();


            while(!juegoTerminado) {
                ejecutarTurno();
                actualizarTablero();
                mostrarEstado();
                verificarFinJuego();
            }
        } else {
            System.out.println("Opción no válida. Reinicia el juego.");
        }
    }

    public void mostrarEstado() {

    }
    public void actualizarTablero() {
        this.tablero.colocarJugador(this.jugador);
        this.tablero.colocarEnemigos2(this.aleatorio);
        this.tablero.mostrarTablero();
    }

    public void ejecutarTurno() {
        System.out.println("apreta para mover");
        String direccion = scanner.nextLine();

        this.jugador.mover(direccion, this.tablero);

        this.aleatorio.mover(this.jugador, this.tablero);

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