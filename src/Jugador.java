// Clase Jugador: representa al personaje principal que controla la persona
// que juega. Se encarga de su posicion, sus vidas, su puntaje, su velocidad
// y el poder temporal que pueda tener activo en un momento dado.
public class Jugador {

    // ------- Identidad y posicion -------
    private String nombre;
    private int fila;
    private int columna;
    private int filaAnterior;
    private int columnaAnterior;

    // ------- Vidas y puntaje -------
    private int vidas;
    private int puntaje;

    // ------- Velocidad y poder -------
    private int velocidad;
    private boolean poderActivo;
    private String tipoPoderActivo;
    private int turnosPoderActivo;

    // CONSTRUCTOR
    // Crea al jugador en una posicion inicial, con 3 vidas, puntaje en 0,
    // velocidad normal y sin ningun poder activo.
    public Jugador(String nombre, int fila, int columna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columna = columna;

        // Al iniciar, la posicion anterior es la misma que la actual,
        // porque el jugador todavia no se ha movido.
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
    // Calcula la nueva posicion segun la direccion, y solo la aplica si
    // Tablero confirma que esa posicion es valida
    public boolean mover(String direccion, Tablero tablero) {

        if (direccion == null || tablero == null) {
            return false;
        }

        // Empezamos suponiendo que la posicion no cambia; si la direccion
        // es valida, ajustamos fila o columna mas abajo
        int nuevaFila = fila;
        int nuevaColumna = columna;

        String direccionMayuscula = direccion.toUpperCase();

        switch (direccionMayuscula) {
            case "W":
                nuevaFila = fila - 1;
                break;
            case "S":
                nuevaFila = fila + 1;
                break;
            case "A":
                nuevaColumna = columna - 1;
                break;
            case "D":
                nuevaColumna = columna + 1;
                break;
            default:
                // Cualquier letra distinta de W, S, A o D es invalida.
                return false;
        }

        if (!tablero.esMovimientoValido(nuevaFila, nuevaColumna)) {
            return false;
        }

        // El movimiento es valido: guardamos de donde salio el jugador
        // y luego actualizamos su posicion actual
        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;

        return true;
    }

    // PUNTOS
    // Suma el valor del punto al puntaje, siempre que el punto exista
    // y no haya sido recolectado antes
    public void recogerPunto(Punto punto) {

        if (punto == null) {
            return;
        }

        if (punto.fueRecolectado()) {
            return;
        }

        puntaje = puntaje + punto.obtenerValor();
        punto.marcarComoRecolectado();
    }

    // VIDAS
    // Quita una vida, sin dejar que el contador baje de 0.
    public void perderVida() {
        vidas = vidas - 1;

        if (vidas < 0) {
            vidas = 0;
        }

        System.out.println(nombre + " perdio una vida. Vidas restantes: " + vidas);
    }

    // Recupera una vida, sin dejar que supere el maximo de 3.
    public void recuperarVida() {

        if (vidas >= 3) {
            System.out.println(nombre + " ya tiene el maximo de vidas.");
            return;
        }

        vidas = vidas + 1;
        System.out.println(nombre + " recupero una vida. Vidas actuales: " + vidas);
    }

    public boolean estaVivo() {
        return vidas > 0;
    }

    // PODERES
    // Activa el efecto de un poder que el jugador recogio del tablero
    public void usarPoder(Poder poder) {

        if (poder == null) {
            return;
        }

        if (poder.fueRecogido()) {
            return;
        }

        poder.activar(this);
        poder.marcarComoRecogido();
    }

    // Guarda el poder temporal que queda activo en el jugador
    // Si ya habia un poder activo, primero se
    // desactiva correctamente para no dejar efectos mezclados
    public void activarPoder(String tipo, int turnos) {

        if (tipo == null || turnos <= 0) {
            return;
        }

        if (poderActivo) {
            desactivarPoderActual();
        }

        poderActivo = true;
        tipoPoderActivo = tipo;
        turnosPoderActivo = turnos;
    }

    // Se llama una vez por turno. Va restando turnos al poder activo hasta
    // que se acaba, momento en el que se desactiva automaticamente
    public void actualizarContadorPoder() {

        if (!poderActivo) {
            return;
        }

        turnosPoderActivo = turnosPoderActivo - 1;

        if (turnosPoderActivo <= 0) {
            desactivarPoderActual();
        }
    }

    // Quita el efecto del poder activo. Si el poder era "Velocidad"
    // regresa la velocidad a su valor normal "1"
    private void desactivarPoderActual() {

        if (tipoPoderActivo.equals("Velocidad")) {
            velocidad = 1;
        }

        poderActivo = false;
        tipoPoderActivo = "Ninguno";
        turnosPoderActivo = 0;
    }

    // TELETRANSPORTE
    // Mueve al jugador directamente a una posicion, sin pasar por
    // esMovimientoValido(). La validacion de seguridad de esa posicion
    // la hara mas adelante ControlEnemigos o Juego.
    public void teletransportar(int nuevaFila, int nuevaColumna) {
        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;
    }

    // ESTADO
    public void mostrarEstado() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Vidas: " + vidas);
        System.out.println("Puntaje: " + puntaje);
        System.out.println("Velocidad: " + velocidad);

        if (poderActivo) {
            System.out.println("Poder activo: " + tipoPoderActivo
                    + " (turnos restantes: " + turnosPoderActivo + ")");
        }
    }

    // GETTERS

    public String getNombre() {
        return nombre;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getFilaAnterior() {
        return filaAnterior;
    }

    public int getColumnaAnterior() {
        return columnaAnterior;
    }

    public int getVidas() {
        return vidas;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean tienePoderActivo() {
        return poderActivo;
    }

    public String getTipoPoderActivo() {
        return tipoPoderActivo;
    }

    public int getTurnosPoderActivo() {
        return turnosPoderActivo;
    }

    // SETTER
    // La velocidad nunca puede ser 0 o negativa; en ese caso se guarda 1
    public void setVelocidad(int velocidad) {
        if (velocidad <= 0) {
            velocidad = 1;
        }
        this.velocidad = velocidad;
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