import java.util.Random;

public class Tablero {

    // ============================================================
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

    // Tamaño mínimo de tablero para poder crear la base 3x3 de enemigos
    // dejando al menos una celda libre debajo de la entrada (ver
    // crearBaseEnemigos()).
    private static final int TAMANO_MINIMO_PARA_BASE = 7;

    // ATRIBUTOS DEL TABLERO

    // Cantidad de filas y columnas que tendrá el tablero
    private int filas;
    private int columnas;

    // Matriz donde se guardan los elementos fijos del mapa:
    // MURO  = muro
    // BASE  = base de enemigos (una sola celda, en el centro del bloque 3x3)
    // PUNTO = punto
    // PODER = poder
    // VACIO = espacio vacío
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

    // Posición que se reserva antes de llenar los caminos con puntos.
    // Así el jugador nunca termina creado en (-1, -1).
    private int filaInicialJugador;
    private int columnaInicialJugador;

    // Referencia a los enemigos (Perseguidor, Aleatorio, Fantasma, o los que sean)
    private Enemigo[] enemigos;

    // Indica si el tablero alcanzó a crear la base de enemigos. Antes,
    // obtenerPosicionBase()/esPosicionBase() asumían que la base siempre
    // existía, pero crearBaseEnemigos() se negaba a crearla en tableros
    // menores a 7x7. Con esta bandera esos métodos ya no devuelven una
    // posición de base que en realidad no está en el tablero.
    private boolean baseCreada;

    // Única instancia de Random para todo el tablero. Antes se creaba una
    // nueva instancia en cada llamada a obtenerPosicionLibreAleatoria(),
    // lo cual era innecesario.
    private Random aleatorio;

    // CONSTRUCTOR
    // Ya no recibe cantidad de muros ni de puntos: los muros se definen en
    // generarTablero(cantidadMurosDeseada) según lo que elija el usuario en el
    // menú, y los puntos ahora llenan automáticamente todo el espacio libre
    // que quede (como en el Pac-Man original).
    public Tablero(int _filas, int _columnas, int _cantidadPoderes) {

        if (_filas < TAMANO_MINIMO_PARA_BASE || _columnas < TAMANO_MINIMO_PARA_BASE) {
            System.out.println("El tamaño minimo del tablero es " + TAMANO_MINIMO_PARA_BASE
                    + "x" + TAMANO_MINIMO_PARA_BASE + " para poder crear la base de enemigos. "
                    + "Se ajusto el tamaño solicitado (" + _filas + "x" + _columnas + ").");
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

