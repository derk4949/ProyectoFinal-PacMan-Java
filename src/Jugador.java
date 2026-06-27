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


    Scanner lector=new Scanner(System.in);
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
                break;
            case "S":
                break;
            case "A":
                break;
            case "D":
                break;
            default:
                System.out.println("Tecla incorrecta");
                break;
        }
    }
    //Metodos
    public void recogerPunto(){
        this.puntaje=+10;
    }
    public void recibirDano() {
        this.salud=-1;
    }
    public void usarPoder() {

    }
    public boolean estaVivo () {
        if (this.salud>0){
            return true;
        } else {
            return false;
        }
    }
    public void mostrarEstado() {
        System.out.println("\n--- ESTADO DE: " + this.nombre + " ---");
        System.out.println("Posición: [" + this.fila + ", " + this.columnna + "]");
        System.out.println("Puntaje: " + this.puntaje);
        System.out.println("Vidas: " + this.salud);
        System.out.println("¿Tiene Poder?: " + (this.poderActivo ? "Sí" : "No"));
        System.out.println("¿Está vivo?:" );
        System.out.println("-----------------------------\n");

    }

    public static void main(String[]args) {
        Scanner lector = new Scanner(System.in);
        System.out.print("Ingresa tu nombre de usuario para Pac-Man: ");
        String nombreUsuario = lector.nextLine();
        Jugador jugadorUnico = new Jugador(nombreUsuario, 5, 5);
        jugadorUnico.mostrarEstado();
        String direccion = lector.nextLine();

    }
}