/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edg10.zoologico;

/**
 *
 * @author deiv
 */
public class Oso {
    //Atributos
    String nombreOso;
    String colorOso; //Pardo, Polar, Panda.....
    boolean hibernando;
    
    //Constructor
    public Oso(String nombreOso) {
        this.nombreOso = nombreOso;
    }
    
    //Métodos

    public String getNombreOso() {
        return nombreOso;
    }

    public String getColorOso() {
        return colorOso;
    }

    public void setColorOso(String colorOso) {
        this.colorOso = colorOso;
    }

    public boolean isHibernando() {
        return hibernando;
    }

    public void setHibernando(boolean hibernando) {
        this.hibernando = hibernando;
    }
    
    public void indicadorDeHibernacion()
    {
        if(hibernando) // if(hibernando == true)
        {
            System.out.println("Sssssh... el oso está hibernando, no lo moleste");
        }
        else
        {
            System.out.println("El oso está despierto, cuidado!");
        }
    }
    
    public void presentarOso()
    {
        System.out.println("Hola soy un oso: " + colorOso + " y me llamo: " + nombreOso);
    }
}
