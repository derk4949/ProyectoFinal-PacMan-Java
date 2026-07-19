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

        // La forma de persecusion se agregara en el siguiente commit
    }
}