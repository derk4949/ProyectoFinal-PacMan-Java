public class Poder {

    private String tipo;
    private int duracion;
    private int fila;
    private int columna;

    private boolean recogido;


    public Poder(String tipo, int duracion, int fila, int columna) {

        this.tipo = tipo;

        if (duracion < 0) {
            duracion = 0;
        }
        this.duracion = duracion;

        this.fila = fila;
        this.columna = columna;
        this.recogido = false;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracion() {
        return duracion;
    }

    public boolean fueRecogido() {
        return recogido;
    }

    public void marcarComoRecogido() {
        recogido = true;
    }


    public void activar(Jugador jugador) {
        System.out.println("El poder debe definir su efecto.");
    }

    public String descripcion() {
        return "Poder especial: " + tipo;
    }
}

