/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.repeticiones;

import Backend.Laberinto;
import Backend.archivos.ArchivosPrograma;
import Backend.casillas.Bot;
import Backend.casillas.Casilla;
import Backend.casillas.Jugador;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import Frontend.VentanaJuego;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class Repeticion {

    //Lista que almacena a todos los bots existentes en el mapa 
    ListaGenerica<Thread> botLista = new ListaGenerica<>();
    ListaGenerica<Bot> bots = new ListaGenerica<>();

    //Para acceder al frontend
    private JPanel panelTablero;

    //Para almacenar las dimensiones del laberinto
    private int filas;
    private int columnas;

    //Nombre del laberinto y todas sus casillas
    private String nombreLaberinto;
    private Casilla[][] tableroLaberinto;

    private Jugador jugador;
    private int posicionX;
    private int posicionY;

    AccionesEnPartida accionesEnPartida;

    public Repeticion(AccionesEnPartida accionesEnPartida) throws ListaException {
        this.accionesEnPartida = accionesEnPartida;

        posicionX = accionesEnPartida.getMovimientosJugadorEnX().obtenerValor(0);
        posicionY = accionesEnPartida.getMovimientosJugadorEnY().obtenerValor(0);

    }
}
