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
    public void mover() {


    }

    public void atacar() {

    }

    public void verificarColision() {



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
        Random posicion = new Random();
        //COLOCAR DIMENSIONES DE LA MATRIZ PARA GENERAR UNA POSICION ALEATORIA
        int Fila = 10;
        int Columna =10;
        int filaAleatoria=posicion.nextInt(Fila);
        int columnaAleatoria=posicion.nextInt(Columna);
        Enemigo1 ElVerdugo = new Enemigo1(filaAleatoria,columnaAleatoria);
        ElVerdugo.mostrarEstado();

    }
}
