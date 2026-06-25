public class Tablero {
    public int filas;
    public int columnas;
    public char [][] matriz;

    public Tablero (int _filas, int _columnas) {
        filas = _filas;
        columnas = _columnas;
        matriz = new char[filas][columnas];
    }
    public void generarTablero () {
        //Tablero();
        for (int i=0 ; i<matriz.length ; i++){
            for (int j = 0; j<matriz[0].length ; j++){
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
