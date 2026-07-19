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

    public void recolectar() {
        this.recolectado = true;
    }

    public void mostrarEstado() {
        System.out.println("Punto en posición: [" + fila + "," + columna + "]");
        System.out.println("Valor: " + valor
                + " - ¿Recolectado?: " + (recolectado ? "Sí" : "No"));
    }
}

