// Enemigo que intenta acercarse al jugador
public class Enemigo1 extends Enemigo {

    public Enemigo1(int fila, int columna) {
        super("Perseguidor", fila, columna);
    }

    @Override
    public void mover(Tablero tablero, Jugador jugador) {

        if (tablero == null || jugador == null || !estaActivo()) {
            return;
        }

        // Evita conservar la posicion anterior de otro turno
        reiniciarPosicion(getFila(), getColumna());

        // Salir de la base cuenta como el movimiento del turno
        if (intentarSalirDeBase(tablero)) {
            return;
        }

        // --- NUEVO: Lógica de persecución principal ---
        int diferenciaFila = jugador.getFila() - getFila();
        int diferenciaColumna = jugador.getColumna() - getColumna();

        // Ya se encuentra en la misma posicion que el jugador
        if (diferenciaFila == 0 && diferenciaColumna == 0) {
            return;
        }

        int pasoFila = 0;
        int pasoColumna = 0;

        if (diferenciaFila < 0) {
            pasoFila = -1;
        } else if (diferenciaFila > 0) {
            pasoFila = 1;
        }

        if (diferenciaColumna < 0) {
            pasoColumna = -1;
        } else if (diferenciaColumna > 0) {
            pasoColumna = 1;
        }

        // Primero intenta el eje con mayor distancia
        if (Math.abs(diferenciaFila) >= Math.abs(diferenciaColumna)) {
            if (pasoFila != 0 && intentarMover(getFila() + pasoFila, getColumna(), tablero)) {
                return;
            }
            if (pasoColumna != 0 && intentarMover(getFila(), getColumna() + pasoColumna, tablero)) {
                return;
            }
        } else {
            if (pasoColumna != 0 && intentarMover(getFila(), getColumna() + pasoColumna, tablero)) {
                return;
            }
            if (pasoFila != 0 && intentarMover(getFila() + pasoFila, getColumna(), tablero)) {
                return;
            }
        }
    }
}