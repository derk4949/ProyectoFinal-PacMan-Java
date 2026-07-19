public class Poder3 extends Poder {

    public Poder3(int fila, int columna) {
        super("Vida extra", 0, fila, columna);
    }

    @Override
    public void activar(Jugador jugador) {

        if (jugador == null) {
            return;
        }

        System.out.println("El poder Vida extra fue activado.");

        jugador.recuperarVida();
    }

    @Override
    public String descripcion() {
        return "Vida extra: recupera una vida sin superar el maximo de 3.";
    }
}
