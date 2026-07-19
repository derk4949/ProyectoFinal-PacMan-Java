public class Muro {

    // Posicion del muro dentro del tablero
    private int fila;
    private int columna;

    // Constructor --- debe coincidir exactamente con el usado en Tablero.java
    public Muro(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    // Getter de la fila
    public int getFila() {
        return fila;
    }

    // Getter de la columna
    public int getColumna() {
        return columna;
    }

    // Devuelve la posicion del muro como un arreglo [fila, columna]
    public int[] obtenerPosicion() {
        return new int[]{fila, columna};
    }
}
