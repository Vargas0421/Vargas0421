/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edg10.zoologico;

/**
 *
 * @author deiv
 */

//Programación Orientada a Objetos
public class Tigre {
    //Atributos de la clase
    //Atributos = son parametros, variables y datos en general, que permiten describir individualmente a la clase
    String tipoTigre; //Podemos tener varios tipos de tigre: bengala, asiático, siberiano, cualquier otro.
    String nombreTigre; // El nombre del tigre
    
    //Constructor de la clase
    //Definir la clase como un objeto en el momento que la utilice
    public Tigre(String nombreTigre) {
        this.nombreTigre = nombreTigre;
    }
    
    //Getters y Setter y otros métodos adicionales

    public String getNombreTigre() {
        return nombreTigre;
    }

    public String getTipoTigre() {
        return tipoTigre;
    }

    public void setTipoTigre(String tipoTigre) {
        this.tipoTigre = tipoTigre;
    }
    
    public void presentarTigre()
    {
        System.out.println("Hola, soy un tigre de tipo: " + tipoTigre + " y me llamo: " + nombreTigre);
    }
    
    public boolean tigreDormido(int indicadorDormido)
    {
        if(indicadorDormido == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void tigreComiendo(int indicadorComiendo)
    {
        if(indicadorComiendo == 0)
        {
            System.out.println("El tigre está comiendo");
        }
        else
        {
            System.out.println("El tigre NO está comiendo");
        }
    }

}
