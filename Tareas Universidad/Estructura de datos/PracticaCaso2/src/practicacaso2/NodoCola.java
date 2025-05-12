package practicacaso2;


public class NodoCola {

    private Objeto dato;
    private NodoCola atras;

    public Objeto getDato() {
        return dato;
    }

    public NodoCola(Objeto dato) {
        this.dato = dato;
    }

    public void setDato(Objeto dato) {
        this.dato = dato;
    }

    public NodoCola getAtras() {
        return atras;
    }

    public void setAtras(NodoCola atras) {
        this.atras = atras;
    }

    public NodoCola(Objeto dato, NodoCola atras) {
        this.dato = dato;
        this.atras = atras;
    }

    public NodoCola() {
    }

    @Override
    public String toString() {
        return "NodoCola{" + "dato=" + dato + ", atras=" + atras + '}';
    }
    
    

}
