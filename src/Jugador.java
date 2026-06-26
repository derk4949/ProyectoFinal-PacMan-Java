import java.util.Scanner;

public class Jugador {
    public String nombre;
    public int fila;
    public int columnna;
    public int puntaje;
    public int salud;
    public int velocidad;
    public boolean poderActivo;

    //Nombre
    public Jugador(String nombreInicial) {
        this.nombre = nombreInicial;
    }
    //Posicion
    public Jugador(int filaInicial, int columnaInicial) {
        this.fila = filaInicial;
        this.columnna = columnaInicial;
    }
    //Puntaje y velocidad
    public Jugador (int puntaje,int salud,int velocidad){
        this.puntaje=puntaje;
        this.salud=salud;
        this.velocidad = velocidad;

    }
    //Poder con velocidad
    public Jugador (boolean poderActivo,int velocidad) {
        this.poderActivo=poderActivo;
        this.velocidad = velocidad;

    }

    public void mover() {
    }

    public void recogerPunto() {
        this.puntaje++;
    }

    public void recibirDano() {
        this.salud -= 20;
    }

    public void usarPoder() {
    }

    public void estaVivo() {
    }

    public void mostrarEstado() {
        System.out.println("--- ESTADO DE: " + this.nombre + " ---");
        System.out.println("Vida: " + this.salud + "%");
        System.out.println("Puntaje: " + this.puntaje);
        System.out.println("Posición: [" + this.fila + ", " + this.columnna + "]");
        System.out.println("-------------------------");
    }
    public static void main(String[]args){

    }
}