package pcaso2;

public class DatoListaCategorias {// esto es una lista simple 

    private int id;
    private String nombreCategoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public DatoListaCategorias(int id, String nombreCategoria) {
        this.id = id;
        this.nombreCategoria = nombreCategoria;
    }

    public DatoListaCategorias() {
    }

    /*@Override
    public String toString() {
        return "DatoListaCategorias{" + "id=" + id + ", nombreCategoria=" + nombreCategoria + '}';
    }*/

    @Override
    public String toString() {
        return this.id + " - " + this.nombreCategoria;
    }
    

}
