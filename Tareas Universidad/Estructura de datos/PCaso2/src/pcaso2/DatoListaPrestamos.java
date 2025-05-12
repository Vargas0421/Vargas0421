package pcaso2;

import java.time.LocalDate;

public class DatoListaPrestamos {

    private Pelicula pelicula;
    private LocalDate fecha;
    private int cedula;
    private String nombre;

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DatoListaPrestamos(Pelicula pelicula, LocalDate fecha, int cedula, String nombre) {
        this.pelicula = pelicula;
        this.fecha = fecha;
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public DatoListaPrestamos() {
    }

    @Override
    public String toString() {
        return "DatoListaPrestamos{" + "pelicula=" + pelicula + ", fecha=" + fecha + ", cedula=" + cedula + ", nombre=" + nombre + '}';
    }

}
