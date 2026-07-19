import java.util.Random;

// Representa al enemigo Fantasma.
public class Enemigo3 extends Enemigo {

    // Se usa para elegir una direccion aleatoria.
    private Random random;

    // Crea al fantasma en una posicion inicial.
    public Enemigo3(int fila, int columna) {
        super("Fantasma", fila, columna);
        random = new Random();
    }

    // Prepara el movimiento del fantasma en cada turno.
    @Override
    public void mover(Tablero tablero, Jugador jugador) {

        // Sin tablero no se puede calcular el movimiento.
        if (tablero == null) {
            return;
        }

        // El jugador es necesario para revisar la colision.
        if (jugador == null) {
            return;
        }

        // Un enemigo inactivo no debe moverse.
        if (!estaActivo()) {
            return;
        }

        // Guarda la posicion antes de realizar el movimiento.
        reiniciarPosicion(getFila(), getColumna());

        // Si ya esta sobre el jugador, espera a que se procese la colision.
        if (getFila() == jugador.getFila()
                && getColumna() == jugador.getColumna()) {
            return;
        }
    }
}
