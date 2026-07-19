import java.util.Random;

public class Tablero {

    // CONSTANTES DE SÍMBOLOS DEL TABLERO
    // Se centralizan aquí en vez de repetir caracteres "mágicos"
    // ('#', 'B', '.', 'O', ' ') por todo el código. Si algún día se
    // quiere cambiar un símbolo, solo se cambia en un lugar.
    // ============================================================
    private static final char MURO = '#';
    private static final char BASE = 'B';
    private static final char PUNTO = '.';
    private static final char PODER = 'O';
    private static final char VACIO = ' ';

    //Tamaño minimo del tablero necesario para la base 3x3 de enemigos, dejando libre la celda bajo su entrada.
    private static final int TAMANO_MINIMO_PARA_BASE = 7;

    // ATRIBUTOS DEL TABLERO

    // Cantidad de filas y columnas que tendrá el tablero
    private int filas;
    private int columnas;

    // Matriz donde se guardan los elementos fijos del mapa: MURO  = muro, BASE  = base de enemigos (una sola celda, en el centro del bloque 3x3) ,PUNTO = punto, PODER = poder, VACIO = espacio vacío
    private char[][] matriz;

    // Arreglo que guarda los objetos de tipo Muro
    private Muro[] muros;
    private int cantidadMurosColocados;

    // Arreglo que guarda los objetos de tipo Punto (se llena TODO el tablero)
    private Punto[] puntos;

    // Arreglo que guarda los objetos de tipo Poder (mezcla Poder1/2/3)
    private Poder[] poderes;

    // Referencia al jugador que será mostrado en el tablero
    private Jugador jugador;

    // Posicion reservada antes de rellenar los caminos con puntos, para evitar que el jugador termine ubicado en (-1, -1).
    private int filaInicialJugador;
    private int columnaInicialJugador;

    // Referencia a los enemigos (Perseguidor, Aleatorio, Fantasma, o los que sean)
    private Enemigo[] enemigos;

    // Indica si el tablero alcanzo a crear la base de enemigos. Antes, obtenerPosicionBase()/esPosicionBase() asumian que la base siempre existia, pero crearBaseEnemigos() se negaba a crearla en tableros menores a 7x7.

    private boolean baseCreada;

    // Unica instancia de Random para todo el tablero, antes se creaba una nueva instancia en cada llamada a obtenerPosicionLibreAleatoria(), lo cual era innecesario.
    private Random aleatorio;

    // CONSTRUCTOR
    // Ya no recibe cantidad de muros ni de puntos: los muros se definen en generarTablero(cantidadMurosDeseada) segun lo que elija el usuario en el menu, y los puntos ahora llenan automáticamente todo el espacio libre que quede (como en el Pac-Man "original").

    public Tablero(int _filas, int _columnas, int _cantidadPoderes) {

        if (_filas < TAMANO_MINIMO_PARA_BASE || _columnas < TAMANO_MINIMO_PARA_BASE) {
            System.out.println("El tamaño minimo del tablero es " + TAMANO_MINIMO_PARA_BASE + "x" + TAMANO_MINIMO_PARA_BASE + " para poder crear la base de enemigos. " + "Se ajusto el tamaño solicitado (" + _filas + "x" + _columnas + ").");
        }
        if (_filas < TAMANO_MINIMO_PARA_BASE) {
            _filas = TAMANO_MINIMO_PARA_BASE;
        }
        if (_columnas < TAMANO_MINIMO_PARA_BASE) {
            _columnas = TAMANO_MINIMO_PARA_BASE;
        }

        this.filas = _filas;
        this.columnas = _columnas;

        if (_cantidadPoderes < 0) {
            _cantidadPoderes = 0;
        }

        this.matriz = new char[_filas][_columnas];

        this.muros = new Muro[0];
        this.cantidadMurosColocados = 0;
        this.puntos = new Punto[0];
        this.poderes = new Poder[_cantidadPoderes];
        this.filaInicialJugador = -1;
        this.columnaInicialJugador = -1;
        this.baseCreada = false;
        this.aleatorio = new Random();
    }

    // Indica si una celda es "transitable" para el jugador: ni muro ni base.
    // La usamos para validar movimientos y para el chequeo de conectividad.
    private boolean esCeldaTransitablePorJugador(int fila, int columna) {
        if (!estaDentroDelTablero(fila, columna)) {
            return false;
        }
        return matriz[fila][columna] != MURO && matriz[fila][columna] != BASE;
    }

    // POSICIÓN DE LA BASE (donde nacen todos los enemigos)

    public int[] obtenerPosicionBase() {
        if (!baseCreada) {
            return new int[]{-1, -1};
        }
        return new int[]{filas / 2, columnas / 2};
    }

    public int[] obtenerPosicionSalidaBase() {
        if (!baseCreada) {
            return new int[]{-1, -1};
        }
        return new int[]{filas / 2 + 1, columnas / 2};
    }

    public boolean esPosicionBase(int fila, int columna) {
        if (!baseCreada) {
            return false;
        }
        return fila == filas / 2 && columna == columnas / 2;
    }

    // CORREDOR DE SALIDA DE LA BASE
    // La casilla delante de la salida (fuera de la base). Junto con la salida inmediata, forma el corredor por el que los enemigos normales avanzan de a uno para no bloquearse entre ellos.
    public int[] obtenerPosicionFrenteSalidaBase() {

        if (!baseCreada) {
            return new int[]{-1, -1};
        }

        int filaSalida = filas / 2 + 1;
        int columnaSalida = columnas / 2;

        int filaFrente = filaSalida + 1;
        int columnaFrente = columnaSalida;

        if (!esPosicionInterior(filaFrente, columnaFrente)) {
            return new int[]{-1, -1};
        }

        return new int[]{filaFrente, columnaFrente};
    }

    // Compara la posicion recibida con la salida inmediata de la base.
    public boolean esPosicionSalidaBase(int fila, int columna) {

        int[] salida = obtenerPosicionSalidaBase();

        if (salida[0] == -1 && salida[1] == -1) {
            return false;
        }

        return fila == salida[0] && columna == salida[1];
    }

    // Zona del corredor completo: la salida inmediata MAS la casilla delante de la salida.
    public boolean esZonaSalidaBase(int fila, int columna) {

        if (!baseCreada) {
            return false;
        }

        if (esPosicionSalidaBase(fila, columna)) {
            return true;
        }

        int[] frente = obtenerPosicionFrenteSalidaBase();

        if (frente[0] == -1 && frente[1] == -1) {
            return false;
        }

        return fila == frente[0] && columna == frente[1];
    }

    // VALIDACION DE POSICIONES LIBRES

    public boolean estaPosicionLibre(int fila, int columna) {

        if (!esPosicionInterior(fila, columna)) {
            return false;
        }

        if (esZonaSalidaBase(fila, columna)) {
            return false;
        }

        return matriz[fila][columna] == VACIO;
    }

    public boolean esPosicionInterior(int fila, int columna) {
        if (fila > 0 && fila < filas - 1 && columna > 0 && columna < columnas - 1) {
            return true;
        }
        return false;
    }

    public boolean estaDentroDelTablero(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return true;
        }
        return false;
    }

    // BUSQUEDA DE UNA POSICION LIBRE ALEATORIA

    public int[] obtenerPosicionLibreAleatoria() {

        int filaAleatoria;
        int columnaAleatoria;

        int intentos = 0;
        int maximoIntentos = filas * columnas;

        if (filas <= 2 || columnas <= 2) {
            return new int[]{-1, -1};
        }

        while (intentos < maximoIntentos) {

            filaAleatoria = aleatorio.nextInt(filas - 2) + 1;
            columnaAleatoria = aleatorio.nextInt(columnas - 2) + 1;

            if (estaPosicionLibre(filaAleatoria, columnaAleatoria)) {
                return new int[]{filaAleatoria, columnaAleatoria};
            }

            intentos++;
        }

        // Si la búsqueda aleatoria tuvo mala suerte, revisamos toda la matriz antes de afirmar que no queda espacio.
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                if (estaPosicionLibre(i, j)) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    // Cuenta cuántas celdas interiores son transitables ahora mismo (no son muro ni base). Sirve para saber cuánto espacio libre queda.
    private int contarCeldasTransitables() {
        int total = 0;
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                if (esCeldaTransitablePorJugador(i, j)) {
                    total++;
                }
            }
        }
        return total;
    }

    // VERIFICA QUE TODO EL CAMINO SIGA CONECTADO

    // Este método evita que un muro encierre puntos, poderes o al jugador.
    // No usa pilas, colas, recursividad ni colecciones. Solamente utiliza una matriz boolean, bucles, condicionales y contadores.
    // La idea es sencilla:
    // 1. Se marca una primera casilla transitable.
    // 2. Las casillas vecinas a una casilla marcada tambien se marcan.
    // 3. Se repite hasta que ya no aparezcan nuevas casillas alcanzables.
    // 4. Si se alcanzaron todas las casillas transitables, el tablero sigue conectado y el muro puede conservarse.
    private boolean tableroSigueConectado() {

        int filaInicial = -1;
        int columnaInicial = -1;
        int cantidadCaminos = 0;

        // Buscar una primera casilla transitable y contar todas las casillas por donde puede caminar el jugador.
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {

                if (esCeldaTransitablePorJugador(i, j)) {
                    cantidadCaminos++;

                    if (filaInicial == -1) {
                        filaInicial = i;
                        columnaInicial = j;
                    }
                }
            }
        }

        // Si no hay caminos, no existe una zona separada que revisar.
        if (cantidadCaminos == 0) {
            return true;
        }

        // Esta matriz guarda qué posiciones pueden alcanzarse desde la primera casilla encontrada.
        boolean[][] alcanzable = new boolean[filas][columnas];
        alcanzable[filaInicial][columnaInicial] = true;

        int cantidadAlcanzada = 1;
        boolean huboCambio = true;

        // Mientras se siga encontrando una nueva casilla alcanzable, volvemos a recorrer la matriz.
        while (huboCambio) {

            huboCambio = false;

            for (int i = 1; i < filas - 1; i++) {
                for (int j = 1; j < columnas - 1; j++) {

                    if (esCeldaTransitablePorJugador(i, j)
                            && !alcanzable[i][j]) {

                        boolean tieneVecinoAlcanzable
                                = alcanzable[i - 1][j]
                                || alcanzable[i + 1][j]
                                || alcanzable[i][j - 1]
                                || alcanzable[i][j + 1];

                        if (tieneVecinoAlcanzable) {
                            alcanzable[i][j] = true;
                            cantidadAlcanzada++;
                            huboCambio = true;
                        }
                    }
                }
            }
        }

        return cantidadAlcanzada == cantidadCaminos;
    }
    // GENERACIÓN DE MUROS (respetando la conectividad del tablero)

    // 1. Los muros ya no pueden encerrar al jugador: cada muro se coloca "de prueba" y solo se conserva si, tras colocarlo, el tablero sigue
    //totalmente conectado. Si lo desconecta, se revierte y se prueba con otra posicion.
    // 2. Siempre se reservan al menos 2 celdas libres (jugador + 1 vecina), así que aunque se pida una cantidad enorme de muros (ej. 99 en un 10x10),
    //el algoritmo se detiene justo antes de dejar al jugador sin espacio, colocando la mayor cantidad posible sin romper esa regla.
    public int agregarMuros(int cantidadDeseada) {

        if (cantidadDeseada < 0) {
            cantidadDeseada = 0;
        }

        // Deben quedar espacios para: la salida de la base, todos los poderes, el jugador y al menos un punto.
        int minimoCeldasTransitables = poderes.length + 3;
        int maximoPosible = contarCeldasTransitables() - minimoCeldasTransitables;

        if (maximoPosible < 0) {
            maximoPosible = 0;
        }

        if (cantidadDeseada > maximoPosible) {
            System.out.println("La cantidad de muros se redujo de " + cantidadDeseada + " a " + maximoPosible + " para reservar espacio para jugador, poderes y puntos.");
            cantidadDeseada = maximoPosible;
        }

        this.muros = new Muro[cantidadDeseada];
        this.cantidadMurosColocados = 0;

        int intentosFallidosSeguidos = 0;
        int maximoFallosSeguidos = filas * columnas * 4;

        while (cantidadMurosColocados < cantidadDeseada && intentosFallidosSeguidos < maximoFallosSeguidos) {

            if (contarCeldasTransitables() <= minimoCeldasTransitables) {
                break;
            }

            int[] posicion = obtenerPosicionLibreAleatoria();

            if (posicion[0] == -1 || posicion[1] == -1) {
                break;
            }

            int filaMuro = posicion[0];
            int columnaMuro = posicion[1];

            matriz[filaMuro][columnaMuro] = MURO;

            if (tableroSigueConectado()) {
                muros[cantidadMurosColocados] = new Muro(filaMuro, columnaMuro);
                cantidadMurosColocados++;
                intentosFallidosSeguidos = 0;
            } else {
                matriz[filaMuro][columnaMuro] = VACIO;
                intentosFallidosSeguidos++;
            }
        }

        if (cantidadMurosColocados < cantidadDeseada) {
            System.out.println("Se colocaron " + cantidadMurosColocados + " de " + cantidadDeseada + " muros solicitados: no habia mas espacio posible sin dejar al jugador encerrado.");
        }

        return cantidadMurosColocados;
    }

    // Sobrecarga sin parametros (como pide el PDF): usa una cantidad sugerida
    public void agregarMuros() {
        agregarMuros(calcularCantidadMurosSugerida());
    }

    public int getCantidadMurosColocados() {
        return cantidadMurosColocados;
    }

    // GENERACION DE PUNTOS: llenan TODO el espacio libre que quede (despues de muros, base y poderes), como en el tablero clasico de Pac-Man.
    public void agregarPuntos() {

        int cantidadCeldasLibres = 0;
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                if (matriz[i][j] == VACIO && !esZonaSalidaBase(i, j) && !(i == filaInicialJugador && j == columnaInicialJugador)) {
                    cantidadCeldasLibres++;
                }
            }
        }

        this.puntos = new Punto[cantidadCeldasLibres];
        int indice = 0;

        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                if (matriz[i][j] == VACIO && !esZonaSalidaBase(i, j) && !(i == filaInicialJugador && j == columnaInicialJugador)) {
                    puntos[indice] = new Punto(i, j, 10);
                    matriz[i][j] = PUNTO;
                    indice++;
                }
            }
        }
    }

    // POSICION INICIAL DEL JUGADOR

    private void reservarPosicionInicialJugador() {

        int[] posicionJugador = obtenerPosicionLibreAleatoria();

        this.filaInicialJugador = posicionJugador[0];
        this.columnaInicialJugador = posicionJugador[1];

        if (filaInicialJugador == -1 || columnaInicialJugador == -1) {
            System.out.println("No hay una posición disponible para el jugador.");
        }
    }

    public int[] obtenerPosicionInicialJugador() {

        // Si generarTablero() ya reservo la posicion, devolvemos esa misma.
        if (filaInicialJugador != -1 && columnaInicialJugador != -1) {
            return new int[]{filaInicialJugador, columnaInicialJugador};
        }

        reservarPosicionInicialJugador();
        return new int[]{filaInicialJugador, columnaInicialJugador};
    }

    // BUSQUEDA Y ELIMINACION DE PUNTOS

    public Punto buscarPunto(int fila, int columna) {

        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] != null) {
                if (puntos[i].getFila() == fila && puntos[i].getColumna() == columna) {
                    if (!puntos[i].fueRecolectado()) {
                        return puntos[i];
                    }
                }
            }
        }
        return null;
    }

    public void eliminarPuntoDelTablero(int fila, int columna) {

        if (!estaDentroDelTablero(fila, columna)) {
            return;
        }

        if (matriz[fila][columna] == PUNTO) {
            matriz[fila][columna] = VACIO;
        }
    }

    public int contarPuntosRestantes() {

        int cantidadRestante = 0;

        for (int i = 0; i < puntos.length; i++) {
            if (puntos[i] != null) {
                if (!puntos[i].fueRecolectado()) {
                    cantidadRestante++;
                }
            }
        }
        return cantidadRestante;
    }
    // GENERACIÓN DE PODERES: coloca uno de cada tipo (Poder1, Poder2, Poder3) en rotación, hasta completar la cantidad indicada en el constructor.
    public void agregarPoderes() {

        for (int i = 0; i < poderes.length; i++) {

            int[] posicion = obtenerPosicionLibreAleatoria();
            if (posicion[0] == -1 || posicion[1] == -1) {
                System.out.println("No hay espacio para colocar mas poderes.");
                break;
            }

            int filaPoder = posicion[0];
            int columnaPoder = posicion[1];
            int tipoDePoder = i % 3;
            if (tipoDePoder == 0) {
                poderes[i] = new Poder1(filaPoder, columnaPoder);
            } else if (tipoDePoder == 1) {
                poderes[i] = new Poder2(filaPoder, columnaPoder);
            } else {
                poderes[i] = new Poder3(filaPoder, columnaPoder);
            }
            matriz[filaPoder][columnaPoder] = PODER;
        }
    }

    // BUSQUEDA Y ELIMINACION DE PODERES

    public Poder buscarPoder(int fila, int columna) {

        for (int i = 0; i < poderes.length; i++) {
            if (poderes[i] != null) {
                if (poderes[i].getFila() == fila && poderes[i].getColumna() == columna) {
                    if (!poderes[i].fueRecogido()) {
                        return poderes[i];
                    }
                }
            }
        }
        return null;
    }

    public void eliminarPoderDelTablero(int fila, int columna) {

        if (!estaDentroDelTablero(fila, columna)) {
            return;
        }

        if (matriz[fila][columna] == PODER) {
            matriz[fila][columna] = VACIO;
        }
    }

    // CONSULTA DIRECTA DE UNA CELDA
    // Permite a otras clases (por ejemplo Juego, Enemigo o Jugador) consultar qué hay en una celda sin depender de mostrarTablero().
    // Fuera del tablero devuelve MURO, tratando el "exterior" como no transitable (comportamiento seguro para quien solo quiera preguntar "¿puedo pasar por aquí?").
    public char obtenerCelda(int fila, int columna) {
        if (!estaDentroDelTablero(fila, columna)) {
            return MURO;
        }
        return matriz[fila][columna];
    }

    // BASE CENTRAL DE LOS ENEMIGOS: un bloque de 3x3 con un unico 'B' en el centro exacto del tablero. Las 8 celdas que rodean a la B son muros, menos una: la celda de abajo, que queda abierta como única entrada/salida.
    public void crearBaseEnemigos() {

        // Con menos de TAMANO_MINIMO_PARA_BASE la celda de entrada quedaria pegada al borde exterior y se aislaría del resto del tablero, así que exigimos un minimo que deje al menos una celda libre debajo de la entrada.
        // El constructor ya garantiza este minimo, pero se deja esta validacion como respaldo.
        if (filas < TAMANO_MINIMO_PARA_BASE || columnas < TAMANO_MINIMO_PARA_BASE) {
            System.out.println("El tablero es muy pequeño " + "para crear la base de enemigos.");
            return;
        }

        int filaCentro = filas / 2;
        int columnaCentro = columnas / 2;

        for (int i = filaCentro - 1; i <= filaCentro + 1; i++) {
            for (int j = columnaCentro - 1; j <= columnaCentro + 1; j++) {
                matriz[i][j] = MURO;
            }
        }

        // El centro exacto del bloque 3x3: ahí nacen los enemigos
        matriz[filaCentro][columnaCentro] = BASE;

        // Única entrada/salida de la base: la celda justo debajo del centro
        matriz[filaCentro + 1][columnaCentro] = VACIO;

        baseCreada = true;
    }

    // SALIDA DE LA BASE
    public boolean esSalidaBase(int fila, int columna) {

        if (!baseCreada) {
            return false;
        }

        int filaCentro = filas / 2;
        int columnaCentro = columnas / 2;

        if (fila == filaCentro + 1 && columna == columnaCentro) {
            return true;
        }
        return false;
    }

    // GETTERS DE DIMENSIONES (los usa, por ejemplo, el fantasma para teletransportarse en los bordes)
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}



