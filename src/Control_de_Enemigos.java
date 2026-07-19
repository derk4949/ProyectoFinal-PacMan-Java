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

    // GETTER
    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}