import java.util.Random;

public class Enemigo2 {
    public String tipo;
    public int fila;
    public int columna;
    public int daño;
    public boolean activo;
    public Random random;

    public Enemigo2(int fila, int columna) {
        this.tipo    = "Aleatorio";
        this.fila    = fila;
        this.columna = columna;
        this.daño   = 1;
        this.activo  = true;
        this.random  = new Random();
    }



    public void mover(Jugador j, Tablero tablero) {

        int direccion = random.nextInt(4);
        int nuevaFila    = fila;
        int nuevaColumna = columna;

        if (direccion == 0)      nuevaFila--;    // Arriba
        else if (direccion == 1) nuevaFila++;    // Abajo
        else if (direccion == 2) nuevaColumna--; // Izquierda
        else                     nuevaColumna++;


    }

    public void atacar(jugador j){
        System.out.println("El enemigote choco!");
        j.recibirDaño(daño);

    }
    public boolean verificarColision(){
        return false;

    }
    public void mostrarEstado(){
        System.out.println("Enemigo 2 [" + tipo + "] en posición: " + fila + "," + columna);

    }


}
