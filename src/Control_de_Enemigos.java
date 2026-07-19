// Clase ControlEnemigos: se encarga de administrar a TODOS los enemigos del juego como grupo.
public class ControlEnemigos {

    // Arreglo de enemigos controlados. Es el MISMO arreglo que tiene Tablero.
    private Enemigo[] enemigos;

    // Jugador contra el que se revisan las colisiones.
    private Jugador jugador;

    // CONSTRUCTOR
    public ControlEnemigos(Enemigo[] enemigos, Jugador jugador) {
        if (enemigos == null) {
            this.enemigos = new Enemigo[0];
        } else {
            this.enemigos = enemigos;
        }
        this.jugador = jugador;
    }

    // CONGELACION
    private boolean enemigosCongelados() {
        return jugador != null
                && jugador.tienePoderActivo()
                && "Congelacion".equals(jugador.getTipoPoderActivo());
    }

    // POSICIONES OCUPADAS
    private boolean posicionOcupadaPorOtro(int indiceActual, int fila, int columna) {
        for (int i = 0; i < enemigos.length; i++) {
            Enemigo otro = enemigos[i];

            if (otro == null) continue;
            if (i == indiceActual) continue;
            if (!otro.estaActivo()) continue;

            if (otro.getFila() == fila && otro.getColumna() == columna) {
                return true;
            }
        }
        return false;
    }
    // CONTROL DE LA SALIDA
    // Revisa si otro enemigo (activo y con indice distinto) esta parado
    // exactamente en la casilla de salida de la base.
    private boolean salidaBaseOcupadaPorOtro(int indiceActual, Tablero tablero) {
        int[] salida = tablero.obtenerPosicionSalidaBase();

        for (int i = 0; i < enemigos.length; i++) {
            if (i == indiceActual) continue;

            Enemigo otro = enemigos[i];
            if (otro == null || !otro.estaActivo()) continue;

            if (otro.getFila() == salida[0] && otro.getColumna() == salida[1]) {
                return true;
            }
        }
        return false;
    }

    // MOVIMIENTO DE LOS ENEMIGOS
    public void moverEnemigos(Tablero tablero) {
        if (tablero == null || jugador == null) {
            return;
        }

        // Si el jugador tiene el poder de congelacion activo...ningun
        // enemigo se mueve este turno
        if (enemigosCongelados()) {
            for (int i = 0; i < enemigos.length; i++) {
                Enemigo enemigo = enemigos[i];
                if (enemigo != null && enemigo.estaActivo()) {
                    enemigo.reiniciarPosicion(enemigo.getFila(), enemigo.getColumna());
                }
            }
            System.out.println("Los enemigos estan congelados.");
            return;
        }

        // Movimiento normal
        for (int i = 0; i < enemigos.length; i++) {
            Enemigo enemigo = enemigos[i];
            if (enemigo == null || !enemigo.estaActivo()) {
                continue;
            }

            // Los enemigos normales dentro de la base esperan si otro
            // enemigo ya ocupa la salida el Fantasma no usa la puerta.
            if (tablero.esPosicionBase(enemigo.getFila(), enemigo.getColumna())
                    && !"Fantasma".equals(enemigo.getTipo())
                    && salidaBaseOcupadaPorOtro(i, tablero)) {

                enemigo.reiniciarPosicion(enemigo.getFila(), enemigo.getColumna());
                continue;
            }

            // guardamos de donde partio, por si hay que devolverlo.
            int filaOrigen = enemigo.getFila();
            int columnaOrigen = enemigo.getColumna();

            enemigo.mover(tablero, jugador);

            // si otro enemigo ya esta en la casilla a la que se movio,
            // este enemigo regresa a su posicion de origen
            if (posicionOcupadaPorOtro(i, enemigo.getFila(), enemigo.getColumna())) {
                enemigo.reiniciarPosicion(filaOrigen, columnaOrigen);
            }
        }
    }

    // GENERAR MOVIMIENTOS
    public void generarMovimientos(Tablero tablero) {
        moverEnemigos(tablero);
    }

    // GETTER
    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}