/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.repeticiones;

import Backend.casillas.Bot;
import Backend.casillas.Camino;
import Backend.casillas.CaminoOro;
import Backend.casillas.Casilla;
import Backend.casillas.Jugador;
import Backend.casillas.Pared;
import Backend.casillas.Salida;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class JugadorRepeticion extends Casilla implements Runnable {

    ListaGenerica<Integer> movimientosX;
    ListaGenerica<Integer> movimientosY;

    private int contadorX;
    private int contadorY;

    private int posX;
    private int posY;

    private Casilla[][] tableroLaberinto;
    private Casilla guardarPaso = new Camino();

    public JugadorRepeticion(ListaGenerica<Integer> movimientosX, ListaGenerica<Integer> movimientosY, Casilla[][] tableroLaberinto) {
        this.movimientosX = movimientosX;
        this.movimientosY = movimientosY;
        this.tableroLaberinto = tableroLaberinto;
    }

    public void empezarRepeticion() {

    }

    @Override
    public void realizarAccionCasilla(Jugador jugador) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
