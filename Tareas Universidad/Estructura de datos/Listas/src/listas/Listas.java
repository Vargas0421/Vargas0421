package listas;

public class Listas {

    public static void main(String[] args) {

        Lista lista = new Lista();
        lista.insertar(new Persona("Carmen", 1));
        lista.insertar(new Persona("Brandon", 2));
        lista.insertar(new Persona("Ronald", 3));
        lista.insertar(new Persona("Stephanie", 4));
        System.out.println(lista.toString());
    }

}
