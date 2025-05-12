/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eeddg10.listasdoblescirculares;

/**
 *
 * @author deivert.guiltrichs
 */
public class ListaDoble {

    private Nodo cabeza;
    private Nodo ultimo;

    public void insertaMejorado(Persona p) {
        //Paso 1, de la presentación
        if (cabeza == null) { // en dado caso que la  este vacia 
            cabeza = new Nodo(p); // seriamos el nodo como la cabeza
            ultimo = cabeza; // setiamos la misma cabeza como la cola
            cabeza.setAnterior(ultimo); //setiamos que el anterior de la cabeza es el ultimo osea la misma cabeza
            cabeza.setSiguiente(ultimo);// setiamos que el siguente de la cabeza es el ultimo que es la cabeza
            ultimo.setSiguiente(cabeza);// setiamos que el siguente del ultimo es cabeza 
            ultimo.setAnterior(cabeza);// setiamos que el anterior del ultimo es la cabeza 
        } else {
            if (cabeza.getDato().getId() > p.getId()) {// en caso de tener un id menor a la cabeza osea una nueva cabeza 
                Nodo aux = new Nodo(p);// setiamos una varible aux com oel nodo a ingresarr (p)
                aux.setSiguiente(cabeza);// a ser menor setiamos que el siguente del mismo es la cabeza
                cabeza.setAnterior(aux);// setiamos que el anterior de la cabeza es es el nuevo aux
                cabeza = aux;// setiamos el nuevo aux como la nueva cabeza
                cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
            } else {
                if (p.getId() > ultimo.getDato().getId()) {// ingresar algo despues del ultimo ( un nuevo ultimo) 
                    Nodo aux = new Nodo(p);// setiamos una varible aux com oel nodo a ingresarr (p)
                    aux.setAnterior(ultimo);// setiamos que el anterior del aux es el ultimo 
                    ultimo.setSiguiente(aux);//refrescamos el sigiuente del ultimo como el nuevo aux 
                    ultimo = aux;// refrescamos el ultimo como el aux que creamos
                    ultimo.setSiguiente(cabeza);// refrescamos el siguente del ultimo 
                    cabeza.setAnterior(ultimo);// refrescamos el anterior de la caeza 
                } else {
                    Nodo aux = cabeza;
                    Nodo auxU = ultimo;
                    while (aux.getDato().getId() <= p.getId() && p.getId() <= auxU.getDato().getId()) { // la condicino de parada es cuando el id sea mayor a el aux y si el id es menor al auxU 
                        aux = aux.getSiguiente();// avanzamos al siguiente aux
                        auxU = auxU.getAnterior();//resoresedmos de auxU
                    }

                    aux = aux.getAnterior();// ponemos esto porque para que se ejecute el if necesitamos el el aux sea menor al id de p
                    if (aux.getDato().getId() < p.getId() && aux.getSiguiente().getDato().getId() > p.getId()) {// verificamos si debemos incertarlo de la cabeza hacia adelante 
                        aux = aux.getSiguiente();// setimos el aux al sigueinte del mismmo 
                        Nodo temp = new Nodo(p);//creamos un nuevo nodo el cual insertaremos 
                        temp.setAnterior(aux.getAnterior()); // seriamos el anterior del nuevo nodo al anteriror del aux
                        temp.setSiguiente(aux);// seriamos el siguente del nodo el auz 
                        aux.setAnterior(temp);// setiamos el nuevo atras del aux el nodo que creamos 
                        temp.getAnterior().setSiguiente(temp);// setiamos que el temp fuese el siguente del anterior 
                    } else if (auxU.getDato().getId() < p.getId() && auxU.getSiguiente().getDato().getId() > p.getId()) { // este funca
                        Nodo temp = new Nodo(p); // se crea el nuevo nodo 
                        temp.setAnterior(auxU); // setiamos que el anterior del nuevo nodo es la posicion en la que paramos
                        temp.setSiguiente(auxU.getSiguiente()); // el sigueinte de ese noso es el siguinete de la posicion en la que paramos
                        auxU.setSiguiente(temp);// setiamos que el sigueinte la posicion en la que paramos es el nuevo nodo 
                        aux.getSiguiente().getSiguiente().setAnterior(temp);//setiamos el nuevo nodo como el anetior del antiguo siguente de la posicion en la que paramos

                    }
                }
            }
        }
    }

    public void inserta(Persona p) {
        //Paso 1, de la presentación
        if (cabeza == null) {
            cabeza = new Nodo(p);
            ultimo = cabeza;
            cabeza.setAnterior(ultimo);
            cabeza.setSiguiente(ultimo);
            ultimo.setSiguiente(cabeza);
            ultimo.setAnterior(cabeza);
        } else {
            //Paso 2, de la presentación
            if (cabeza.getDato().getId() > p.getId()) {
                Nodo aux = new Nodo(p);
                aux.setSiguiente(cabeza);
                cabeza.setAnterior(aux);
                cabeza = aux;
                cabeza.setAnterior(ultimo);
                ultimo.setSiguiente(cabeza);
            } else {
                //Paso 3, de la presentación
                if (p.getId() > ultimo.getDato().getId()) {
                    Nodo aux = new Nodo(p);
                    aux.setAnterior(ultimo);
                    ultimo.setSiguiente(aux);
                    ultimo = aux;
                    ultimo.setSiguiente(cabeza);
                    cabeza.setAnterior(ultimo);
                } else {
                    //Paso 4, de la presentación
                    Nodo aux = cabeza;
                    while (aux.getDato().getId() < p.getId()) {
                        aux = aux.getSiguiente();
                    }

                    Nodo temp = new Nodo(p);
                    temp.setAnterior(aux.getAnterior());
                    temp.setSiguiente(aux); //Acá aux.getAnterior está apuntando (en su siguiente) a aux
                    aux.setAnterior(temp);
                    temp.getAnterior().setSiguiente(temp);
                }
            }
        }
    }

    @Override
    public String toString() {
        String respuesta = "Lista doble circular: \n";

        if (cabeza != null) {
            Nodo aux = cabeza;

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
