/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.laberintos;

import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class DiseñadorLaberinto {

    public boolean verificarPuertas(String Laberinto) {
        int cantidadDePuertas = 0;

        String[] lineas = Laberinto.split("\n");

        for (int i = 5; i < lineas.length; i++) {
            String[] caracter = lineas[i].split(",");

            for (int j = 0; j < caracter.length; j++) {
                if (caracter[j].equalsIgnoreCase("S")) {
                    cantidadDePuertas++;
                }
            }
        }

        //Si al terminar la lectura de la configuracion no hay puertas devuelve null
        if (cantidadDePuertas == 0) {
            JOptionPane.showMessageDialog(null, "Debes agregar al menos una salida");
            return false;
        }
        return true;
    }

    public boolean verificarOro(String Laberinto) {
        int cantidadDeOro = 0;
        int oroParaSalir = 0;

        //Para dividir el arreglo en texto
        String[] lineas = Laberinto.split("\n");

        try {

            String[] oroPuerta = lineas[4].split("=");
            oroParaSalir = Integer.parseInt(oroPuerta[1]);
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Debe de indicar cuando oro se necesita para salir");
            return false;
        }

        //Sabiendo que la configuracion de casiilas empieza en la linea 5
        for (int i = 5; i < lineas.length; i++) {
            String[] caracter = lineas[i].split(",");

            for (int j = 0; j < caracter.length; j++) {
                if (caracter[j].equalsIgnoreCase("O")) {
                    cantidadDeOro++;
                }
            }
        }

        //Promedio de oro generado en el mapa
        cantidadDeOro *= 5;

        //si la cantidad de oro que hay acutualmente en el mapa es menor al oro que se necesita para salir
        if (cantidadDeOro < oroParaSalir) {
            JOptionPane.showMessageDialog(null, "No hay suficiente oro para salir");
            return false;
        }

        return true;
    }
}
