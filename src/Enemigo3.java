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

        // Elige una de las cuatro direcciones posibles.
        int direccion = random.nextInt(4);

        // Prueba cada direccion hasta encontrar un movimiento valido.
        for (int intento = 0; intento < 4; intento++) {

            int nuevaFila = getFila();
            int nuevaColumna = getColumna();

            switch (direccion) {

                case 0:
                    // Arriba
                    nuevaFila = getFila() - 1;
                    if (nuevaFila == 0) {
                        nuevaFila = tablero.getFilas() - 2;
                    }
                    break;

                case 1:
                    // Abajo
                    nuevaFila = getFila() + 1;
                    if (nuevaFila == tablero.getFilas() - 1) {
                        nuevaFila = 1;
                    }
                    break;

                case 2:
                    // Izquierda
                    nuevaColumna = getColumna() - 1;
                    if (nuevaColumna == 0) {
                        nuevaColumna = tablero.getColumnas() - 2;
                    }
                    break;

                case 3:
                    // Derecha
                    nuevaColumna = getColumna() + 1;
                    if (nuevaColumna == tablero.getColumnas() - 1) {
                        nuevaColumna = 1;
                    }
                    break;
            }

            // El fantasma puede avanzar aunque la casilla tenga un muro.
            if (intentarMoverIgnorandoMuros(nuevaFila, nuevaColumna, tablero)) {
                return;
            }

            // Si no puede avanzar, prueba la siguiente direccion.
            direccion++;

            if (direccion == 4) {
                direccion = 0;
            }
        }// Si ninguna de las 4 direcciones fue valida, el Fantasma permanece en su posicion actual durante este turno.
    }
}
