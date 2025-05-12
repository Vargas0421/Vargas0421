package pkg402640854_pp1;

import java.time.LocalDate;
import java.util.Random;

public class Persona {

    // Atributos de la clase Persona
    private String apellido;
    private String nombre;
    private LocalDate fechaDeNacimiento;
    private int[] notas = new int[10];

    // Constructor que inicializa la persona con valores proporcionados
    public Persona(String apellido, String nombre, LocalDate fechaDeNacimiento) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
        // Se generan notas aleatorias para la persona
        Random random = new Random();
        for (int x = 0; x < notas.length; x++) {
            notas[x] = random.nextInt(101); // Cambiado el rango de notas a [0, 100]
        }
    }

    // Constructor predeterminado que inicializa la persona con valores por defecto
    public Persona() {
        this.apellido = "Vargas";
        this.nombre = "Brandon";
        this.fechaDeNacimiento = LocalDate.of(2004, 7, 21);
        // Se generan notas aleatorias para la persona
        Random random = new Random();
        for (int x = 0; x < notas.length; x++) {
            notas[x] = random.nextInt(101);
        }
    }

    // Método para calcular el promedio de las notas de la persona
    public double promedioR() {
        return promedioR(0, 0); // Llamada inicial al método recursivo
    }

    // Método recursivo privado para calcular el promedio de las notas
    private double promedioR(int a, double acumulado) {
        if (a == 10) {
            return acumulado / 10;
        } else {
            acumulado = acumulado + notas[a];
            return promedioR(a + 1, acumulado);
        }
    }

    // Método toString que devuelve una representación en cadena de la persona
    @Override
    public String toString() {
        double promedio = promedioR();
        return "Nombre: " + nombre + ", Apellido: " + apellido + ", Fecha de Nacimiento: " + fechaDeNacimiento + ", Promedio: " + promedio;
    }

    // Métodos getter y setter para acceder a los atributos de la clase
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int[] getNotas() {
        return notas;
    }

    public void setNotas(int[] notas) {
        this.notas = notas;
    }
}
