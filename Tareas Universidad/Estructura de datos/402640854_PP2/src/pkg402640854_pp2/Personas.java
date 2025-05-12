package pkg402640854_pp2;

public class Personas {

    // se crean los atributos y el de Genero se hace con Enum para que solo pueda ser M o F
    public enum Genero {
        M, // Para género masculino
        F  // Para género femenino
    }
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private int cedula;
    private Genero genero;

    public Personas(String nombre, String apellido, String fechaNacimiento, int cedula, Genero genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.cedula = cedula;
        this.genero = genero;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }


}
