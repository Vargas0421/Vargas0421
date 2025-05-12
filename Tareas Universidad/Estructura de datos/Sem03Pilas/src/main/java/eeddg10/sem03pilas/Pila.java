/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eeddg10.sem03pilas;

/**
 *
 * @author deiv
 */
public class Pila {
    private Nodo cima;

    public Pila() {
        this.cima = null;
    }
    
    public boolean esVacia()
    {
        if(cima == null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public void apilar(String nombre)
    {
        //Insertar elementos en la pila
        Nodo nuevo = new Nodo(nombre);
        if(esVacia())
        {
            cima = nuevo;
        }
        else
        {
            nuevo.setSiguiente(cima); //Cada elemento nuevo, va a tener como siguiente a la cima actual
                                              // PERRO -> GATO
            cima = nuevo;
        }
    }
    
    public void desapilar()
    {
        if(esVacia())
        {
            System.out.println("No se puede extraer ningún elemento porque la pila está vacía");
        }
        else
        {
            //cima = gato
            cima = cima.getSiguiente(); // cima = al siguiente del gato
            //cima = null
        }
    }
    
    public String imprimirPila()
    {
        String respuesta = ""; // En este string vamos a ir almacenando cada uno de los nodos de la pila
        
        if(!esVacia()) //Si la lista no es vacía la imprimimos
        {
            Nodo auxiliar = cima; //Por cada nodo en el que me mueva, voy a guardar la referencia
            while(auxiliar != null) //Siempre y cuando el auxiliar no sea nulo
            {
                //Voy a seguir recorriendo la pila
                respuesta += auxiliar.getElemento().getNombre() + "\n";
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return respuesta;
    }
}
