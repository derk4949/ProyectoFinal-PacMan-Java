import java.util.Scanner;

public class Jugador {

    // Atributos
    private String nombre;
    private int fila;
    private int columnna;
    private int puntaje;
    private int salud;
    private int velocidad;
    private boolean poderActivo;


    Scanner lector=new Scanner(System.in);
    //Nombre
    public Jugador(String nombre,int fila, int columnna,int puntaje, int salud, int velocidad,boolean poderActivo) {
        this.nombre = nombre;
        this.fila = fila;
        this.columnna = columnna;
        this.puntaje = 0;
        this.salud = 3;
        this.velocidad = 1;
        this.poderActivo = false;
    }
    public void mover(String direccion) {

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
}