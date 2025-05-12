package pcaso2;

public class PCaso2 {

    public static void main(String[] args) {

        // Crear la lista de categorías
        ListaCategorias listaCategorias = new ListaCategorias();

        // Crear nuevas categorías y añadirlas a la lista
        DatoListaCategorias categoriaDrama = new DatoListaCategorias(1, "Drama");
        DatoListaCategorias categoriaComedia = new DatoListaCategorias(2, "Comedia");
        DatoListaCategorias categoriaAccion = new DatoListaCategorias(3, "Accion");
        DatoListaCategorias categoriaTerror = new DatoListaCategorias(4, "Terror");
        DatoListaCategorias categoriaMisterio = new DatoListaCategorias(5, "Misterio");

        listaCategorias.insertar(categoriaDrama);
        listaCategorias.insertar(categoriaComedia);
        listaCategorias.insertar(categoriaAccion);
        listaCategorias.insertar(categoriaTerror);
        listaCategorias.insertar(categoriaMisterio);

        // Buscar el nodo de la categoría "Drama"
        NodoListaCategorias nodoDrama = listaCategorias.buscarCategoria(1);
        NodoListaCategorias nodoComedia = listaCategorias.buscarCategoria(2);
        NodoListaCategorias nodoAccion = listaCategorias.buscarCategoria(3);
        NodoListaCategorias nodoTerror = listaCategorias.buscarCategoria(4);
        NodoListaCategorias nodoMisterio = listaCategorias.buscarCategoria(5);

        // Insertar películas en la lista de películas del nodo de la categoría "Drama"
        nodoDrama.getPeliculas().insertar(new Pelicula(1, "Orgullo y Prejuicio"));
        nodoDrama.getPeliculas().insertar(new Pelicula(2, "El Padrino"));
        nodoDrama.getPeliculas().insertar(new Pelicula(3, "Cadena Perpetua"));
        //insertar pelis en la lista de comediA
        nodoComedia.getPeliculas().insertar(new Pelicula(1, "Que paso ayer?"));
        nodoComedia.getPeliculas().insertar(new Pelicula(2, "Las locuras de Dick y Jane "));
        nodoComedia.getPeliculas().insertar(new Pelicula(3, "Todopoderoso"));
        nodoComedia.getPeliculas().insertar(new Pelicula(4, "Son como ninos "));

        System.out.println(listaCategorias);
    }
}
