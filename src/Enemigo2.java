import java.util.Random;

// Enemigo que se mueve en una direccion aleatoria
public class Enemigo2 extends Enemigo {

    private Random random;

    public Enemigo2(int fila, int columna) {
        super("Aleatorio", fila, columna);
        random = new Random();
    }

    @Override
    public void mover(Tablero tablero, Jugador jugador) {

        if (tablero == null || jugador == null || !estaActivo()) {
            return;
        }

        // Evita conservar la posicion anterior de otro turno
        reiniciarPosicion(getFila(), getColumna());

        // Si ya coincide con el jugador, permanece en la casilla
        // para que posteriormente se detecte la colision
        if (getFila() == jugador.getFila()
                && getColumna() == jugador.getColumna()) {
            return;
        }

        // Salir de la base cuenta como el movimiento del turno
        if (intentarSalirDeBase(tablero)) {
            return;
        }

        // 0 arriba, 1 abajo, 2 izquierda, 3 derecha
        int direccion = random.nextInt(4);

        for (int intento = 0; intento < 4; intento++) {

            int nuevaFila = getFila();
            int nuevaColumna = getColumna();

            switch (direccion) {

                case 0:
                    nuevaFila = getFila() - 1;
                    break;

                case 1:
                    nuevaFila = getFila() + 1;
                    break;

                case 2:
                    nuevaColumna = getColumna() - 1;
                    break;

                case 3:
                    nuevaColumna = getColumna() + 1;
                    break;
            }

            if (intentarMover(nuevaFila, nuevaColumna, tablero)) {
                return;
            }

            // Prueba la siguiente direccion
            direccion++;

            if (direccion == 4) {
                direccion = 0;
            }
        }

        // Si las cuatro direcciones estan bloqueadas,
        // permanece en su posicion
    }
}
