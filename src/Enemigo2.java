import java.util.Random;

public class Enemigo2 {
    public String tipo;
    public int fila;
    public int columna;
    public int danio;
    public boolean activo;
    public Random random;


    public Enemigo2(int fila, int columna) {
        this.tipo    = "Aleatorio";
        this.fila    = fila;
        this.columna = columna;
        this.danio   = 1;
        this.activo  = true;
        this.random  = new Random();
    }

    public void mover(Jugador jugador, Tablero tablero) {
        if (!activo) return;

        int direccion = random.nextInt(4);
        int nuevaFila    = fila;
        int nuevaColumna = columna;

        if (direccion == 0) {
            nuevaFila--;    // Arriba
        }
        else if (direccion == 1) {
            nuevaFila++;    // Abajo
        }
        else if (direccion == 2) {
            nuevaColumna--; // Izquierda
        }
        else nuevaColumna++;

        if (tablero.esMovimientoValido(nuevaFila, nuevaColumna)) {
            fila    = nuevaFila;
            columna = nuevaColumna;
        }
    }

    public void atacar(Jugador jugador){
        System.out.println("El enemigote choco!");
        jugador.recibirDanio(danio); //dano
    }

    public boolean verificarColision(Jugador jugador) {

        return fila == jugador.getFila() && columna == jugador.getColumna();
    }

    public void mostrarEstado(){
        System.out.println("Enemigo 2 [" + tipo + "] en posición: " + fila + "," + columna);

    }
}
