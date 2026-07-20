import java.util.Random;
import java.util.Scanner;
public class Juego {
    private Jugador jugador;
    private Tablero tablero;
    private Enemigo[] enemigos;
    private boolean juegoTerminado;
    private ControlEnemigos controlEnemigos;
    private Scanner scanner;

    public Juego() {
        scanner = new Scanner(System.in);
        jugador = null;
        tablero = null;
        enemigos = new Enemigo[0];
        controlEnemigos = null;
        juegoTerminado = false;
    }



    public void iniciarJuego() {

        boolean programaActivo = true;

        while (programaActivo) {

            System.out.println();
            System.out.println("------------------------");
            System.out.println("       PAC-MAN"          );
            System.out.println("------------------------");
            System.out.println("1. Iniciar juego");
            System.out.println("2. Ver instrucciones");
            System.out.println("3. Salir");

            int opcion = leerEntero("Seleccione una opcion: ");

            switch (opcion) {

                case 1:
                    configurarNuevaPartida();
                    break;

                case 2:
                    mostrarInstrucciones();
                    break;

                case 3:
                    programaActivo = false;
                    System.out.println("Gracias por jugar");
                    break;

                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }
        scanner.close();
    }

// pedir entero si osi
    private int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    //para validar numero y asegurar que sea el minimo
    private int leerEnteroMinimo(String mensaje, int minimo) {

        while (true) {

            int valor = leerEntero(mensaje);

            if (valor < minimo) {
                System.out.println("El valor minimo permitido es " + minimo);
            } else {
                return valor;
            }
        }
    }

    private int leerCantidadMuros() {

        while (true) {

            int cantidad = leerEntero("Cantidad de muros (-1 para automatico): ");

            if (cantidad < -1) {
                System.out.println("Ingrese -1 para automatico o un numero mayor o igual a 0");
            } else {
                return cantidad;
            }
        }
    }

    private void mostrarInstrucciones() {
        System.out.println();
        System.out.println("CONTROLES");
        System.out.println("W = arriba");
        System.out.println("S = abajo");
        System.out.println("A = izquierda");
        System.out.println("D = derecha ");
        System.out.println("Q = abandonar la partida ");

        System.out.println();
        System.out.println("OBJETIVO");
        System.out.println("- Recoger todos los puntos");
        System.out.println("- Evitar a los enemigos.");
        System.out.println("-  El jugador comienza con 3 vidas");
        System.out.println("- Cada colision quita exactamente una vida");
        System.out.println("- Se gana al recoger todos los puntos");
        System.out.println("- Se pierde al quedarse sin vidas");

        System.out.println();
        System.out.println("PODERES");
        System.out.println("- Velocidad: permite dos movimientos por turno temporalmente");
        System.out.println("- Congelacion: evita que los enemigos se muevan temporalmente");
        System.out.println("- Vida extra: recupera una vida sin superar el maximo de 3");
    }

    // pide datos para armar la partida y entra a ciclo de turnos si se creo correctamente
    private void configurarNuevaPartida() {
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine().trim();

        int filas = leerEnteroMinimo("Cantidad de filas (minimo 7): ", 7);
        int columnas = leerEnteroMinimo("Cantidad de columnas (minimo 7): ", 7);
        int cantidadPoderes = leerEnteroMinimo("Cantidad de poderes (minimo 3): ", 3);
        int cantidadMuros = leerCantidadMuros();
        int cantidadEnemigos = leerEnteroMinimo("Cantidad de enemigos (minimo 3): ", 3);

        boolean partidaCreada = prepararPartida(
                nombre,
                filas,
                columnas,
                cantidadPoderes,
                cantidadMuros,
                cantidadEnemigos
        );

        if (partidaCreada) {
            ejecutarPartida();
        }
    }

    private boolean prepararPartida(
            String nombreJugador,
            int filas,
            int columnas,
            int cantidadPoderes,
            int cantidadMuros,
            int cantidadEnemigos
    ) {

        // El mismo objeto Juego puede iniciar varias partidas, asi que
        // se reinicia todo antes de armar la nueva.
        jugador = null;
        tablero = null;
        enemigos = new Enemigo[0];
        controlEnemigos = null;
        juegoTerminado = false;

        // Validacion del nombre.
        String nombreValidado;
        if (nombreJugador == null || nombreJugador.trim().isEmpty()) {
            nombreValidado = "Jugador";
        } else {
            nombreValidado = nombreJugador.trim();
        }

        // Validaciones numericas: se conservan como proteccion interna,
        // aunque ya el teclado no deje pasar valores menores al minimo.
        if (filas < 7) {
            filas = 7;
        }

        if (columnas < 7) {
            columnas = 7;
        }

        if (cantidadPoderes < 3) {
            cantidadPoderes = 3;
        }

        if (cantidadEnemigos < 3) {
            cantidadEnemigos = 3;
        }

        if (cantidadMuros < -1) {
            cantidadMuros = -1;
        }

        // Creacion del tablero.
        tablero = new Tablero(filas, columnas, cantidadPoderes);

        if (cantidadMuros == -1) {
            tablero.generarTablero();
        } else {
            tablero.generarTablero(cantidadMuros);
        }

        // Creacion del jugador.
        int[] posicionJugador = tablero.obtenerPosicionInicialJugador();

        if (posicionJugador[0] == -1 || posicionJugador[1] == -1) {
            System.out.println("No fue posible crear al jugador en el tablero");
            juegoTerminado = true;
            return false;
        }

        jugador = new Jugador(nombreValidado, posicionJugador[0], posicionJugador[1]);
        tablero.colocarJugador(jugador);

        // Enemigos.
        generarEnemigos(cantidadEnemigos);

        controlEnemigos = new ControlEnemigos(enemigos, jugador);
        tablero.colocarEnemigos(enemigos);

        juegoTerminado = false;

        System.out.println("El juego ha comenzado buena suerte " + jugador.getNombre());

        return true;
    }


//pide una direccion por turno y se la pasa a ejecutarturno hasta que el juego termine
    private void ejecutarPartida() {

        boolean abandonarPartida = false;

        while (!juegoTerminado && !abandonarPartida) {

            System.out.println();
            mostrarEstado();

            System.out.print("Movimiento (W/A/S/D) o Q para abandonar: ");
            String direccion = scanner.nextLine().trim().toUpperCase();

            if (direccion.equals("Q")) {
                abandonarPartida = true;
                System.out.println("Partida abandonada, reegresando al menu principal");

            } else if (direccion.equals("W")
                    || direccion.equals("A")
                    || direccion.equals("S")
                    || direccion.equals("D")) {

                ejecutarTurno(direccion);

            } else {
                System.out.println("tecla no reconocido, Use W, A, S, D o Q");
            }
        }

        if (juegoTerminado) {
            System.out.println();
            mostrarEstado();
            System.out.println("Fin de la partida. Regresando al menu principal");
        }
    }


    // crea y coloca enemigo en base de tablero
    public void generarEnemigos(int cantidad) {

        if (cantidad < 3) {
            cantidad = 3;
        }

        enemigos = new Enemigo[cantidad];

        int[] base = tablero.obtenerPosicionBase();

        for (int i = 0; i < cantidad; i++) {

            if (i % 3 == 0) {
                enemigos[i] = new Enemigo1(base[0], base[1]);
            } else if (i % 3 == 1) {
                enemigos[i] = new Enemigo2(base[0], base[1]);
            } else {
                enemigos[i] = new Enemigo3(base[0], base[1]);
            }
        }
    }

//revisa si lacasilla actual del jugador hay un punto o poder y los aplica, devuelve true solo si recogio poder
    private boolean procesarCasillaJugador() {

        Punto punto = tablero.buscarPunto(jugador.getFila(), jugador.getColumna());

        if (punto != null) {
            int valor = punto.obtenerValor();
            jugador.recogerPunto(punto);
            tablero.eliminarPuntoDelTablero(jugador.getFila(), jugador.getColumna());
            System.out.println("punto recogido a ganado : " + valor);
        }

        Poder poder = tablero.buscarPoder(jugador.getFila(), jugador.getColumna());

        if (poder != null) {

            int duracion = poder.getDuracion();

            System.out.println(poder.descripcion());
            System.out.println("Poder consumido: " + poder.getTipo() + ".");

            jugador.usarPoder(poder);
            tablero.eliminarPoderDelTablero(jugador.getFila(), jugador.getColumna());

            if (duracion > 0) {
                return true;
            }
        }

        return false;
    }

//comprobar enemigo en casilla del jugador, solo comprobacion
    private boolean hayEnemigoEnPosicionJugador() {

        for (int i = 0; i < enemigos.length; i++) {

            Enemigo enemigo = enemigos[i];

            if (enemigo == null || !enemigo.estaActivo()) {
                continue;
            }

            if (enemigo.getFila() == jugador.getFila()
                    && enemigo.getColumna() == jugador.getColumna()) {
                return true;
            }
        }

        return false;
    }

//mueve al jugador de acuerdo a su velocidad y procesa lo que encuentre en el camino y verifica colisiones
public boolean ejecutarTurno(String direccion) {
    if (juegoTerminado
            || jugador == null
            || tablero == null
            || controlEnemigos == null
            || direccion == null) {
        return false;
    }
    int movimientosPermitidos = jugador.getVelocidad();

    boolean huboMovimiento = false;
    boolean poderTemporalNuevo = false;

    for (int i = 0; i < movimientosPermitidos; i++) {

        boolean movimientoValido = jugador.mover(direccion, tablero);

        if (!movimientoValido) {

            if (!huboMovimiento) {
                System.out.println("Movimiento invalido");
                return false;
            } else {
                break;
            }
        }
        huboMovimiento = true;

        if (procesarCasillaJugador()) {
            poderTemporalNuevo = true;
        }

        if (hayEnemigoEnPosicionJugador()) {

            controlEnemigos.verificarColisiones(tablero);

            if (!poderTemporalNuevo) {
                jugador.actualizarContadorPoder();
            }

            controlEnemigos.eliminarEnemigosInactivos();
            verificarFinJuego();
            return true;
        }
        if (verificarFinJuego()) {
            return true;
        }
    }
    if (huboMovimiento && !juegoTerminado) {

        controlEnemigos.moverEnemigos(tablero);
        controlEnemigos.verificarColisiones(tablero);
        controlEnemigos.eliminarEnemigosInactivos();

        if (!poderTemporalNuevo) {
            jugador.actualizarContadorPoder();
        }

        verificarFinJuego();
    }
    return true;
}
//verificar si tiene vidas y despues victoria osea comiendo todos los puntos
public boolean verificarFinJuego() {

    if (juegoTerminado) {
        return true;
    }

    if (jugador == null || tablero == null) {
        return false;
    }

    if (!jugador.estaVivo()) {
        juegoTerminado = true;
        System.out.println("Juego terminado, " + jugador.getNombre() + " ha perdido");
        System.out.println("Puntaje final: " + jugador.getPuntaje());
        return true;
    }

    if (tablero.contarPuntosRestantes() == 0) {
        juegoTerminado = true;
        System.out.println("Felicidades, " + jugador.getNombre() + ". Has ganado el juegoo");
        System.out.println("Puntaje final: " + jugador.getPuntaje());
        return true;
    }

    return false;
}

//refresca tabkeri en pantaklla y muestra el estado del jugador junto con los puntos que faltan  recoger
    public void mostrarEstado() {

        if (tablero == null || jugador == null) {
            return;
        }

        actualizarTablero();

        System.out.println();

        jugador.mostrarEstado();

        System.out.println("Puntos restantes: " + tablero.contarPuntosRestantes());
    }

//solo nuestra el tablero
    public void actualizarTablero() {
        if (tablero != null) {
            tablero.mostrarTablero();
        }
    }

    // GETTERS
    public Jugador getJugador() {
        return jugador;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public Enemigo[] getEnemigos() {
        return enemigos;
    }

    public boolean estaTerminado() {
        return juegoTerminado;
    }
}


