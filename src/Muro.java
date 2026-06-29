public class Muro {

    //Atributos
    private int fila;
    private int columna;

    //Constructor
    public Muro(int _fila, int _columna) {
        this.fila = _fila;
        this.columna = _columna;
    }

    //Metodos
    public int[] obtenerPosicion(){ //Duvuelve la ubicación del muro
        return new int[]{fila, columna};
    }
}