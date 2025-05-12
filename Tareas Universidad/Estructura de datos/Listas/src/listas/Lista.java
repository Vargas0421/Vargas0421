
package listas;
public class Lista {

    private NodoLista cabeza;

    public void insertar(Persona p) {
        //1. La lista está vacía.
        if (cabeza == null) // si la lista esta vacia 
        {
            cabeza = new NodoLista(p);// se anade algo a la cabeza
        } else {
            //2.se anade algo antes de la cabeza
            if (p.getId() < cabeza.getDato().getId()) {//se inserta algo antes de la cabeza
                NodoLista auxiliar = new NodoLista(p);
                auxiliar.setSiguiente(cabeza);
                cabeza = auxiliar;
            } else {
                //3.Insertar despues de la cabeza
                if (cabeza.getSiguiente() == null) {
                    NodoLista nuevo = new NodoLista(p);
                    cabeza.setSiguiente(nuevo);
                } else {
                    //4.para anadir algo al final del todo o en el medio
                    NodoLista auxiliar = cabeza;
                    while (auxiliar.getSiguiente() != null
                            && auxiliar.getSiguiente().getDato().getId() < p.getId()) {
                        auxiliar = auxiliar.getSiguiente();
                    }

                    NodoLista otroAuxiliar = new NodoLista(p);
                    otroAuxiliar.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(otroAuxiliar);
                }
            }
        }
    }

    public boolean existe(int id) {
        boolean respuesta = false;// se setea la variable respuesta a falsa por defecto 

        NodoLista auxiliar = cabeza;// se define al auxiliar como la cabeza para iniciar 

        while (auxiliar != null) {// se verifica que no este vacia la fila
            if (auxiliar.getDato().getId() == id) {// se compara el dato de la posicion de aux con el id ingresado
                respuesta = true;// si esto es verdaddero se cambia la variable respuesta a verdadera
                break;
            } else {// se no cumplirse se avanza a la siguiente posicion
                auxiliar = auxiliar.getSiguiente();
            }
        }

        return respuesta; // se decuelve la respuesta una vez terminada
    }

    public void modifica(Persona p) {
        NodoLista auxiliar = cabeza; // se empieza por a  cabeza

        while (auxiliar != null)// se verifica que el aux no sea null osea que la fila no este vacia
        {
            if (auxiliar.getDato().getId() == p.getId())// se eldato del aux y el id del aux es el mismo que el que se busca 
            {
                auxiliar.getDato().setNombre(p.getNombre());// se cambia la informacion 
                break;
            } else {
                auxiliar = auxiliar.getSiguiente();//de no ser el mismo id se pasa al sigueinte nodo en la lisa 
            }
        }
    }

    public void elimina(int id) {
        NodoLista auxiliar = cabeza; // se setea al auxiliar a la cabeza de la lista 
        NodoLista anterior = null; // el anterior se setea a null para isarlo mas adelante

        while (auxiliar != null)// se verifica que la fila no este vacia 
        {
            if (auxiliar.getDato().getId() == id)// se vusca el id deseado a eliminar 
            {
                if (auxiliar == cabeza)// si el nodo a eliminar es la cabeza se sete el siguiente nodo como la cabeza 
                {
                    cabeza = cabeza.getSiguiente();
                    auxiliar.setSiguiente(null);
                    break;
                } else {
                    anterior.setSiguiente(auxiliar.getSiguiente());
                    auxiliar.setSiguiente(null);
                    break;
                }
            } else// no hay dato para eliminar 
            {
                anterior = auxiliar;
                auxiliar = auxiliar.getSiguiente();
            }
        }
    }

    @Override
    public String toString() {
        NodoLista auxiliar = cabeza;
        String respuesta = "Lista: \n";

        while (auxiliar != null) {
            respuesta += auxiliar.toString() + "\n";
            auxiliar = auxiliar.getSiguiente();
        }

        return respuesta;
    }

}
