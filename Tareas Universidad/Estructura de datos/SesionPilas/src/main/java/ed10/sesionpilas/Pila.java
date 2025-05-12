/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed10.sesionpilas;
import javax.swing.JOptionPane;

/**
 *
 * @author deivert.guiltrichs
 */
public class Pila {
    private Nodo cima;

    public Nodo getCima() {
        return cima;
    }

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
    
    public void apilar()
    {
        Dato d = new Dato();
        
        d.setNombre(JOptionPane.showInputDialog(null, "Digite el nombre:"));
        
        Nodo nuevo = new Nodo();
        
        nuevo.setElemento(d);
        
        if(esVacia())
        {
            cima = nuevo;
        }
        else
        {
            nuevo.setSiguiente(cima);
            cima = nuevo;
        }
    }
    
    public void desapilar()
    {
        if(!esVacia())
        {
            cima = cima.getSiguiente();
            JOptionPane.showMessageDialog(null, "El elemento fue extraído");
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No se pueden extraer elementos de una pila vacía");
        }
    }
    
    public String imprimirPila()
    {
        String respuesta = "";
        if(!esVacia())
        {
            //Imprimimos los nodos
            Nodo temp = cima; // Maria
            while(temp != null) // La segunda vez que entra, va a a encontrar a Javier
                                //La tercera vez, temp = null, y se va a salir de while
            {
                respuesta += temp.getElemento().getNombre() + "\n"; // Aquí vamos a imprimir María la primera
                                                        // Aquí vamos a imprimir a Javier la segunda
                temp = temp.getSiguiente(); // Cuando nos movemos a este, vamos a ir a Javier
                                            //Cuando nos movemos a este, ya no encontramos nada
            }
        }
        else
        {
            respuesta = "La pila está vacía";
        }
        
        return respuesta;
    }
    
    
}
