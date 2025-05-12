package practicacaso2;

public class Cola {

    private NodoCola frente;
    private NodoCola ultimo;

    public NodoCola getFrente() {
        return frente;
    }

    public void setFrente(NodoCola frente) {
        this.frente = frente;
    }

    public NodoCola getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoCola ultimo) {
        this.ultimo = ultimo;
    }

    public void encolar(NodoCola elemento) {
        if (frente == null) {
            frente = elemento;
            ultimo = elemento;

        } else {
            ultimo.setAtras(elemento);
            ultimo = elemento;
        }
    }

    public NodoCola deEncolar() {
        NodoCola actual = frente;
        if (frente != null) {
            frente = frente.getAtras();
            actual.setAtras(null);
        }
        return actual;

    }

    public int imprimirRecursivo() {// se llama al metodo de abajo el cual es el que hace todo 
        return imprimirR(frente);// se llama al medoto empezando en el frente 
    }

    private int imprimirR(NodoCola n) { // se crea el metodo con un parametro 
        if (n != null) {// en caso de que n sea distinta a null se sigue 
            return n.getDato().getId() + imprimirR(n.getAtras());// se devuleve el dato de n en un toString y se hace el llamado recursivo 
        }
        return 0;// al final se llama a un string vacio 
    }

    private void colaAListaDobCircR(NodoCola nodo, ListaDobleCircular l) {// se hace un llamado a una funcino con parametros de NodoCola y la listadoblecirc 
        if (nodo != null) {//en caso de que n sea distinta a null se sigue 
            l.insertaMejorado(nodo.getDato());// se inserta un nodo con el dato del mismmo en la lista 
            colaAListaDobCircR(nodo.getAtras(), l);// se hace el llamado de la recursividad con el nodo anteriorr de la cola 

        }
    }

    public ListaDobleCircular colaAListaDobCirc() {// se nombra el metodo 
        ListaDobleCircular l = new ListaDobleCircular();// se crea la lista doble circ 
        colaAListaDobCircR(frente, l);// se establece la posicion con el parametro de la lista creada 
        return l;// se devuleve la lista 
    }
    
    public void valorPar(Cola cola) {
    NodoCola nodoPar = primerPar(cola.getFrente());
    if (nodoPar != null) {
        System.out.println("El primer valor par en la cola es: " + nodoPar.getDato().getId());
    } else {
        System.out.println("No se encontro ningun valor par en la cola.");
    }
}

    private NodoCola primerPar(NodoCola nodo) {
        while (nodo != null) {// si el nodo es distinto de null 
            if (nodo.getDato().getId() % 2 == 0) {// si es divisble entre 2 y el residuo es 0  se deveulve dicho nodo 
                return nodo; // Devuelve el nodo que contiene el primer número par encontrado
            }// de no serlo se mueve atras
            nodo = nodo.getAtras(); // Mueve al siguiente nodo
        }
        return null; // No habia ningun valor par 
    }

    public String imprimirCola() {// se crea el metodo para imprimir la cola de manera normal con un while 
        String respuesta = "";// se crea un String vacio 
        NodoCola actual = frente;// se crea una variable y se iguala al frente 
        while (actual != null) {// nmientas la variable sea distrinta a null se prosigue 
            respuesta += actual.getDato() + " - ";// se concatenana las respestas 
            actual = actual.getAtras();//se avanza al sigueinte nodo con eun getAtras
        }
        return respuesta;// se devuleven las respuestas una vez terminado el while 
    }

    @Override
    public String toString() {// un toString :} 
        return "Cola{" + "frente=" + frente + ", ultimo=" + ultimo + '}';
    }

}
