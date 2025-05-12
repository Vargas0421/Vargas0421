package pkg402640854_pp2;

import java.util.LinkedList;
import java.util.Queue;

public class ColaPersonas {

    public Queue<Personas> colaPersonas;

    public ColaPersonas() {
        colaPersonas = new LinkedList<>();
    }

    // Método para encolar una persona
    public void encolarPersona(Personas persona) {
        colaPersonas.offer(persona);
    }

    // Este metodo se crea para recorrer la Cola de manera recursiva
    public int conteoPorSexo(Personas.Genero sexo) {
        return conteoPorSexoRecursivo(colaPersonas, sexo);
    }

    private int conteoPorSexoRecursivo(Queue<Personas> cola, Personas.Genero sexo) {
//Si la cosa esta vacia no se devulve nada
        if (cola.isEmpty()) {
            return 0;
        } else {
            //Se optiene la primera persona de la cola
            Personas persona = cola.poll();
            //Se verifica si el sexo de la persona coincide con el que se busca por eso hay uno para M y otro para F
            int conteo = (persona.getGenero() == sexo) ? 1 : 0; // se hace medante una expresion condicional
            int restoConteo = conteoPorSexoRecursivo(cola, sexo); // se crea esta variable para almacenar el retorno de Conteo
//se devulve la persona a al cola por medio del .offer
            cola.offer(persona);
            // se retorna el conteo total
            return conteo + restoConteo;
        }
    }
}
