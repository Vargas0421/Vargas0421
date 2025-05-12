package pcaso2;

public class ListaCategorias {// esto es una lista simple circular 

    private NodoListaCategorias cabeza;
    private NodoListaCategorias ultimo;

    public NodoListaCategorias buscarCategoria(int id) {
        if (cabeza == null) {
            return null; // La lista está vacía
        } else {
            NodoListaCategorias auxiliar = cabeza;
            do {
                if (auxiliar.getDato().getId() == id) {
                    return auxiliar; // Se encontró la categoría
                }
                auxiliar = auxiliar.getSiguiente();
            } while (auxiliar != cabeza); // Mientras no se haya recorrido toda la lista circular
            return null; // No se encontró la categoría
        }
    }

    public void insertar(DatoListaCategorias datoCategoria) {
        NodoListaCategorias nuevoNodo = new NodoListaCategorias(datoCategoria);

        if (cabeza == null) {
            cabeza = nuevoNodo;
            ultimo = cabeza;
            ultimo.setSiguiente(cabeza);
        } else {
            if (datoCategoria.getId() < cabeza.getDato().getId()) {
                nuevoNodo.setSiguiente(cabeza);
                cabeza = nuevoNodo;
                ultimo.setSiguiente(cabeza);
            } else {
                if (ultimo.getDato().getId() < datoCategoria.getId()) {
                    ultimo.setSiguiente(nuevoNodo);
                    ultimo = nuevoNodo;
                    ultimo.setSiguiente(cabeza);
                } else {
                    NodoListaCategorias auxiliar = cabeza;
                    while (auxiliar.getSiguiente().getDato().getId() < datoCategoria.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }
                    nuevoNodo.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(nuevoNodo);
                }
            }
        }
    }

    public NodoListaCategorias getCabeza() {
        return cabeza;
    }

    public void setCabeza(NodoListaCategorias cabeza) {
        this.cabeza = cabeza;
    }

    public NodoListaCategorias getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoListaCategorias ultimo) {
        this.ultimo = ultimo;
    }

    /*@Override
    public String toString() {
        String respuesta = "Lista de categorias: \n";

        if (cabeza != null) {
            NodoListaCategorias aux = cabeza;

            respuesta += aux.toString() + "\n";

            aux = aux.getSiguiente();

            while (aux != cabeza) {
                respuesta += aux.toString() + "\n";
                aux = aux.getSiguiente();
            }
        } else {
            respuesta += "Vacía";
        }

        return respuesta;
    }*/
    public String toString() {
        StringBuilder sb = new StringBuilder();
        NodoListaCategorias auxiliar = cabeza;

        // Recorre la lista circular de categorías
        do {
            sb.append("Categoria: ").append(auxiliar.getDato()).append("\n");
            sb.append("Peliculas: \n");

            // Accede a la lista de películas dentro de cada nodo de categoría
            NodoListaPeliculas auxiliarPeliculas = auxiliar.getPeliculas().getCabeza();
            while (auxiliarPeliculas != null) {
                sb.append(auxiliarPeliculas.getDato()).append("\n");
                auxiliarPeliculas = auxiliarPeliculas.getSiguiente();
            }
            sb.append("-----------------------\n");

            auxiliar = auxiliar.getSiguiente();
        } while (auxiliar != cabeza);

        return sb.toString();
    }

}
