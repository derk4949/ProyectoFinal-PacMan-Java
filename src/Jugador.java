// Clase Jugador: representa al personaje principal que controla la persona
// que juega. Se encarga de su posicion, sus vidas, su puntaje, su velocidad
// y el poder temporal que pueda tener activo en un momento dado
public class Jugador {

    // Identidad y posicion
    private String nombre;
    private int fila;
    private int columna;
    private int filaAnterior;
    private int columnaAnterior;

    // Vida y puntaje
    private int vidas;
    private int puntaje;

    // velocidad y poder
    private int velocidad;
    private boolean poderActivo;
    private String tipoPoderActivo;
    private int turnosPoderActivo;

    // CONSTRUCTOR
    public Jugador(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;

        this.filaAnterior = fila;
        this.columnaAnterior = columna;

        this.vidas = 3;
        this.puntaje = 0;
        this.velocidad = 1;

        this.poderActivo = false;
        this.tipoPoderActivo = "Ninguno";
        this.turnosPoderActivo = 0;
    }

    // MOVIMIENTO
    public boolean mover(String direccion, Tablero tablero) {
        if (direccion == null || tablero == null) {
            return false;
        }

        int nuevaFila = fila;
        int nuevaColumna = columna;
        String direccionMayuscula = direccion.toUpperCase();

        switch (direccionMayuscula) {
            case "W": nuevaFila = fila - 1; break;
            case "S": nuevaFila = fila + 1; break;
            case "A": nuevaColumna = columna - 1; break;
            case "D": nuevaColumna = columna + 1; break;
            default: return false;
        }

        if (!tablero.esMovimientoValido(nuevaFila, nuevaColumna)) {
            return false;
        }

        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;

        return true;
    }


    public void teletransportar(int nuevaFila, int nuevaColumna) {
        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;
    }

    // GETTERS BÁSICOS DE ESTE PASO
    public String getNombre() { return nombre; }
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public int getFilaAnterior() { return filaAnterior; }
    public int getColumnaAnterior() { return columnaAnterior; }
}