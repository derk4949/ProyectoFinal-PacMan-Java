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
            if (filaT > 5 && columnaT > 5) {
                this.tablero = new Tablero(filaT,columnaT,filaT+5,5,0);
                this.tablero.generarTablero();
            }
            else{
                System.out.println("Error tamaño de tablero invalido porfavor introduce un valor mayor a 5 ");
            }


            int []posicionLibre = this.tablero.obtenerPosicionLibreAleatoria();
            int filaFantasma = posicionLibre[0];
            int colFantasma = posicionLibre[1];
            Enemigo2 aleatorio = new Enemigo2(filaFantasma,colFantasma);
            this.tablero.colocarEnemigos2(aleatorio);

            this.jugador = new Jugador("leonardo",2,2);
            this.tablero.colocarJugador(jugador);
            this.tablero.mostrarTablero();
            lector.nextInt();

            while(!juegoTerminado) {

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
        tablero.generarTablero();
        tablero.colocarJugador(jugador);

    }
    public void ejecutarTurno() {
        Scanner lector = new Scanner(System.in);
        System.out.println("APRETA TECLA");
        String direccionUsuario = lector.nextLine();
        jugador.mover(direccionUsuario);



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