package practicacaso2;

public class ListaCircular {

    NodoListaCircular cabeza;
    NodoListaCircular ultimo;

    public void inserta(Objeto o) {
        if (cabeza == null) {
            cabeza = new NodoListaCircular(o);
            ultimo = cabeza;
            ultimo.setSiguiente(cabeza);
        } else {
            if (o.getId() < cabeza.getDato().getId()) {
                //Insertamos al inicio
                NodoListaCircular auxiliar = new NodoListaCircular(o);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
                ultimo.setSiguiente(cabeza);
            } else {
                if (ultimo.getDato().getId() < o.getId()) {
                    //Insertamos al final
                    NodoListaCircular auxiliar = new NodoListaCircular(o);
                    ultimo.setSiguiente(auxiliar);
                    ultimo = auxiliar;
                    ultimo.setSiguiente(cabeza);
                } else {
                    //Insertamos en el medio de la lista
                    NodoListaCircular auxiliar = cabeza;
                    while (auxiliar.getSiguiente().getDato().getId() < o.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoListaCircular temporal = new NodoListaCircular(o);
                    temporal.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(temporal);
                }
            }
        }
    }

    public Cola pasarACola() {
        Cola cola = new Cola();
        NodoListaCircular actual = cabeza;
        do {
            NodoCola nodoCola = new NodoCola(actual.getDato(), null);
            cola.encolar(nodoCola);
            actual = actual.getSiguiente();
        } while (actual != cabeza);
        return cola;
    }

    public String imprimirSiguienteDelUltimo() {
        return ultimo.getSiguiente().toString();
    }

    public NodoListaCircular getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaCircular cabeza) {
        this.cabeza = cabeza;
    }

    public NodoListaCircular getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoListaCircular ultimo) {
        this.ultimo = ultimo;
    }

    @Override
    public String toString() {
        String respuesta = "Lista circular: \n";

        NodoListaCircular auxiliar = cabeza;

        if (auxiliar != null) {
            //Para determinar que no está vacía
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
            while (auxiliar != cabeza) {
                respuesta += auxiliar.toString() + "\n";
                auxiliar = auxiliar.getSiguiente();
            }
        } else {
            respuesta += "Vacía";
        }

        return respuesta;
    }
}
