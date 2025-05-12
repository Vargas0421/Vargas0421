/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package eeddg10.sem03pilas;

/**
 *
 * @author deiv
 */
public class Sem03Pilas {

    public static void main(String[] args) {
        Pila nuevaPila = new Pila();
        
        System.out.println("***PILA ORIGINAL***");
        
        nuevaPila.apilar("Gato");
        nuevaPila.apilar("Perro");
        nuevaPila.apilar("Mapache");
        
        System.out.println(nuevaPila.imprimirPila());
        
        System.out.println("***PILA MODIFICADA***");
        
        nuevaPila.desapilar();
        
        System.out.println(nuevaPila.imprimirPila());
    }
}
