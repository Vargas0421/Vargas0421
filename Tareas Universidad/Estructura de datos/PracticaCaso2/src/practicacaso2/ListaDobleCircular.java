package practicacaso2;

public class ListaDobleCircular {

    private NodoListaDobleCircular cabeza;
    private NodoListaDobleCircular ultimo;
    private int cantidadNodos;

    public void insertaMejorado(Objeto o) {
        //Paso 1, de la presentación
        if (cabeza == null) { // en dado caso que la  este vacia 
            cabeza = new NodoListaDobleCircular(o); // seriamos el nodo como la cabeza
            ultimo = cabeza; // setiamos la misma cabeza como la cola
            cabeza.setAnterior(ultimo); //setiamos que el anterior de la cabeza es el ultimo osea la misma cabeza
            cabeza.setSiguiente(ultimo);// setiamos que el siguente de la cabeza es el ultimo que es la cabeza
            ultimo.setSiguiente(cabeza);// setiamos que el siguente del ultimo es cabeza 
            ultimo.setAnterior(cabeza);// setiamos que el anterior del ultimo es la cabeza 
            cantidadNodos++;
        } else {
            if (cabeza.getDato().getId() > o.getId()) {// en caso de tener un id menor a la cabeza osea una nueva cabeza 
                NodoListaDobleCircular aux = new NodoListaDobleCircular(o);// setiamos una varible aux com oel nodo a ingresarr (p)
                aux.setSiguiente(cabeza);// a ser menor setiamos que el siguente del mismo es la cabeza
                cabeza.setAnterior(aux);// setiamos que el anterior de la cabeza es es el nuevo aux
                cabeza = aux;// setiamos el nuevo aux como la nueva cabeza
                cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                cantidadNodos++;

            } else {
                if (o.getId() > ultimo.getDato().getId()) {// ingresar algo despues del ultimo ( un nuevo ultimo) 
                    NodoListaDobleCircular aux = new NodoListaDobleCircular(o);// setiamos una varible aux com oel nodo a ingresarr (p)
                    aux.setAnterior(ultimo);// setiamos que el anterior del aux es el ultimo 
                    ultimo.setSiguiente(aux);//refrescamos el sigiuente del ultimo como el nuevo aux 
                    ultimo = aux;// refrescamos el ultimo como el aux que creamos
                    ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                    cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                    cantidadNodos++;

                } else {
                    if (ultimo.getDato().getId() > o.getId() && ultimo.getAnterior().getDato().getId() < o.getId()) {  // en caso de insertar antes del ultimo 
                        NodoListaDobleCircular aux = new NodoListaDobleCircular(o);// setiamos una varible aux com oel nodo a ingresarr (p)
                        ultimo.getAnterior().setSiguiente(aux);// setiamos la refenrencia del anterior al ultimo 
                        aux.setAnterior(ultimo.getAnterior().getAnterior());//setia,os el anterior del nodo nuevo 
                        ultimo.setAnterior(aux);// refrescamos el anterior del ultimo 
                        aux.setSiguiente(ultimo);// setiamos el siguenre del del aux osea el ultimo por la funcion  
                        cantidadNodos++;
                    } else {
                        NodoListaDobleCircular aux = cabeza;
                        NodoListaDobleCircular auxU = ultimo;
                        while (aux.getDato().getId() <= o.getId() && o.getId() <= auxU.getDato().getId()) { // la condicino de parada es cuando el id sea mayor a el aux y si el id es menor al auxU 
                            aux = aux.getSiguiente();// avanzamos al siguiente aux
                            auxU = auxU.getAnterior();//resoresedmos de auxU
                        }

                        aux = aux.getAnterior();// ponemos esto porque para que se ejecute el if necesitamos el el aux sea menor al id de p
                        if (aux.getDato().getId() < o.getId() && aux.getSiguiente().getDato().getId() > o.getId()) {// verificamos si debemos incertarlo de la cabeza hacia adelante 
                            aux = aux.getSiguiente();// setimos el aux al sigueinte del mismmo 
                            NodoListaDobleCircular temp = new NodoListaDobleCircular();//creamos un nuevo nodo el cual insertaremos 
                            temp.setAnterior(aux.getAnterior()); // seriamos el anterior del nuevo nodo al anteriror del aux
                            temp.setSiguiente(aux);// seriamos el siguente del nodo el auz 
                            aux.setAnterior(temp);// setiamos el nuevo atras del aux el nodo que creamos 
                            temp.getAnterior().setSiguiente(temp);// setiamos que el temp fuese el siguente del anterior 
                            cantidadNodos++;
                        } else if (auxU.getDato().getId() < o.getId() && auxU.getSiguiente().getDato().getId() > o.getId()) { // este funca
                            NodoListaDobleCircular temp = new NodoListaDobleCircular(o); // se crea el nuevo nodo 
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

    public void extraerPromerdio() {
        int promedio = 0;
        int principio = cabeza.getDato().getId();
        int aux = 0;
        if (cabeza != null) {
            aux = cabeza.getDato().getId();
            promedio = promedio + aux;
            cabeza = cabeza.getSiguiente();
            do {
                cabeza = cabeza.getSiguiente();
                aux = cabeza.getDato().getId();
                promedio = promedio + aux;
            } while (aux != principio);
            System.out.println(promedio / cantidadNodos);

        }

    }

    public void eliminar(Objeto p) {
        NodoListaDobleCircular aux = cabeza;
        NodoListaDobleCircular auxU = ultimo;

        // Caso donde el nodo a eliminar es la cabeza
        if (p.getId() == aux.getDato().getId()) {
            cabeza = cabeza.getSiguiente();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            cantidadNodos--;
            return;
        }

        // Caso donde el nodo a eliminar es el último
        if (p.getId() == auxU.getDato().getId()) {
            ultimo = ultimo.getAnterior();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
            cantidadNodos--;
            return;
        }

        // Caso donde el nodo a eliminar está en medio de la lista
        aux = cabeza.getSiguiente(); // Empezamos desde el segundo nodo
        while (aux != cabeza) { // Continuamos hasta que volvamos a la cabeza
            if (aux.getDato().getId() == p.getId()) {
                aux.getAnterior().setSiguiente(aux.getSiguiente());
                aux.getSiguiente().setAnterior(aux.getAnterior());
                cantidadNodos--;
                return;
            }
            aux = aux.getSiguiente();

        }
    }

    public void impirmirUltimo() { // metodo para que imprima el ultimo 
        NodoListaDobleCircular aux = ultimo;
        System.out.println(aux);
    }

    public void impirmirCabeza() { // metodo para que imprima el ultimo 
        NodoListaDobleCircular aux = cabeza;
        System.out.println(aux);
    }

    public void ImprimirSiguienteDelUltimo() { // metodo para que imprima el ultimo 
        NodoListaDobleCircular aux = ultimo;
        aux = aux.getSiguiente();
        System.out.println(aux);
    }

    public Objeto extrae(int id) {
        Objeto o = null; // creamos la variable p vacia para retornarla si se enceuntra el id deseado 
        if (cabeza != null) { // se continua si cabeza tiene algun dato 
            if (cabeza.getDato().getId() == id) { // si el datro es cabeza se continua con lo siguiente 
                o = cabeza.getDato();
                cabeza = cabeza.getSiguiente();// setiamos el sigueinte de cabeza la nueva cabeza 
                cabeza.setAnterior(ultimo);// refrescamos la referencia del ultimo 
                ultimo.setSiguiente(cabeza);//refrescamos la referrencia del primero 
                System.out.println("Esto es lo que se extrae" + o); // se meustra lo que se extrae 

            } else if (ultimo.getDato().getId() == id) {// vemos si el que extraemos es el ultimo 
                ultimo = ultimo.getAnterior();
                ultimo.setSiguiente(cabeza);
                cabeza.setAnterior(ultimo);
                System.out.println("Esto es lo que se extrae" + o); // se meustra lo que se extrae 

            } else {
                NodoListaDobleCircular aux = cabeza; // creamos un puntero llamado aux que empieza en cabeza 
                while (aux.getSiguiente() != cabeza && cabeza.getDato().getId() == id)// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
                {
                    aux = aux.getSiguiente();
                    if (aux.getSiguiente() != cabeza && cabeza.getDato().getId() == id) {// se detiene cuando el siguiete sea cabeza es decir cuando de una vuelta y se encuentre el id deseado 
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

    public boolean existe(int id) {
        boolean respuesta = false;// se setea la variable respuesta a falsa por defecto 

        NodoListaDobleCircular auxiliar = cabeza;// se define al auxiliar como la cabeza para iniciar 

        while (auxiliar != null) {// se verifica que no este vacia la fila
            if (auxiliar.getDato().getId() == id) {// se compara el dato de la posicion de aux con el id ingresado
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
        String respuesta = "Lista doble circular: \n";

        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;

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
    }

}
