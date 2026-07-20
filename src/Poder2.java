// Poder2: representa el poder de congelacion. Hereda de Poder todos los
// atributos comunes : tipo, duracion, fila, columna, recogido
public class Poder2 extends Poder {

    // Constructor: solo recibe la posicion, el tipo y la duracion de este
    // poder siempre son los mismos, asi que se los pasamos fijos a poder
    public Poder2(int fila, int columna) {
        super("Congelacion", 3, fila, columna);
    }

    // Activa el estado de congelacion en el jugador. No toca a los
    // enemigos directamente: eso lo decidira Juego o ControlEnemigos
    // consultando el estado del jugador.
    @Override
    public void activar(Jugador jugador) {

        if (jugador == null) {
            return;
        }

        jugador.activarPoder("Congelacion", getDuracion());

        System.out.println("¡Poder de congelacion activado durante " + getDuracion() + " turnos!");
    }

    // Descripcion especifica de este poder.
    @Override
    public String descripcion() {
        return "Congela a todos los enemigos durante "
                + getDuracion() + " turnos.";
    }
}