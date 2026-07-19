// Poder1: representa el poder de velocidad. Hereda de Poder todos los
// atributos comunes (tipo, duracion, fila, columna, recogido).
public class Poder1 extends Poder {

    // Constructor: solo recibe la posicion. El tipo y la duracion de este
    // poder siempre son los mismos, asi que se los pasamos fijos a Poder.
    public Poder1(int fila, int columna) {
        super("Velocidad", 5, fila, columna);
    }

    // Los métodos activadores y la descripción se añadirán en el siguiente commit
}