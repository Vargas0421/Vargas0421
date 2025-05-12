package pcaso2;

public class NodoListaPeliculas {
   private Pelicula dato;
    private NodoListaPeliculas siguiente;

    public NodoListaPeliculas(Pelicula dato) {
        this.dato = dato;
    }

    public Pelicula getDato() {
        return dato;
    }

    public void setDato(Pelicula dato) {
        this.dato = dato;
    }

    public NodoListaPeliculas getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaPeliculas siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoLista{" + "dato=" + dato + '}';
    }
    
    
}