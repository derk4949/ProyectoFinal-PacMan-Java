import java.util.Scanner;
public class Juego {
    public Jugador jugador;
    public Tablero tablero;
    public Control_de_Enemigos control_de_Enemigos;
    public boolean juegoTerminado;
    public Scanner scanner;
    public int turno;


    public Juego() {
        this.juegoTerminado = false;
        this.scanner = new Scanner(System.in);

    }


    public void iniciarJuego() {
        mostrarMenu();
        String entrada = scanner.nextLine().trim();
        if (entrada.equals("2")) {
            System.out.println("Hasta luego!");
            return;
        }

        while(!juegoTerminado) {
            actualizarTablero();
            mostrarEstado();
            ejecutarTurno();
            verificarFinJuego();
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