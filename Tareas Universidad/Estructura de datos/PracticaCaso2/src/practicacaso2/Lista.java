package practicacaso2;

public class Lista {

    private NodoLista cabeza;

    public void insertar(Objeto o) {
        if (cabeza == null) {
            cabeza = new NodoLista(o);
        } else {
            if (o.getId() < cabeza.getDato().getId()) {
                NodoLista auxiliar = new NodoLista(o);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                if (cabeza.getSiguiente() == null) {
                    NodoLista nuevo = new NodoLista(o);
                    cabeza.setSiguiente(nuevo);
                } else {
                    NodoLista auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null
                            && auxiliar.getSiguiente().getDato().getId() < o.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }
                    NodoLista otroAuxiliar = new NodoLista(o);
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }
        }
    }

    public NodoLista getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoLista cabeza) {
        this.cabeza = cabeza;
    }

    @Override
    public String toString() {
        return "Lista{" + "cabeza=" + cabeza + '}';
    }

}
