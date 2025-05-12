/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package eeddg10.listasdoblescirculares;

/**
 *
 * @author deivert.guiltrichs
 */
public class ListasDoblesCirculares {

    public static void main(String[] args) {
        ListaDoble l = new ListaDoble();

        l.inserta(new Persona("Manuel", 3));
        l.inserta(new Persona("Manuel", 2));
        l.inserta(new Persona("Manuel", 1));
        l.inserta(new Persona("Sofia", 5));
        l.inserta(new Persona("Alberto", 9));
        l.inserta(new Persona("Maria", 11));
        l.inserta(new Persona("Jorge", 7));
        l.inserta(new Persona("carmen", 12));
        l.inserta(new Persona("enrrique", 15));
        l.inserta(new Persona("marta", 13));
        System.out.println(l.toString());
        System.out.println("Se anade un espacio con el insertar especial");
        l.insertaMejorado(new Persona("Carlos", 4));
        System.out.println(l.toString());

    }
}
