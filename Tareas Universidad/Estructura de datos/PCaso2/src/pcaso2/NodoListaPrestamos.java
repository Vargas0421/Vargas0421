/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pcaso2;

/**
 *
 * @author Brandon VM
 */
public class NodoListaPrestamos {

    private DatoListaPrestamos dato;
    private NodoListaPrestamos anterior;
    private NodoListaPrestamos siguiente;

    public NodoListaPrestamos(DatoListaPrestamos dato) {
        this.dato = dato;
    }

    public NodoListaPrestamos() {
    }

    public DatoListaPrestamos getDato() {
        return dato;
    }

    public void setDato(DatoListaPrestamos dato) {
        this.dato = dato;
    }

    public NodoListaPrestamos getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoListaPrestamos anterior) {
        this.anterior = anterior;
    }

    public NodoListaPrestamos getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaPrestamos siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "NodoListaPrestamos{" + "dato=" + dato + ", anterior=" + anterior + ", siguiente=" + siguiente + '}';
    }

}
