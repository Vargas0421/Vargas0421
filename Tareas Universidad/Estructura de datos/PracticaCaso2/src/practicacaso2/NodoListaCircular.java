package practicacaso2;

public class NodoListaCircular {

    private NodoListaCircular siguiente;
    private Objeto dato;

    public NodoListaCircular(Objeto dato) {
        this.siguiente = siguiente;
        this.dato = dato;
    }

    public NodoListaCircular() {
    }

    public NodoListaCircular getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaCircular siguiente) {
        this.siguiente = siguiente;
    }

    public Objeto getDato() {
        return dato;
    }

    public void setDato(Objeto dato) {
        this.dato = dato;
    }

    @Override
    public String toString() {
        return this.dato.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
