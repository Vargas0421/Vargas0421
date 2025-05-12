package pkg402640854_pp2;
import javax.swing.JOptionPane;
public class Ejecucion {

    public void ejecucion(){
        
        PilaAnimales pila = new PilaAnimales();

        // Agregar algunos animales a la pila
        pila.agregarAnimal(new Animales("Tortuga", "31/01/2010", "Byron"));
        pila.agregarAnimal(new Animales("Perro", "05/03/2015", "El"));
        pila.agregarAnimal(new Animales("Delfin", "03/03/2022", "Rofolfo"));
        pila.agregarAnimal(new Animales("Perro", "27/07/2014", "Pedro"));
        pila.agregarAnimal(new Animales("Tortuga", "02/02/2041", "Carmen"));
        pila.agregarAnimal(new Animales("Tortuga", "01/01/2050", "Marcelo"));
        pila.agregarAnimal(new Animales("Perro", "05/05/2015", "Perron"));
        pila.agregarAnimal(new Animales("Delfin", "03/03/2052", "Casimiro"));
        pila.agregarAnimal(new Animales("Perro", "17/07/2014", "Juan"));
        pila.agregarAnimal(new Animales("Tortuga", "22/02/2011", "Nashe"));

        // Se eilimnan el tipo de animal elegid por el usuario 
        pila.EliminarTipo(JOptionPane.showInputDialog("Cual seria el nombre del animal a eliminar: Perro, Delfin o Tortuga"));

        while (!pila.pilaAnimales.isEmpty()) {
            Animales animal = pila.pilaAnimales.pop();
            System.out.println("Tipo: " + animal.getTipo() + ", Fecha de Nacimiento: " + animal.getFechaNaciemiento()+ ", Nombre: " + animal.getNombre());
        }
    }
}
