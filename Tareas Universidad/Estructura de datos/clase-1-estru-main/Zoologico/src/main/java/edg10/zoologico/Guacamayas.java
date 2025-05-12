
package edg10.zoologico;

import java.time.LocalDate;
public class Guacamayas {
    
    private String tienePareja;
    private String nombre;
    private LocalDate fechaIngreso; 
    private String color; 

    public String getTienePareja() {
        return tienePareja;
    }

    public void setTienePareja(String tienePareja) {
        this.tienePareja = tienePareja;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
 
    
    }
        

