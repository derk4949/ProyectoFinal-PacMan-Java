import java.util.Random;
public class Enemigo1 {
    // ATRIBUTOS

    public String tipo;
    public int fila;
    public int columna;
    public int dano;
    public int vida;
    public boolean activo;


    public Enemigo1 (int fila , int columna) {
        this.tipo = "Perseguir";
        this.fila=fila;
        this.columna=columna;
        this.dano=1;
        this.activo=true;
        this.vida=1;
    }
    // MÉTODOS
    public void mover(Jugador jugador) {
        if (this.fila<jugador.fila) { // 3 <5
            this.fila=fila+1;
        } else {
            if (this.fila>jugador.fila){
                this.fila=fila-1;
            }
        }
        if (this.columna<jugador.columnna) { // 3 <5
            this.columna=columna+1;
        } else {
            if (this.columna>jugador.columnna){
                this.columna=columna-1;
            }
        }


    }
    public void atacar(Jugador jugador) {
        if((this.fila==jugador.fila)&&(this.columna==jugador.columnna)){
            jugador.recibirDano();
        }
    }

    public boolean verificarColision(Jugador jugador) {
        if ((this.fila == jugador.fila) && (this.columna == jugador.columnna)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaVivo () {
        if (this.vida>0){
            return true;
        } else {
            return false;
        }
    }

    public void mostrarEstado(){
        System.out.println("Enemigo: El Verdugo");
        System.out.println("--------------------");
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Daño: " + this.dano);
        System.out.println("Estado : " + (estaVivo() ? "ACTIVADO" : "DESACTIVADO"));
        if (estaVivo()) {
            System.out.println("Posicion : [" + this.fila + "," + this.columna + "]");
        }else{
            System.out.println("Posicion : [0,0]");
        }


    }

    public static void main(String[]args){
        //COLOCAR DIMENSIONES DE LA MATRIZ PARA GENERAR UNA POSICION ALEATORIA
        Enemigo1 ElVerdugo = new Enemigo1(5,5);
        ElVerdugo.mostrarEstado();

    }
}

