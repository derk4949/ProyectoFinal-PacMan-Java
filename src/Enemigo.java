public class Enemigo {

    private String tipo;
    private int fila;
    private int columna;
    private int filaAnterior;
    private int columnaAnterior;

    // estado
    private int danio;
    private boolean activo;

    public Enemigo(String tipo, int fila, int columna) {

        if (tipo == null || tipo.trim().isEmpty()) {
            tipo = "Enemigo";
        }

        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;

        // Al iniciar, la posicion anterior es la misma que la actual.
        this.filaAnterior = fila;
        this.columnaAnterior = columna;

        this.danio = 1;
        this.activo = true;
    }




}


