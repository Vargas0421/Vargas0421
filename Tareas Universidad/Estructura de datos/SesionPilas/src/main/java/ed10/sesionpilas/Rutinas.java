/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed10.sesionpilas;

/**
 *
 * @author deivert.guiltrichs
 */
public class Rutinas {
    public Pila p1 = new Pila();
    
    public boolean encuentra(int x)
    {
        boolean respuesta = false;
        
        if(!p1.esVacia())
        {
            //Recorremos la pila
            
            Nodo aux = p1.getCima();
            
            //Una vez que tenemos la cima, comenzamos a recorrer la pila, para buscar el elemento
            
            while(aux != null) // mientras tanto, no se cumpla una condición, entonces vamos a aplicar un proceso
            {
                if(x == Integer.parseInt(aux.getElemento().getNombre()))
                {
                    respuesta = true;
                    break;
                }
                else
                {
                    aux = aux.getSiguiente(); //Si aux es nuestro pivote, tenemos que sobreescribir esta variable
                                              // cada vez que nos movemos.
                }
            }
        }
        else
        {
            respuesta = false; // si la pila está vacía, se sale, porque no vamos a encontrar el valor
        }
        
        return respuesta;
    }
}
