package pcaso2;

public class NodoListaCategorias {

    private NodoListaCategorias siguiente;
    private DatoListaCategorias dato;
    private ListaPeliculas peliculas;

    public ListaPeliculas getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ListaPeliculas peliculas) {
        this.peliculas = peliculas;
    }

    public NodoListaCategorias(DatoListaCategorias dato) {
        this.dato = dato;
        this.siguiente = null;
        this.peliculas = new ListaPeliculas(); // Inicializamos la lista de películas

    }

    public NodoListaCategorias() {
    }

    public NodoListaCategorias getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaCategorias siguiente) {
        this.siguiente = siguiente;
    }

    public DatoListaCategorias getDato() {
        return dato;
    }

    public void setDato(DatoListaCategorias dato) {
        this.dato = dato;

    }

    @Override
    public String toString() {
        return "NodoListaCategorias{" + dato + '}';
    }

}
