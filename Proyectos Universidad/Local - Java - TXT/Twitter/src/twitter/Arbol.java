package twitter;

import javax.swing.JOptionPane;

public class Arbol {

    //atributos de la clase
    private NodoArbol root; //crea el nodo root de arbol

    public Arbol() {
        this.root = null;//inicializa el nodo creado como nulo
    }

    //get y set
    public NodoArbol getRoot() {
        return root;
    }

    public void setRoot(NodoArbol root) {
        this.root = root;
    }//final get y set

    public boolean esVacio() {
        return root == null;//metodo para saber si el arbol está vacío
    }//Final metodo esVacio

    public void agregar(Post mensaje) {
        //seguir comentando esta shit zzz
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para el nuevo mensaje (Post)
        if (esVacio()) {//si el arbol esta vacio
            root = nuevo; // se crea el Mensaje principal
        }//final if 
    }//final metodo agregar

    public void responder(Usuario user, Usuario post, NodoArbol root, ListaDobleCircular usuarios) {
        Usuario usuario = usuarios.buscarUsuarioPorCorreo(root.getMensaje().getUser());
        Post mensaje = Post.respuesta(user, usuario);
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para la respuesta (Post)
        nuevo.getMensaje().setMsj("      " + nuevo.getMensaje().getMsj());
        NodoArbol aux = root; //este esta conectado al mensaje principal
        NodoArbol padre; // otra variable de apoyo para la respuesta a la respuesta
        while (true) {
            padre = aux;  //igualamos ambas variables
            if (padre.getIzquierdo() == null) {
                aux = aux.getIzquierdo();
                if (aux == null) {
                    padre.setIzquierdo(nuevo);
                    return;
                }//Final if
            }//final if
            else {
                aux = aux.getDerecho();
                if (aux == null) {
                    padre.setDerecho(nuevo);
                    return;
                }//final if
            }//final else
        }//final while
    }//final else

    public void responder(Usuario user, Usuario post, NodoArbol root, String respuesta, String fecha, ListaDobleCircular usuarios) {
        Usuario usuario = usuarios.buscarUsuarioPorCorreo(root.getMensaje().getUser());
        Post mensaje = Post.respuesta(user, usuario, respuesta);
        mensaje.setFecha(fecha);
        NodoArbol nuevo = new NodoArbol(mensaje); //se establece una variable para la respuesta (Post)
        nuevo.getMensaje().setMsj(nuevo.getMensaje().getMsj());
        NodoArbol aux = root; //este esta conectado al mensaje principal
        NodoArbol padre; // otra variable de apoyo para la respuesta a la respuesta
        while (true) {
            padre = aux;  //igualamos ambas variables
            if (padre.getIzquierdo() == null) {
                aux = aux.getIzquierdo();
                if (aux == null) {
                    padre.setIzquierdo(nuevo);
                    return;
                }//Final if
            }//final if
            else {
                aux = aux.getDerecho();
                if (aux == null) {
                    padre.setDerecho(nuevo);
                    return;
                }//final if
            }//final else
        }//final while
    }//final else

    public void mostrar(Arbol arbol, ListaDobleCircular usuarios) {
        if (!esVacio()) { //si no es vacio
            String post = inOrdenR(root, usuarios);
            if (post != "") {
                int opcion = Integer.parseInt(JOptionPane.showInputDialog(post + "\n\n 1- Responder     2- Siguiente"));//mostrar el inOrder Recursivo
                switch (opcion) {
                    case 1:
                        if (root.getDerecho() != null && root.getIzquierdo() != null) {
                            JOptionPane.showMessageDialog(null, "Se ha alcanzado el numero de respuestas máximo");
                            mostrar(arbol, usuarios);
                        }//final if
                        else {
                            JOptionPane.showMessageDialog(null, "A continuacion, seleccione el usuario que esta respondiendo.");
                            Usuario usuarioa = Grafo.seleccionarUsuario(usuarios);// se busca en la lista doble mediante la funcion 
                            if (usuarioa != null) {// de haberlo
                                Usuario usuario = usuarios.buscarUsuarioPorCorreo(root.getMensaje().getUser());
                                arbol.responder(usuarioa, usuario, root, usuarios);
                                mostrar(arbol, usuarios);
                            }//final if
                            else {
                                JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario");
                            }//final else
                        }//final else
                        break;
                    case 2:
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Elija una opción valida.");
                }//final switch
            }//final if 
        }//final if
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
    }//final del metodo mostrar

    public Arbol eliminarPost(Arbol arbol, Usuario usuarioa, ListaDobleCircular usuarios) {
        Arbol post = null;
        if (!esVacio()) { //si no es vacio
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(inOrdenR(root, usuarios) + "\n\n 1- Eliminar Post     2- Siguiente"));//mostrar el inOrder Recursivo
            switch (opcion) {
                case 1:
                    if (usuarioa != null) {// de haberlo
                        return arbol;
                    }//final if 
                    else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado al usuario");
                    }//final else
                    break;
                case 2:
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Elija una opción valida.");
            }//final switch
        }//final if 
        else {
            JOptionPane.showMessageDialog(null, "El arbol está vacío");
        }//final else
        return post;
    }//final del metodo mostrar

    public String inOrdenR(NodoArbol root, ListaDobleCircular usuarios) {
        String resultado = "";
        if (root != null && comparar(root, usuarios)) {
            resultado += root.getMensaje();
            if (root.getIzquierdo() != null && comparar(root.getIzquierdo(), usuarios)) {
                resultado += "------";
            }//final if
            resultado += inOrdenR(root.getIzquierdo(), usuarios);
            if (root.getDerecho() != null && comparar(root.getDerecho(), usuarios)) {
                resultado += "------";
            }//Final if
            resultado += inOrdenR(root.getDerecho(), usuarios);
        }//final if
        return resultado;
    }//final del metodo inOrderR

    private boolean comparar(NodoArbol post, ListaDobleCircular usuarios) {
        Usuario usuario = usuarios.buscarUsuarioPorCorreo(post.getMensaje().getUser());
        boolean x = false;
        NodoListaDobleCircular cabeza = usuarios.getCabeza();
        if (cabeza != null) {
            NodoListaDobleCircular aux = cabeza;
            if (aux.getDato() == usuario) {
                x = true;
            }//final if
            aux = aux.getSiguiente();
            while (aux != cabeza) {
                if (aux.getDato() == usuario) {
                    x = true;
                }//final if
                aux = aux.getSiguiente();
            }//final while
        }//final if
        return x;
    }//final del metodo actualizarListaSeguidores

    @Override
    public String toString() {
        return root.getMensaje().getUser() + root;
    }

}//Final de la clase
