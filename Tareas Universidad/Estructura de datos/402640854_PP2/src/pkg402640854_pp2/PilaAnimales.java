package pkg402640854_pp2;
import java.util.Stack;  
public class PilaAnimales {
    
    Stack<Animales> pilaAnimales;
    
    public PilaAnimales() {  // se crea la pila para los animales
        pilaAnimales = new Stack<>();
    }
    
    public void agregarAnimal(Animales animal) { // se crea el metodo para agregar un animal
        pilaAnimales.push(animal);
        
    }
    
     public void EliminarTipo(String tipo) {
        Stack<Animales> tempStack = new Stack<>();// se crea una pila temporal para mover  a los animales cuando recorremos la pila original

        // se transfieren los animales que no coinciden con el tipo especificado a una pila temporal
        while (!pilaAnimales.isEmpty()) {  // el while se recorre siempre que la pila no este vacia 
            Animales animal = pilaAnimales.pop();
            if (!animal.getTipo().equals(tipo)) {
                tempStack.push(animal);
            }
        }

        // se transfieren los animales nuevamente a la pila original en el mismo orden
        while (!tempStack.isEmpty()) {
            pilaAnimales.push(tempStack.pop());
        }
    }
}
