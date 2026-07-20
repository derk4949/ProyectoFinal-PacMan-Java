public class Punto {
    private int fila;
    private int columna;
    private int valor;
    private boolean recolectado;

    public Punto(int fila, int columna, int valor){
        this.fila        = fila;
        this.columna     = columna;
        this.valor       = valor;
        this.recolectado = false;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int obtenerValor() {
        return valor;
    }

    public boolean fueRecolectado() {
        return recolectado;
    }

    public void marcarComoRecolectado() {
        recolectado = true;
    }
}

