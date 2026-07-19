import java.util.Random;
public class Tablero {
    // Atributos basicos de la clase
    private int filas;
    private int columnas;
    private char[][] matriz;

    //Atributos de los objetos
    private Muro[] muros;
    private Punto[] puntos;
    private Poder1[] poderes;

    private boolean baseCreada;

    //Constructor
    public Tablero (int _filas, int _columnas, int _cantMuros, int _cantPuntos, int _cantPoderes) {
        this.filas = _filas;
        this.columnas = _columnas;
        this.matriz = new char[filas][columnas];
        this.muros = new Muro[_cantMuros];
        this.poderes = new Poder1[_cantPoderes];
        this.puntos = new Punto[_cantPuntos];
    }

    //Metodos
    public void generarTablero () { // Genera el tablero vacío y coloca los bordes
        for (int i=0 ; i<matriz.length ; i++){
            for (int j = 0; j<matriz[i].length ; j++){
                if (i==0 || i==filas-1 || j==0 || j==columnas-1){
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

    public boolean esPosicionSalidaBase(int fila, int columna) {

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

    public void mostrarTablero () { //Muestra la matriz en consola
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
    public void colocarJugador(Jugador jugador) {
        // Pendiente: se completará cuando la clase Jugador esté terminada
        int filajugador = jugador.getFila();
        int columnajugador = jugador.getColumna();

        this.matriz[filajugador][columnajugador] = 'J';
    }
    public void colocarEnemigos() {
        // Pendiente: se completará cuando la clase Enemigos esté terminada
    }

    public void colocarEnemigos2(Enemigo2 enemigo2) {
        int filaEnemigo2 = enemigo2.getFila();
        int columnaEnemigo2 = enemigo2.getColumna();

        this.matriz[filaEnemigo2][columnaEnemigo2] = 'A';
        // Pendiente: se completará cuando la clase Enemigos esté terminada
    }

    public void limpiarPosicion(int nuevaFila, int nuevaColumna){
        this.matriz[nuevaFila][nuevaColumna] = ' ';
    }

}





class PruebaTablero{
    public static void main(String[]args){
        Tablero tabla = new Tablero(10,10,6,4,5);

        tabla.generarTablero();
        tabla.mostrarTablero();


    }
}
