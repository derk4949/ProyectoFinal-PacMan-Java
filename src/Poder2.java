public class Poder2 {

    // ATRIBUTOS
    private String tipo;
    private int duracion;

    // CONSTRUCTOR
    public Poder2(int duracion) {
        this.tipo = "Velocidad aumentada";
        this.duracion = duracion;
    }

    // Recibe al objeto jugador para poder modificarlo
    public void activar(Jugador jugador) {
        jugador.velocidad = jugador.velocidad + 1;
        jugador.poderActivo = true;
    }

    public String descripcion() {
        return "Poder: " + tipo +  "\nEfecto: Aumenta la velocidad del jugador." +
                "\nDuración: " + duracion + "Segundos//turnos.";
    }
}