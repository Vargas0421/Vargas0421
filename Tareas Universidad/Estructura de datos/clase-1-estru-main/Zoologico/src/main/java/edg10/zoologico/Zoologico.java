/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edg10.zoologico;

import java.time.LocalDate;
import java.util.HashSet;

/**
 *
 * @author deiv
 */
public class Zoologico {

    public static void main(String[] args) {
        Tigre primerTigre = new Tigre("Tony");
        primerTigre.setTipoTigre("Asiático");
        System.out.println("**** TIGRE ****");
        System.out.println();
        primerTigre.presentarTigre();
        primerTigre.tigreComiendo(0);
        primerTigre.tigreComiendo(1);

        Tortuga primerTortuga = new Tortuga();
        primerTortuga.setNombre("Marta");
        primerTortuga.setTipo("marina");
        primerTortuga.setFechaIngreso(LocalDate.MIN);
        System.out.println("****Tortuga****" + "\n");
        primerTortuga.presentarTortuga();

        System.out.println();
        System.out.println("**** OSOS ****");
        System.out.println();
        Oso primerOso = new Oso("Polar");
        Oso segundoOso = new Oso("Pardo");
        Oso tercerOso = new Oso("Panda");

        primerOso.setColorOso("Polar");
        segundoOso.setColorOso("Pardo");
        tercerOso.setColorOso("Panda");

        primerOso.presentarOso();
        segundoOso.presentarOso();
        tercerOso.presentarOso();

        primerOso.setHibernando(true);
        segundoOso.setHibernando(false);
        tercerOso.setHibernando(false);

        tercerOso.indicadorDeHibernacion();
        segundoOso.indicadorDeHibernacion();
        primerOso.indicadorDeHibernacion();
    }
}
