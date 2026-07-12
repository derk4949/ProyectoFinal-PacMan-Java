public class Enemigo1 {
    // ATRIBUTOS
    private String tipo;
    private int fila;
    private int columna;
    private int dano;
    private int vida;
    private boolean activo;

    //CONSTRUCTOR
    public Enemigo1 (int _fila , int _columna) {
        this.tipo = "Perseguir";
        this.fila = _fila;
        this.columna = _columna;
        this.dano = 1;
        this.activo = true;
        this.vida = 1;
    }

    // GETTERS (todos, para que Juego pueda leer el estado)

    public String getTipo() {
        return tipo;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getDano() {
        return dano;
    }

    public int getVida() {
        return vida;
    }

    public boolean isActivo() {
        return activo;
    }

    // SETTERS (solo lo necesario para que Juego pueda reposicionar/activar-desactivar)

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // MÉTODOS
    public void mover(Jugador jugador) {
        if (this.fila < jugador.getFila()) {
            this.fila = fila + 1;
        } else {
            if (this.fila > jugador.getFila()){
                this.fila = fila - 1;
            }
        }
        if (this.columna < jugador.getColumnna()) {
            this.columna = columna + 1;
        } else {
            if (this.columna > jugador.getColumnna()){
                this.columna = columna - 1;
            }
        }
    }

    public void atacar(Jugador jugador) {
        if((this.fila == jugador.getFila()) && (this.columna == jugador.getColumnna())){
            jugador.recibirDano();
        }
    }

    public boolean verificarColision(Jugador jugador) {
        if ((this.fila == jugador.getFila()) && (this.columna == jugador.getColumnna())) {
            return true;
        } else {
            return false;
        }
    }

    // Reduce la vida del enemigo (por ejemplo, si el jugador tiene un poder de ataque)
    public void recibirDano() {
        this.vida = vida - 1;
        if (this.vida <= 0) {
            this.activo = false;
        }
    }

    public boolean estaVivo () {
        if (this.vida > 0){
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
        Enemigo1 ElVerdugo = new Enemigo1(5,5);
        ElVerdugo.mostrarEstado();
    }
}