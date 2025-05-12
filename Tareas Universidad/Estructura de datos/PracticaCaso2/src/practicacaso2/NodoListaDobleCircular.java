package practicacaso2;

public class NodoListaDobleCircular {

    private Objeto dato;
    private NodoListaDobleCircular anterior;
    private NodoListaDobleCircular siguiente;

    public NodoListaDobleCircular(Objeto dato) {
        this.dato = dato;
    }

    public NodoListaDobleCircular() {
    }

    public Objeto getDato() {
        return dato;
    }

    public void setDato(Objeto dato) {
        this.dato = dato;
    }

    public NodoListaDobleCircular getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoListaDobleCircular anterior) {
        this.anterior = anterior;
    }

    public NodoListaDobleCircular getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaDobleCircular siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return dato.toString();
    }

}
