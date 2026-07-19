import java.util.Random;
import java.util.Scanner;
public class Juego {
    private Jugador jugador;
    private Tablero tablero;
    private Enemigo[] enemigos;
    private boolean juegoTerminado;
    private ControlEnemigos controlEnemigos;
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
        jugador = null;
        tablero = null;
        enemigos = new Enemigo[0];
        controlEnemigos = null;
        juegoTerminado = false;
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



        System.out.println();
        System.out.println("------------------------");
        System.out.println("       PAC-MAN"          );
        System.out.println("------------------------");
        System.out.println("1. Iniciar juego");
        System.out.println("2. Ver instrucciones");
        System.out.println("3. Salir");




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

    public void configurarNuevaPartida() {

    }

    private void mostrarInstrucciones() {
        System.out.println();
        System.out.println("CONTROLES");
        System.out.println("W = arriba");
        System.out.println("S = abajo");
        System.out.println("A = izquierda");
        System.out.println("D = derecha ");
        System.out.println("Q = abandonar la partida ");

        System.out.println();
        System.out.println("OBJETIVO");
        System.out.println("- Recoger todos los puntos");
        System.out.println("- Evitar a los enemigos.");
        System.out.println("-  El jugador comienza con 3 vidas");
        System.out.println("- Cada colision quita exactamente una vida");
        System.out.println("- Se gana al recoger todos los puntos");
        System.out.println("- Se pierde al quedarse sin vidas");

        System.out.println();
        System.out.println("PODERES");
        System.out.println("- Velocidad: permite dos movimientos por turno temporalmente");
        System.out.println("- Congelacion: evita que los enemigos se muevan temporalmente");
        System.out.println("- Vida extra: recupera una vida sin superar el maximo de 3");
    }


    public void programaActivo() {

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