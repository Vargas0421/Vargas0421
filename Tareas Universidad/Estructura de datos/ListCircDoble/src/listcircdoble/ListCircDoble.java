
package listcircdoble;
public class ListCircDoble {

    public static void main(String[] args) {
        Lista l = new Lista();
        l.insertaMejorado(new Persona("Manuel", 3));
        l.insertaMejorado(new Persona("Miguel", 2));
        l.insertaMejorado(new Persona("Charlie", 1));
        l.insertaMejorado(new Persona("Sofia", 5));
        l.insertaMejorado(new Persona("Alberto", 9));
        l.insertaMejorado(new Persona("Jorge", 7));
        l.insertaMejorado(new Persona("carmen", 12));
        l.insertaMejorado(new Persona("marta", 13));
        l.insertaMejorado(new Persona("a", 4));
        l.insertaMejorado(new Persona("v", 11));
        l.insertaMejorado(new Persona("victoria", 16));
        l.insertaMejorado(new Persona("Jazz", 14));
        l.insertaMejorado(new Persona("enrrique", 15));

        System.out.println(l.toString());
        l.ImprimirSiguienteDelUltimo();
        /*
        l.modifica(new Persona("Brandon", 3));
        System.out.println(l.toString());
        l.extrae(15);
        System.out.println(l.toString());
         */

        
    }
    
}
