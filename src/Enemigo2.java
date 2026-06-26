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


    }

    public void atacar(){
        System.out.println("El enemigote choco!");

    }
    public boolean verificarColision(){
        return false;

    }
    public void mostrarEstado(){
        System.out.println("Enemigo 2 [" + tipo + "] en posición: " + fila + "," + columna);

    }


}
