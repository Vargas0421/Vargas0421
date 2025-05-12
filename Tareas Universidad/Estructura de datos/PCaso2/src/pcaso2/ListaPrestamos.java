package pcaso2;

public class ListaPrestamos {

    private NodoListaPrestamos cabeza;
    private NodoListaPrestamos ultimo;
    private int cantidadNodos;

    public void insertaMejorado(DatoListaPrestamos d) {
        //Paso 1, de la presentación
        if (cabeza == null) { // en dado caso que la  este vacia 
            cabeza = new NodoListaPrestamos(d); // seriamos el nodo como la cabeza
            ultimo = cabeza; // setiamos la misma cabeza como la cola
            cabeza.setAnterior(ultimo); //setiamos que el anterior de la cabeza es el ultimo osea la misma cabeza
            cabeza.setSiguiente(ultimo);// setiamos que el siguente de la cabeza es el ultimo que es la cabeza
            ultimo.setSiguiente(cabeza);// setiamos que el siguente del ultimo es cabeza 
            ultimo.setAnterior(cabeza);// setiamos que el anterior del ultimo es la cabeza 
            cantidadNodos++;
        } else {
            if (cabeza.getDato().getCedula() > d.getCedula()) {// en caso de tener un id menor a la cabeza osea una nueva cabeza 
                NodoListaPrestamos aux = new NodoListaPrestamos(d);// setiamos una varible aux com oel nodo a ingresarr (p)
                aux.setSiguiente(cabeza);// a ser menor setiamos que el siguente del mismo es la cabeza
                cabeza.setAnterior(aux);// setiamos que el anterior de la cabeza es es el nuevo aux
                cabeza = aux;// setiamos el nuevo aux como la nueva cabeza
                cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                cantidadNodos++;

            } else {
                if (d.getCedula() > ultimo.getDato().getCedula()) {// ingresar algo despues del ultimo ( un nuevo ultimo) 
                    NodoListaPrestamos aux = new NodoListaPrestamos(d);// setiamos una varible aux com oel nodo a ingresarr (p)
                    aux.setAnterior(ultimo);// setiamos que el anterior del aux es el ultimo 
                    ultimo.setSiguiente(aux);//refrescamos el sigiuente del ultimo como el nuevo aux 
                    ultimo = aux;// refrescamos el ultimo como el aux que creamos
                    ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                    cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                    cantidadNodos++;

                } else {
                    if (ultimo.getDato().getCedula() > d.getCedula() && ultimo.getAnterior().getDato().getCedula() < d.getCedula()) {  // en caso de insertar antes del ultimo 
                        NodoListaPrestamos aux = new NodoListaPrestamos(d);// setiamos una varible aux com oel nodo a ingresarr (p)
                        ultimo.getAnterior().setSiguiente(aux);// setiamos la refenrencia del anterior al ultimo 
                        aux.setAnterior(ultimo.getAnterior().getAnterior());//setia,os el anterior del nodo nuevo 
                        ultimo.setAnterior(aux);// refrescamos el anterior del ultimo 
                        aux.setSiguiente(ultimo);// setiamos el siguenre del del aux osea el ultimo por la funcion  
                        cantidadNodos++;
                    } else {
                        NodoListaPrestamos aux = cabeza;
                        NodoListaPrestamos auxU = ultimo;
                        while (aux.getDato().getCedula() <= d.getCedula() && d.getCedula() <= auxU.getDato().getCedula()) { // la condicino de parada es cuando el id sea mayor a el aux y si el id es menor al auxU 
                            aux = aux.getSiguiente();// avanzamos al siguiente aux
                            auxU = auxU.getAnterior();//resoresedmos de auxU
                        }

                        aux = aux.getAnterior();// ponemos esto porque para que se ejecute el if necesitamos el el aux sea menor al id de p
                        if (aux.getDato().getCedula() < d.getCedula() && aux.getSiguiente().getDato().getCedula() > d.getCedula()) {// verificamos si debemos incertarlo de la cabeza hacia adelante 
                            aux = aux.getSiguiente();// setimos el aux al sigueinte del mismmo 
                            NodoListaPrestamos temp = new NodoListaPrestamos();//creamos un nuevo nodo el cual insertaremos 
                            temp.setAnterior(aux.getAnterior()); // seriamos el anterior del nuevo nodo al anteriror del aux
                            temp.setSiguiente(aux);// seriamos el siguente del nodo el auz 
                            aux.setAnterior(temp);// setiamos el nuevo atras del aux el nodo que creamos 
                            temp.getAnterior().setSiguiente(temp);// setiamos que el temp fuese el siguente del anterior 
                            cantidadNodos++;
                        } else if (auxU.getDato().getCedula() < d.getCedula() && auxU.getSiguiente().getDato().getCedula() > d.getCedula()) { // este funca
                            NodoListaPrestamos temp = new NodoListaPrestamos(d); // se crea el nuevo nodo 
                            temp.setAnterior(auxU); // setiamos que el anterior del nuevo nodo es la posicion en la que paramos
                            temp.setSiguiente(auxU.getSiguiente()); // el sigueinte de ese noso es el siguinete de la posicion en la que paramos
                            auxU.setSiguiente(temp);// setiamos que el sigueinte la posicion en la que paramos es el nuevo nodo 
                            aux.getSiguiente().getSiguiente().setAnterior(temp);//setiamos el nuevo nodo como el anetior del antiguo siguente de la posicion en la que paramos
                            cantidadNodos++;
                        }
                    }
                }
            }
        }

    }

    public void eliminar(DatoListaPrestamos d) {
        NodoListaPrestamos aux = cabeza;
        NodoListaPrestamos auxU = ultimo;

        // Caso donde el nodo a eliminar es la cabeza
        if (d.getCedula() == aux.getDato().getCedula()) {
            cabeza = cabeza.getSiguiente();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            cantidadNodos--;
            return;
        }

        // Caso donde el nodo a eliminar es el último
        if (d.getCedula() == auxU.getDato().getCedula()) {
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            cantidadNodos--;
            return;
        }

        // Caso donde el nodo a eliminar está en medio de la lista
        aux = cabeza.getSiguiente(); // Empezamos desde el segundo nodo
        while (aux != cabeza) { // Continuamos hasta que volvamos a la cabeza
            if (aux.getDato().getCedula() == d.getCedula()) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                cantidadNodos--;
                return;
            }
            aux = aux.getSiguiente();

        }
    }

    public DatoListaPrestamos extrae(int id) {
        DatoListaPrestamos o = null; // creamos la variable p vacia para retornarla si se enceuntra el id deseado 
        if (cabeza != null) { // se continua si cabeza tiene algun dato 
            if (cabeza.getDato().getCedula() == id) { // si el datro es cabeza se continua con lo siguiente 
                o = cabeza.getDato();
                cabeza = cabeza.getSiguiente();// setiamos el sigueinte de cabeza la nueva cabeza 
                cabeza.setAnterior(ultimo);// refrescamos la referencia del ultimo 
                ultimo.setSiguiente(cabeza);//refrescamos la referrencia del primero 
                System.out.println("Esto es lo que se extrae" + o); // se meustra lo que se extrae 

            } else if (ultimo.getDato().getCedula() == id) {// vemos si el que extraemos es el ultimo 
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(cabeza);
                cabeza.setAnterior(ultimo);
                System.out.println("Esto es lo que se extrae" + o); // se meustra lo que se extrae 

            } else {
                NodoListaPrestamos aux = cabeza; // creamos un puntero llamado aux que empieza en cabeza 
                while (aux.getSiguiente() != cabeza && cabeza.getDato().getCedula() == id)// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
                {
                    aux = aux.getSiguiente();
                    if (aux.getSiguiente() != cabeza && cabeza.getDato().getCedula() == id) {// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
                        o = aux.getAnterior().getDato();
                        aux.setSiguiente(aux.getSiguiente().getSiguiente());
                        aux.setAnterior(aux.getAnterior());
                        System.out.println("Esto es lo que se extrae" + o); // se meustra lo que se extrae 

                    }
                }
            }
        }

        return o;
    }

    public boolean existe(int cedula) {
        boolean respuesta = false;// se setea la variable respuesta a falsa por defecto 

        NodoListaPrestamos auxiliar = cabeza;// se define al auxiliar como la cabeza para iniciar 

        while (auxiliar != null) {// se verifica que no este vacia la fila
            if (auxiliar.getDato().getCedula() == cedula) {// se compara el dato de la posicion de aux con el id ingresado
                respuesta = true;// si esto es verdaddero se cambia la variable respuesta a verdadera
                break;
            } else {// de no cumplirse se avanza a la siguiente posicion
                auxiliar = auxiliar.getSiguiente();
            }
        }

        return respuesta; // se decuelve la respuesta una vez terminada
    }

    @Override
    public String toString() {
        return "ListaPrestamos{" + "cabeza=" + cabeza + ", ultimo=" + ultimo + ", cantidadNodos=" + cantidadNodos + '}';
    }

}
