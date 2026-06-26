import java.util.Scanner;

public class Jugador {

    // ATRIBUTOS
    private String nombre;
    private int fila;
    private int columnna;
    private int puntaje;
    private int salud;
    private int velocidad;
    private boolean poderActivo;


    Scanner lector=new Scanner(System.in);
    //ATRIBUTOS CONSTRUCTOR
    public Jugador(String nombre,int fila, int columnna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columnna = columnna;
        this.puntaje = 0;
        this.salud = 3;
        this.velocidad = 1;
        this.poderActivo = false;
    }
    //METODOS
    public void mover() {
    }
    public void recogerPunto(){

    }
    public void recibirDano() {

    }
    public void usarPoder() {

    }
    public void estaVivo () {

    }
    public void mostrarEstado() {


    }

    public static void main(String[]args) {
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingresa tu nombre de usuario para Pac-Man: ");
        String nombreUsuario = lector.nextLine();
        Jugador jugadorUnico = new Jugador(nombreUsuario, 5, 5);
        jugadorUnico.mostrarEstado();
    }
}