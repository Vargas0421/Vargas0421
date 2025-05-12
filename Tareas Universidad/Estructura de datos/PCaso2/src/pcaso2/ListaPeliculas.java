package pcaso2;

public class ListaPeliculas {

    private NodoListaPeliculas cabeza;

    public Pelicula insertar(Pelicula p) {

        if (cabeza == null) {
            cabeza = new NodoListaPeliculas(p);
        } else {
            if (p.getId() < cabeza.getDato().getId()) {
                NodoListaPeliculas auxiliar = new NodoListaPeliculas(p);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                if (cabeza.getSiguiente() == null) {
                    NodoListaPeliculas nuevo = new NodoListaPeliculas(p);
                    cabeza.setSiguiente(nuevo);
                } else {
                    NodoListaPeliculas auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null
                            && auxiliar.getSiguiente().getDato().getId() < p.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }
                    NodoListaPeliculas otroAuxiliar = new NodoListaPeliculas(p);
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }
        }
        return p;
    }

    public NodoListaPeliculas getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaPeliculas cabeza) {
        this.cabeza = cabeza;
    }

    @Override
    public String toString() {
        NodoListaPeliculas auxiliar = cabeza;
        String respuesta = "";

        while (auxiliar != null) {
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
        }

        return respuesta;
    }

    

}
