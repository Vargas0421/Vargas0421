package practicacaso2;

public class PracticaCaso2 {

    public static void main(String[] args) {
        ////////////////////////////////////////////////////////////////////////
        ListaCircular listaCirc = new ListaCircular();// se crea una lista circlar  apartir de un for
        for (int i = 1; i < 31; i++) {// se hace un ciclo para recorrerlo 30 vece 
            listaCirc.inserta(new Objeto(i));// se crean las listas 
        }
        //System.out.println(listaCirc);
        ////////////////////////////////////////////////////////////////////////
        Cola cola = listaCirc.pasarACola(); // se crea una cola con un metodo de la clase lista 
        //System.out.println(cola.imprimirCola());// se imprime la cola 
        cola.imprimirRecursivo();
        ////////////////////////////////////////////////////////////////////////
        ListaDobleCircular l = cola.colaAListaDobCirc();// se llama la funcion sobr ela listadoblecirc 
        //System.out.println(l.toString());// se imprime la lista 
        ////////////////////////////////////////////////////////////////////////

        //l.extraerPromerdio();// se da el promedio de la listadoblecircualr
        ////////////////////////////////////////////////////////////////////////
        cola.valorPar(cola);
    }
}
