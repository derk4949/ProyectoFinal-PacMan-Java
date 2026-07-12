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
        jugador.setVelocidad(jugador.getVelocidad() + 1);
        jugador.setPoderActivo(true);
    }

    // Revierte el efecto cuando el poder termina
    public void desactivar(Jugador jugador) {
        jugador.setVelocidad(jugador.getVelocidad() - 1);
        jugador.setPoderActivo(false);
    }

    public String descripcion() {
        return "Poder: " + tipo +  "\nEfecto: Aumenta la velocidad del jugador." +
                "\nDuración: " + duracion + "Segundos//turnos.";
    }
}