package pcaso2;

public class Pelicula {

    private int id;
    private String titulo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pelicula(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Pelicula() {
    }

    @Override
    public String toString() {
        return "Pelicula: " + "#"+  id +" "+ titulo ;
    }

}
