/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eeddg10.sem03pilas;

/**
 *
 * @author deiv
 */
public class Nodo {
    private Dato elemento;
    private Nodo siguiente;

    public Nodo(String nombre) {
        this.elemento = new Dato(nombre);
        this.siguiente = null;
    }
    
    public Dato getElemento() {
        return elemento;
    }

    public void setElemento(Dato elemento) {
        this.elemento = elemento;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    
    
}
