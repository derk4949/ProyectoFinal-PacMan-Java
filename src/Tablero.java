import java.util.Random;
public class Tablero {
    // Atributos basicos de la clase
    private int filas;
    private int columnas;
    private char[][] matriz;

    //Atributos de los objetos
    private Muro[] muros;
    private Punto[] puntos;
    private Poder[] poderes;

    //Constructor
    public Tablero (int _filas, int _columnas, int _cantMuros, int _cantPuntos, int _cantPoderes) {
        this.filas = _filas;
        this.columnas = _columnas;
        this.matriz = new char[filas][columnas];
        this.muros = new Muro[_cantMuros];
        this.poderes = new Poder[_cantPoderes];
        this.puntos = new Punto[_cantPuntos];
    }

    //Metodos
    public void generarTablero () { // Genera el tablero vacío y coloca los bordes
        for (int i=0 ; i<matriz.length ; i++){//filas
            for (int j = 0; j<matriz[i].length ; j++){//columnas
                if (i==0 || i==filas-1 || j==0 || j==columnas-1){//i pregunta si esta en a fila y j en la columna
                    matriz[i][j] = '#';
                }else{
                    matriz[i][j] = ' ';
                }
            }
        }
        //Aqui llamamos al metodo que genera los muros
        agregarMurosAleatorios();
    }

    public void agregarMurosAleatorios() { //Generará los muros en posiciones aleatorias
        for (int i=0; i< muros.length ; i++){
            int[] posicion = obtenerPosicionLibreAleatoria();
            int filaMuro = posicion[0];
            int columnaMuro = posicion[1];
            muros[i]= new Muro(filaMuro, columnaMuro);
            matriz[filaMuro][columnaMuro] = '#';
        }
    }

    public boolean estaDentroDelTablero(int fila, int columna) { //Verifica que una posición exista dentro de la matriz
        if (fila >= 0 && fila < filas && columna >= 0 && columna< columnas){
            return true;
        }
        return false;
    }

    public boolean esPosicionInterior(int fila, int columna) { //Verifica que una posición no pertenezca a los bordes
        if (fila > 0 && fila < filas - 1 && columna > 0 && columna < columnas - 1){
            return true;
        }
        return false;
    }

    public boolean estaPosicionLibre(int fila, int columna) { //Verifica que una posición no tenga otro elemento
        if (esPosicionInterior(fila,columna) && matriz[fila][columna] == ' ' ){
            return true;
        }
        return false;
    }

    public boolean esMovimientoValido(int fila, int columna) { //Verifica si el jugador o enemigo puede desplazarse
        if (estaDentroDelTablero(fila,columna) && matriz[fila][columna] !='#'){
            return true;
        }
        return false;
    }

    public int[] obtenerPosicionLibreAleatoria() {//Devuelve una posición aleatoria que se encuentre libre
        Random aleatorio = new Random();
        int filaAleatoria;
        int columnaAleatoria;
        do {
            //Genera una posición
            filaAleatoria = aleatorio.nextInt(filas -2) +1;
            columnaAleatoria = aleatorio.nextInt(columnas -2) +1;

        }while (!estaPosicionLibre(filaAleatoria, columnaAleatoria));

        return new int[]{filaAleatoria, columnaAleatoria};
    }

    public void mostrarTablero () { //Imprime lo que encuentra en cada casilla de la matriz
        for (int i=0 ; i<matriz.length ; i++){
            for (int j = 0; j<matriz[i].length ; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Pendientes hasta recibir las otras clases
    public void agregarPuntos() {
        // Pendiente: se completará cuando la clase Punto esté terminada
    }
    public void agregarPoderes() {
        // Pendiente: se completará cuando la clase Poder esté terminada
    }
    public void colocarJugador() {
        // Pendiente: se completará cuando la clase Jugador esté terminada
    }
    public void colocarEnemigos() {
        // Pendiente: se completará cuando la clase Enemigos esté terminada
    }
}

