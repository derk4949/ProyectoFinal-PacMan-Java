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

    //aqui se jalara de las clases hijas el comportamiendo de cada enemigo
    public void mover(Tablero tablero, Jugador jugador) {
        System.out.println("El enemigo debe definir su movimiento");
    }

    //protegida para que solo las clases hijas puedaan usarlo
    protected boolean intentarMoverIgnorandoMuros(int nuevaFila, int nuevaColumna, Tablero tablero) {

        if (tablero == null) {
            return false;
        }

        if (!activo) {
            return false;
        }

        if (!tablero.esMovimientoValidoFantasma(nuevaFila, nuevaColumna)) {
            return false;
        }

        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;

        return true;
    }

    protected boolean intentarMover(int nuevaFila, int nuevaColumna, Tablero tablero) {

        if (tablero == null || !activo) {
            return false;
        }
        boolean estaEnBase = tablero.esPosicionBase(fila, columna);
        boolean estaEnSalida = tablero.esPosicionSalidaBase(fila, columna);
        boolean destinoEsBase = tablero.esPosicionBase(nuevaFila, nuevaColumna);
        boolean destinoEsSalida = tablero.esPosicionSalidaBase(nuevaFila, nuevaColumna);

        if (!estaEnBase && !estaEnSalida && (destinoEsBase || destinoEsSalida)) {
            return false;
        }

        if (!tablero.esMovimientoValidoEnemigo(nuevaFila, nuevaColumna)) {
            return false;
        }

        filaAnterior = fila;
        columnaAnterior = columna;
        fila = nuevaFila;
        columna = nuevaColumna;

        return true;
    }

    protected boolean intentarSalirDeBase(Tablero tablero) {

        if (tablero == null || !activo) {
            return false;
        }


        // para dejar salir al fantasma salida
        if (tablero.esPosicionBase(fila, columna)) {
            int[] salida = tablero.obtenerPosicionSalidaBase();
            return intentarMover(salida[0], salida[1], tablero);
        }

        // si esta en la salida se mueve alfrente pa dejar libre la salida
        if (tablero.esPosicionSalidaBase(fila, columna)) {
            int[] frente = tablero.obtenerPosicionFrenteSalidaBase();
            return intentarMover(frente[0], frente[1], tablero);
        }


        // no esta en ninguno
        return false;
    }

    //COlisiones
    //detecta tanto en la misma celda o por intercambio en un turno
    public boolean verificarColision(Jugador jugador) {

        if (jugador == null) {
            return false;
        }

        if (!activo) {
            return false;
        }

        boolean mismaPosicion = (fila == jugador.getFila()) && (columna == jugador.getColumna());

        boolean intercambioDePosiciones =
                (fila == jugador.getFilaAnterior())
                        && (columna == jugador.getColumnaAnterior())
                        && (filaAnterior == jugador.getFila())
                        && (columnaAnterior == jugador.getColumna());

        return mismaPosicion || intercambioDePosiciones;
    }

    // ATAQUE
    // Si hay colision con el jugador, le quita exactamente una vida.
    public void atacar(Jugador jugador) {

        if (verificarColision(jugador)) {
            jugador.perderVida();
        }
    }

    // REINICIO DE POSICION
    // Coloca al enemigo en una posicion nueva y deja la posicion
    // anterior igual a la actual, para no generar una falsa colision
    // por intercambio en el siguiente turno.
    public void reiniciarPosicion(int nuevaFila, int nuevaColumna) {
        fila = nuevaFila;
        columna = nuevaColumna;
        filaAnterior = nuevaFila;
        columnaAnterior = nuevaColumna;
    }

    // ACTIVACION
    public void activar() {
        activo = true;
    }

    public void desactivar() {
        activo = false;
    }

    // ESTADO
    public void mostrarEstado() {
        System.out.println("Tipo: " + tipo);
        System.out.println("Posicion: (" + fila + ", " + columna + ")");
        System.out.println("Daño: " + danio);

        if (activo) {
            System.out.println("Estado: activo");
        } else {
            System.out.println("Estado: inactivo");
        }
    }

    // GETTERS
    public String getTipo() {
        return tipo;
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

    public int getDanio() {
        return danio;
    }

    public boolean estaActivo() {
        return activo;
    }
}

