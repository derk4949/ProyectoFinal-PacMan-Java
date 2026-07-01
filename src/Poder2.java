public class Poder2 {

    // ATRIBUTOS
    private String tipo;
    private int duracion;

    // CONSTRUCTOR
    public Poder2(int duracion) {
        this.tipo = "Velocidad aumentada";
        this.duracion = duracion;
    }

    // ACTIVA EL PODER
    public void activar(Jugador jugador) {
        jugador.velocidad = jugador.velocidad + 1;
        jugador.poderActivo = true;
    }

    // DEVUELVE LA DESCRIPCIÓN DEL PODER
    public String descripcion() {
        return "Poder: " + tipo +
                "\nEfecto: Aumenta la velocidad del jugador." +
                "\nDuración: " + duracion + " turnos.";
    }
}