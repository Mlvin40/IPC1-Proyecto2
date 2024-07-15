/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.repeticiones;

import Backend.listas.ListaGenerica;
import java.io.Serializable;

/**
 *
 * @author Phoenix
 */
public class AccionesEnPartida implements Serializable {

    //
    //Esta clase sirve para guardar todos los movimientos realizados en una partida
    //
    private ListaGenerica<ListaGenerica> movimientosEnXBots;
    private ListaGenerica<ListaGenerica> movimientosEnYBots;

    private ListaGenerica<Integer> movimientosJugadorEnX;
    private ListaGenerica<Integer> movimientosJugadorEnY;

    private String ConfiguracionTablero;

    private int velocidadBot;

    public AccionesEnPartida(ListaGenerica<ListaGenerica> movimientosEnXBots, ListaGenerica<ListaGenerica> movimientosEnYBots, ListaGenerica<Integer> movimientosJugadorEnX, ListaGenerica<Integer> movimientosJugadorEnY, String ConfiguracionTablero) {
        this.movimientosEnXBots = movimientosEnXBots;
        this.movimientosEnYBots = movimientosEnYBots;
        this.movimientosJugadorEnX = movimientosJugadorEnX;
        this.movimientosJugadorEnY = movimientosJugadorEnY;
        this.ConfiguracionTablero = ConfiguracionTablero;
    }

    public ListaGenerica<ListaGenerica> getMovimientosEnXBots() {
        return movimientosEnXBots;
    }

    public ListaGenerica<ListaGenerica> getMovimientosEnYBots() {
        return movimientosEnYBots;
    }

    public ListaGenerica<Integer> getMovimientosJugadorEnX() {
        return movimientosJugadorEnX;
    }

    public ListaGenerica<Integer> getMovimientosJugadorEnY() {
        return movimientosJugadorEnY;
    }

    public String getConfiguracionTablero() {
        return ConfiguracionTablero;
    }

}
