public class Punto {
    public int fila;
    public int columna;
    public int valor;
    public boolean recolectado;

    public Punto(int fila, int columna, int valor){

        this.fila        = fila;
        this.columna     = columna;
        this.valor       = 10;
        this.recolectado = false;
    }

    public int obtenerValor() {

        return valor;
    }


    public boolean fueRecolectado() {

        return recolectado;
    }



}

