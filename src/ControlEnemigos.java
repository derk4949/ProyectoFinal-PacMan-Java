// Clase ControlEnemigos: se encarga de administrar a TODOS los enemigos
// del juego como grupo.
// No decide como se mueve cada enemigo,eso ya lo
// hace cada subclase de Enemigo con su propio mover()); esta clase solo
// coordina, les da la orden de moverse, evita que dos enemigos terminen
// en la misma casilla, respeta la congelasion y procesa las colisiones
// con el jugador
public class ControlEnemigos {

    // Arreglo de enemigos controlados Es el MISMO arreglo que tiene
    // Tablero, no una copia, para que ambos vean siempre los mismos
    // cambios (por ejemplo, cuando un enemigo se pone en null).
    private Enemigo[] enemigos;

    // Jugador contra el que se revisan las colisiones.
    private Jugador jugador;

    // CONSTRUCTOR
    // Guarda el arreglo de enemigos recibido (sin copiarlo) y el jugador....
    // Si llega null en enemigos, se guarda un arreglo vacio para que el
    // resto de la clase nunca tenga que revisar null en cada bucle
    public ControlEnemigos(Enemigo[] enemigos, Jugador jugador) {

        if (enemigos == null) {
            this.enemigos = new Enemigo[0];
        } else {
            this.enemigos = enemigos;
        }

        this.jugador = jugador;
    }

    // CONGELACION
    // Devuelve true solo si el jugador existe, tiene un poder activo y
    // ese poder es exactamente "Congelacion"
    private boolean enemigosCongelados() {
        return jugador != null
                && jugador.tienePoderActivo()
                && "Congelacion".equals(jugador.getTipoPoderActivo());
    }

    // POSICIONES OCUPADAS
    // Recorre el arreglo buscando OTRO enemigo (distinto indice) que
    // este activo y parado en la misma fila y columna indicadas.
    private boolean posicionOcupadaPorOtro(int indiceActual, int fila, int columna) {

        for (int i = 0; i < enemigos.length; i++) {

            Enemigo otro = enemigos[i];

            if (otro == null) {
                continue;
            }

            if (i == indiceActual) {
                continue;
            }

            if (!otro.estaActivo()) {
                continue;
            }

            if (otro.getFila() == fila && otro.getColumna() == columna) {
                return true;
            }
        }

        return false;
    }

    // CONTROL DE LA SALIDA
    // Revisa si otro enemigo (activo y con indice distinto) esta parado
    // exactamente en la casilla de salida de la base.
    private boolean salidaBaseOcupadaPorOtro(int indiceActual, Tablero tablero) {

        int[] salida = tablero.obtenerPosicionSalidaBase();

        for (int i = 0; i < enemigos.length; i++) {

            if (i == indiceActual) {
                continue;
            }

            Enemigo otro = enemigos[i];

            if (otro == null) {
                continue;
            }

            if (!otro.estaActivo()) {
                continue;
            }

            if (otro.getFila() == salida[0] && otro.getColumna() == salida[1]) {
                return true;
            }
        }

        return false;
    }

    // MOVIMIENTO DE LOS ENEMIGOS
    // Mueve a todos los enemigos activos un turno respetando la
    // congelacion y evitando que dos enemigos queden en la misma celda.
    public void moverEnemigos(Tablero tablero) {

        if (tablero == null || jugador == null) {
            return;
        }

        // Si el jugador tiene el poder de congelacion activo...ningun
        // enemigo se mueve este turno.
        if (enemigosCongelados()) {

            for (int i = 0; i < enemigos.length; i++) {

                Enemigo enemigo = enemigos[i];

                if (enemigo != null && enemigo.estaActivo()) {
                    // Solo iguala la posicion anterior con la actual,
                    // para que no se genere una falsa colision por
                    // intercambio mientras esta congelado.
                    enemigo.reiniciarPosicion(enemigo.getFila(), enemigo.getColumna());
                }
            }

            System.out.println("Los enemigos estan congelados.");
            return;
        }

        // Movimiento normal: cada enemigo activo se mueve segun su
        // propio comportamiento (Perseguidor, Aleatorio o Fantasma).
        for (int i = 0; i < enemigos.length; i++) {

            Enemigo enemigo = enemigos[i];

            if (enemigo == null || !enemigo.estaActivo()) {
                continue;
            }

            // Los enemigos normales dentro de la base esperan si otro
            // enemigo ya ocupa la salida, para que el corredor funcione
            // ordenadamente (de a uno). El Fantasma no usa la puerta,
            // asi que no participa en esta espera.
            if (tablero.esPosicionBase(enemigo.getFila(), enemigo.getColumna())
                    && !"Fantasma".equals(enemigo.getTipo())
                    && salidaBaseOcupadaPorOtro(i, tablero)) {

                enemigo.reiniciarPosicion(enemigo.getFila(), enemigo.getColumna());
                continue;
            }

            // Guardamos de donde partio, por si hay que devolverlo.
            int filaOrigen = enemigo.getFila();
            int columnaOrigen = enemigo.getColumna();

            enemigo.mover(tablero, jugador);

            // Si otro enemigo ya esta en la casilla a la que se movio,
            // este enemigo regresa a su posicion de origen.
            if (posicionOcupadaPorOtro(i, enemigo.getFila(), enemigo.getColumna())) {
                enemigo.reiniciarPosicion(filaOrigen, columnaOrigen);
            }
        }
    }

    // GENERAR MOVIMIENTOS
    // Nombre alternativo pedido por la guia. Solo delega en
    // moverEnemigos() para no duplicar la logica.
    // IMPORTANTE: Juego debe llamar a moverEnemigos() O a
    // generarMovimientos() en el turno pero NUNCA a los dosporque
    // los enemigos se moverian dos veces en el mismo turno.
    public void generarMovimientos(Tablero tablero) {
        moverEnemigos(tablero);
    }

    // COLISIONES
    // Revisa si algun enemigo activo choco con el jugador
    // Se detiene en el primer choque encontrado, para procesar como maximo una perdida de vida por turno.
    public boolean verificarColisiones(Tablero tablero) {

        if (tablero == null || jugador == null) {
            return false;
        }

        for (int i = 0; i < enemigos.length; i++) {

            Enemigo enemigo = enemigos[i];

            if (enemigo == null || !enemigo.estaActivo()) {
                continue;
            }

            if (enemigo.verificarColision(jugador)) {

                System.out.println("Colision con el enemigo: " + enemigo.getTipo());

                // atacar() ya llama internamente a jugador.perderVida().
                enemigo.atacar(jugador);

                if (jugador.estaVivo()) {
                    reiniciarDespuesDeColision(tablero);
                }

                return true;
            }
        }

        return false;
    }

    // REINICIO DESPUES DE UNA COLISION
    // Regresa al jugador a su posicion inicial..los enemigos NO regresan
    // a la base conservan exactamente la fila y columna donde ocurrio
    // la colision. Solo se iguala su posicion anterior con la actual
    // (mismo valor que ya tenian) para evitar una falsa colision por
    // intercambio en el siguiente turno.
    private void reiniciarDespuesDeColision(Tablero tablero) {

        int[] posicionJugador = tablero.obtenerPosicionInicialJugador();

        if (posicionJugador[0] != -1 && posicionJugador[1] != -1) {
            jugador.teletransportar(posicionJugador[0], posicionJugador[1]);
        }

        for (int i = 0; i < enemigos.length; i++) {

            Enemigo enemigo = enemigos[i];

            if (enemigo != null && enemigo.estaActivo()) {
                enemigo.reiniciarPosicion(enemigo.getFila(), enemigo.getColumna());
            }
        }
    }

    // ELIMINAR ENEMIGOS INACTIVOS
    // Saca del arreglo (poniendolos en null) a los enemigos que ya no
    // estan activos...como es el mismo arreglo que usa Tablero.. este
    // cambio tambien se refleja alli.
    public void eliminarEnemigosInactivos() {

        for (int i = 0; i < enemigos.length; i++) {

            Enemigo enemigo = enemigos[i];

            if (enemigo != null && !enemigo.estaActivo()) {
                System.out.println(enemigo.getTipo() + " fue eliminado del control.");
                enemigos[i] = null;
            }
        }
    }

    // GETTER
    public Enemigo[] getEnemigos() {
        return enemigos;
    }
}
