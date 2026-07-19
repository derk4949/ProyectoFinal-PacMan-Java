// Poder1: representa el poder de velocidad. Hereda de Poder todos los
// atributos comunes
public class Poder1 extends Poder {

    // Constructor: solo recibe la posicion. El tipo y la duracion de este
    // poder siempre son los mismos, asi que se los pasamos fijos a Poder
    public Poder1(int fila, int columna) {
        super("Velocidad", 5, fila, columna);
    }

    // aplica el efecto de velocidad sobre el jugador
    @Override
    public void activar(Jugador jugador) {

        if (jugador == null) {
            return;
        }

        // Primero se activa el poder temporal: esto desactiva correctamente
        // cualquier poder anterior que el jugador tuviera activo
        jugador.activarPoder("Velocidad", getDuracion());

        // Despues se aumenta la velocidad del jugador
        jugador.setVelocidad(2);

        System.out.println("¡Poder de velocidad activado durante " + getDuracion() + " turnos!");
    }

    // Descripcion especifica de este poder
    @Override
    public String descripcion() {
        return "Aumenta la velocidad del jugador a 2 movimientos durante "
                + getDuracion() + " turnos.";
    }
}