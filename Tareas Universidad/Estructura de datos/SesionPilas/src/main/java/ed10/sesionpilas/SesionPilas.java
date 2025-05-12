/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ed10.sesionpilas;

/**
 *
 * @author deivert.guiltrichs
 */
public class SesionPilas {

    public static void main(String[] args) {
       Pila nuevaPila = new Pila();
       
       //System.out.println(nuevaPila.imprimirPila());
       
       nuevaPila.apilar();
       nuevaPila.apilar();
       nuevaPila.apilar();
       nuevaPila.apilar();
       nuevaPila.apilar();
       
       System.out.println(nuevaPila.imprimirPila());
       
       /*nuevaPila.desapilar();
       
       System.out.println(nuevaPila.imprimirPila());*/
       
       Rutinas r = new Rutinas();
       
       r.p1 = nuevaPila;
       
       if(r.encuentra(7))
       {
           System.out.println("Encontrado");
       }
       else
       {
           System.out.println("No Encontrado");
       }
    }
}
