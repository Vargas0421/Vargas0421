/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package edg10.zoologico;
import java.time.LocalDate;


/**
 *
 * @author Edificio C
 */
public class Tortuga {
    String tipo;
    String nombre;
    LocalDate fechaIngreso; 

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = LocalDate.now();
    }

    public Tortuga(String nombre) {
        this.tipo = nombre;
        
    }
    public Tortuga(){
    
    }
   
    
    public void presentarTortuga()
    {
        System.out.println("Hola, soy una tortuga de tipo: " + tipo + " y me llamo: " + nombre + " Ingrese el" + fechaIngreso);
    }

    
    
}
