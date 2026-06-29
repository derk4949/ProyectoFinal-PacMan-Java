public class Tablero {
    //Atributos basicos de la clase
    public int filas;
    public int columnas;
    public char [][] matriz;
    //Arreglos de objetos
    public Muro [] muros;
    public Poder [] poderes;
    private Punto[] puntos;

    //Constructor
    public Tablero (int _filas, int _columnas, int _cantMuros, int _cantPuntos, int _cantPoderes) {
        filas = _filas;
        columnas = _columnas;
        matriz = new char[filas][columnas];
        muros = new Muro[_cantMuros];
        poderes = new Poder[_cantPoderes];
        this.puntos = new Punto[_cantPuntos];

    }

    public void generarTablero () {
        //Tablero();
        for (int i=0 ; i<matriz.length ; i++){
            for (int j = 0; j<matriz[i].length ; j++){
                if (i==0 || i==filas-1 || j==0 || j==columnas-1){
                    matriz[i][j] = '#';
                }else{
                    matriz[i][j] = ' ';
                }
            }
        }
        System.out.println();
    }

    public void mostrarTablero () {
        //Tablero();
        for (int i=0 ; i<matriz.length ; i++){
            for (int j = 0; j<matriz[0].length ; j++){
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
