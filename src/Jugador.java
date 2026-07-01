import java.util.Scanner;

public class Jugador {

    // ATRIBUTOS
    private String nombre;
    public int fila;
    public int columnna;
    public int puntaje;
    public int salud;
    public int velocidad;
    public boolean poderActivo;


    //ATRIBUTOS CONSTRUCTOR
    public Jugador(String nombre,int fila, int columnna) {
        this.nombre = nombre;
        this.fila = fila;
        this.columnna = columnna;
        this.puntaje = 0;
        this.salud = 3;
        this.velocidad = 1;
        this.poderActivo = false;
    }
    //METODOS
    public void mover(String direccion) {

        switch (direccion.toUpperCase()){
            case "W":
                this.fila -= this.velocidad;
                break;
            case "S":
                this.fila += this.velocidad;
                break;
            case "A":
                this.columnna -= this.velocidad;
                break;
            case "D":
                this.columnna += this.velocidad;
                break;
            default:
                System.out.println("Tecla incorrecta. Usa W, A, S o D.");
                break;
        }
    }
    //Metodos
    public void recogerPunto(){
        this.puntaje=puntaje + 10;
    }
    public void recibirDano() {
        this.salud=salud - 1 ;
    }
    public void usarPoder() {
        this.poderActivo = true;
    }
    public boolean estaVivo () {
        if (this.salud>0){
            return true;
        } else {
            return false;
        }
    }
    //mostrar estado
    public void mostrarEstado() {
        System.out.println("\n--- ESTADO DE: " + this.nombre + " ---");
        System.out.println("Posición: [" + this.fila + ", " + this.columnna + "]");
        System.out.println("Puntaje: " + this.puntaje);
        System.out.println("Vidas: " + this.salud);
        System.out.println("¿Tiene Poder?: " + (this.poderActivo ? "Sí" : "No"));
        System.out.println("¿Está vivo?:" + (this.estaVivo()? "Si": "No, GAME OVER") );
        System.out.println("-----------------------------\n");

    }
    public static void main(String[]args) {
        Scanner lector = new Scanner(System.in);
        //nombre
        System.out.print("Ingresa tu nombre de usuario para Pac-Man: ");
        String nombreUsuario = lector.nextLine();

        //punto fijo en las filas y columnas
            Jugador jugadorUnico = new Jugador(nombreUsuario, 5, 5);

        jugadorUnico.mostrarEstado();

        //Se inicia con un vacion en la variable direccion
        String direccion = " " ;

        // MIENTRAS QUE EL JUGADOR ESTE VIVO Y LA TECLA INGRESADA NO SEA X
        while (jugadorUnico.estaVivo() && !direccion.equalsIgnoreCase("X")) {
            // SE MUESTRA ESTADO

            jugadorUnico.mostrarEstado();
            System.out.print("Presiona W/A/S/D para moverte (o 'X' para salir): ");
            direccion = lector.nextLine();

            //Si no ingresa X entonces mover la direccion del jugador
            if (!direccion.equalsIgnoreCase("X")) {
                jugadorUnico.mover(direccion);

            }
        }
        //mostrar estado si el personaje muere en plena partida
        if (!jugadorUnico.estaVivo()) {
            jugadorUnico.mostrarEstado();
        }
        System.out.println("FIN DE LA PARTIDA");
        lector.close();

    }
}