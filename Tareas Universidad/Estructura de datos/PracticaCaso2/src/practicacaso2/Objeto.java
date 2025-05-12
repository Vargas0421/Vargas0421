package practicacaso2;
public class Objeto {
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Objeto() {
    }

    public Objeto(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Objeto{" + "id=" + id + '}';
    }
    
}
