package pkg402640854_pp2;

public class Ejecucion_2 {

    public void Ejecucion2() {
        ColaPersonas cola = new ColaPersonas();
// se crean las 10 personas
        cola.encolarPersona(new Personas("Juan", "Perez", "01/01/1990", 1234567890, Personas.Genero.M));
        cola.encolarPersona(new Personas("Mario", "Vargas", "03/04/1990", 479373903, Personas.Genero.M));
        cola.encolarPersona(new Personas("Juan", "Perez", "01/01/1890", 393839383, Personas.Genero.M));
        cola.encolarPersona(new Personas("Maria", "Perez", "01/01/1990", 38893003, Personas.Genero.F));
        cola.encolarPersona(new Personas("Valeria", "Hernandez", "31/4/1990", 44343534, Personas.Genero.F));
        cola.encolarPersona(new Personas("Carlos", "Valdez", "09/23/2004", 745679, Personas.Genero.M));
        cola.encolarPersona(new Personas("Ella", "Vargas", "03/04/1990", 479373903, Personas.Genero.F));
        cola.encolarPersona(new Personas("Marco", "Perez", "01/01/1890", 393839383, Personas.Genero.M));
        cola.encolarPersona(new Personas("Vale", "Perez", "01/01/1990", 38893003, Personas.Genero.F));
        cola.encolarPersona(new Personas("Camila", "Hernandez", "31/4/1990", 44343534, Personas.Genero.F));

        //Se realiza el conteo de las personas por el metodo "Conteo por sexo"
        int hombres = cola.conteoPorSexo(Personas.Genero.M);
        int mujeres = cola.conteoPorSexo(Personas.Genero.F);
        
        System.out.println("Contenido de la cola:");
        for (Personas persona : cola.colaPersonas) {
            System.out.println("Nombre: " + persona.getNombre() +
                               ", Apellido: " + persona.getApellido() +
                               ", Fecha de Nacimiento: " + persona.getFechaNacimiento() +
                               ", Cedula: " + persona.getCedula() +
                               ", Genero: " + persona.getGenero());
        }

        System.out.println("Hombres: " + hombres);
        System.out.println("Mujeres: " + mujeres);
    }
}
