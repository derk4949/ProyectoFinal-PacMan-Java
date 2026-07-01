import java.util.Scanner;

public class Jugador {

    //1. ATRIBUTOS
    // Características que tendrá cada jugador.
    private String nombre;
    public int fila;       // Posición en el eje Y
    public int columnna;   // Posición en el eje X
    public int puntaje;    // Puntos
    public int salud;      // Cantidad de vidas.
    public int velocidad;  // Cuántas casillas se mueve.
    public boolean poderActivo; // Estado especial del personaje.


    // 2. CONSTRUCTOR
    public Jugador(String _nombre, int _fila, int _columnna) {
        this.nombre = _nombre;       // Inicializa con el nombre recibido por parámetro.
        this.fila = _fila;           // Posición inicial asignada en filas.
        this.columnna = _columnna;   // Posición inicial asignada en columnas.
        this.puntaje = 0;            
        this.salud = 3;
        this.velocidad = 1;
        this.poderActivo = false;
    }

    // 3. MÉTODOS


    public void mover(String direccion) {
        switch (direccion.toUpperCase()){
            case "W":
                this.fila -= this.velocidad; // Sube (resta en el eje vertical).
                break;
            case "S":
                this.fila += this.velocidad; // Baja (suma en el eje vertical).
                break;
            case "A":
                this.columnna -= this.velocidad; // Va a la izquierda (resta en el eje horizontal).
                break;
            case "D":
                this.columnna += this.velocidad; // Va a la derecha (suma en el eje horizontal).
                break;
            default:
                System.out.println("Tecla incorrecta. Usa W, A, S o D.");
                break;
        }
    }

    // Incrementa el puntaje
    public void recogerPunto(){
        this.puntaje = puntaje + 10;
    }

    // Reduce la salud del jugador en 1 unidad.
    public void recibirDano() {
        this.salud = salud - 1 ;
    }

    // Activa el poder del jugador.
    public void usarPoder() {
        this.poderActivo = true;
    }

    public boolean estaVivo () {
        if (this.salud > 0){
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
        System.out.println("¿Está vivo?:" + (this.estaVivo()? "Si": "No, GAME OVER") );
        System.out.println("-----------------------------\n");
    }

//----------------------------PRUEBA EN UN MAIN-----------------------------------------------------
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);

        System.out.print("Ingresa tu nombre de usuario para Pac-Man: ");
        String nombreUsuario = lector.nextLine();
        //crecion de objeto
        Jugador jugadorUnico = new Jugador(nombreUsuario, 5, 5);

        String direccion = " " ;

        // MIENTRAS QUE EL JUGADOR ESTE VIVO Y LA TECLA INGRESADA NO SEA X
        while (jugadorUnico.estaVivo() && !direccion.equalsIgnoreCase("X")) {

            // Muestra cómo está el personaje antes de pedir la siguiente acción.
            jugadorUnico.mostrarEstado();
            System.out.print("Presiona W/A/S/D para moverte (o 'X' para salir): ");
            direccion = lector.nextLine();

            // Si la tecla no fue 'X', procede a ejecutar el movimiento.
            if (!direccion.equalsIgnoreCase("X")) {
                jugadorUnico.mover(direccion);
            }
        }

        //mostrar estado si el personaje muere en plena partida, con su score
        if (!jugadorUnico.estaVivo()) {
            jugadorUnico.mostrarEstado(); // Muestra el estado final con el mensaje de GAME OVER.
        }

        System.out.println("FIN DE LA PARTIDA");
        lector.close();
    }
}