import java.util.Scanner;

public class Jugador {

    //1. ATRIBUTOS
    private String nombre;
    private int fila;
    private int columna;
    private int puntaje;
    private int salud;
    private int velocidad;
    private boolean poderActivo;


    // 2. CONSTRUCTOR
    public Jugador(String nombre, int fila, int columnna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columnna;
        this.puntaje = 0;
        this.salud = 3;
        this.velocidad = 1;
        this.poderActivo = false;
    }

    // 3. GETTERS (todos, para que la clase Juego pueda leer el estado)

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getSalud() {
        return salud;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean isPoderActivo() {
        return poderActivo;
    }

    // 4. SETTERS (solo los necesarios para que Juego controle posición/estado especial)

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumnna(int columnna) {
        this.columna = columnna;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setPoderActivo(boolean poderActivo) {
        this.poderActivo = poderActivo;
    }

    // 5. MÉTODOS

    public void mover(String direccion, Tablero tablero) {
        int nuevaFila = this.fila;
        int nuevaColumna = this.columna;

        switch (direccion.toUpperCase()) {
            case "W":
                nuevaFila -= this.velocidad;
                break;
            case "S":
                nuevaFila += this.velocidad;
                break;
            case "A":
                nuevaColumna -= this.velocidad;
                break;
            case "D":
                nuevaColumna += this.velocidad;
                break;
            default:
                System.out.println("Tecla incorrecta. Usa W, A, S o D.");
                break;
        }

        if (tablero.esMovimientoValido(nuevaFila, nuevaColumna)) {
            tablero.limpiarPosicion(this.fila, this.columna);
            this.fila = nuevaFila;
            this.columna = nuevaColumna;
        }

    }

    public void recogerPunto() {
        this.puntaje = puntaje + 10;
    }

    public void recibirDanio() {
        this.salud = salud - 1;
    }

    public void usarPoder() {
        this.poderActivo = true;
    }

    public boolean estaVivo() {
        if (this.salud > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarEstado() {
        System.out.println("\n--- ESTADO DE: " + this.nombre + " ---");
        System.out.println("Posición: [" + this.fila + ", " + this.columna + "]");
        System.out.println("Puntaje: " + this.puntaje);
        System.out.println("Vidas: " + this.salud);
        System.out.println("¿Tiene Poder?: " + (this.poderActivo ? "Sí" : "No"));
        System.out.println("¿Está vivo?:" + (this.estaVivo() ? "Si" : "No, GAME OVER"));
        System.out.println("-----------------------------\n");
    }
}