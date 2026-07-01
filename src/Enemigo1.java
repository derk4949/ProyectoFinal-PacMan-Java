public class Enemigo1 {
    // ATRIBUTOS
    public String tipo;
    public int fila;
    public int columna;
    public int dano;
    public int vida;
    public boolean activo;

    //CONSTRUCTOR
    public Enemigo1 (int _fila , int _columna) {
        this.tipo = "Perseguir";
        this.fila= _fila;
        this.columna= _columna;
        this.dano=1;
        this.activo=true;
        this.vida=1;
    }
    // MÉTODOS
    public void mover(Jugador jugador) {
        if (this.fila<jugador.fila) { // Se compara las posiciones y se realiza movimientos
            this.fila=fila+1;
        } else {
            if (this.fila>jugador.fila){
                this.fila=fila-1;
            }
        }
        if (this.columna<jugador.columnna) {
            this.columna=columna+1;
        } else {
            if (this.columna>jugador.columnna){
                this.columna=columna-1;
            }
        }


    }
    public void atacar(Jugador jugador) {
        if((this.fila==jugador.fila)&&(this.columna==jugador.columnna)){
            jugador.recibirDano(); //se llama al metodo recibir daño de la clase jugador
        }
    }

    public boolean verificarColision(Jugador jugador) {
        if ((this.fila == jugador.fila) && (this.columna == jugador.columnna)) {
            return true; //si hubo colision
        } else {
            return false; //no hubo colision
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
//----------------------------PRUEBA EN UN MAIN-----------------------------------------------------
    public static void main(String[]args){
        //COLOCAR DIMENSIONES DE LA MATRIZ PARA GENERAR UNA POSICION ALEATORIA
        Enemigo1 ElVerdugo = new Enemigo1(5,5);
        ElVerdugo.mostrarEstado();

    }
}

