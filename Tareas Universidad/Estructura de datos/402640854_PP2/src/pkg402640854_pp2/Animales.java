package pkg402640854_pp2;
public class Animales { // Se crea la calse Animales y se anaden los atributos requetidos 

    private String tipo;
    private String fechaNaciemiento;
    private String nombre;
// se crean setter u gutter y su constructor 
    public Animales(String tipo, String fechaNaciemiento, String nombre) {
        this.tipo = tipo;
        this.fechaNaciemiento = fechaNaciemiento;
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaNaciemiento() {
        return fechaNaciemiento;
    }

    public void setFechaNaciemiento(String fechaNaciemiento) {
        this.fechaNaciemiento = fechaNaciemiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    

}
