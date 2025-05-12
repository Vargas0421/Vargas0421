package practicacaso2;


  public class NodoLista {
    private Objeto dato;
    private NodoLista siguiente;

    public NodoLista(Objeto dato) {
        this.dato = dato;
    }

    public Objeto getDato() {
        return dato;
    }

    public void setDato(Objeto dato) {
        this.dato = dato;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoLista{" + "dato=" + dato + '}';
    }
    
    
}