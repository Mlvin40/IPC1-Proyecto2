/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import Backend.repeticiones.AccionesEnPartida;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Phoenix
 */
public class Partidas implements Serializable {

    private final String nombreDePartida;

    private final String laberintoJugado;
    //Mostrar cantidad de oro recolectado.
    private final int oroRecolectado;

    //Mostrar la cantidad de movimientos realizados para salir/perder.
    private final int cantidadMovimientos;

    //Mostrar la cantidad de movimientos que realizó cada bot para atraparlo (si lo atrapa).
    private final ListaGenerica<Integer> bots;

    //Mostrar cantidad de oro ganado con el minijuego wuacamole
    private final int cantidadOroWuacamole;

    //Mostrar la cantidad de veces que se inició el minijuego wuacamole
    private final int wuacamoleJugados;

    private final String resultadoPartida;

    private int contadorVictoria;
    private int contadorAtrapado;

    private final AccionesEnPartida accionesEnPartida;

    public Partidas(String laberintoJugado, int oroRecolectado, String nombreDePartida,
            int catidadMovimientos, ListaGenerica<Integer> bots, int cantidadOroWuacamole,
            int wacamoleJugados, String resultadoPartida, int contadorVictoria,
            AccionesEnPartida accionesEnPartida, int contadorAtrapado) {

        this.laberintoJugado = laberintoJugado;
        this.oroRecolectado = oroRecolectado;
        this.nombreDePartida = nombreDePartida;
        this.cantidadMovimientos = catidadMovimientos;
        this.bots = bots;
        this.cantidadOroWuacamole = cantidadOroWuacamole;
        this.wuacamoleJugados = wacamoleJugados;
        this.resultadoPartida = resultadoPartida;
        this.contadorVictoria = contadorVictoria;
        this.accionesEnPartida = accionesEnPartida;
        this.contadorAtrapado = contadorAtrapado;
    }

    /**
     * Se contruye todo con un string builder
     *
     * @return
     */
    public String mostrarReportes() {
        StringBuilder sb = new StringBuilder();

        sb.append("LABERINTO JUGADO: ").append(laberintoJugado).append("\n");
        sb.append("\n");
        sb.append("Cantidad de oro recolectado: ").append(oroRecolectado).append("\n");
        sb.append("Nombre de la partida :").append(nombreDePartida).append("\n");
        sb.append("Cantidad de movimientos realizador por el jugador: ").append(cantidadMovimientos).append("\n");
        sb.append(resultadoPartida).append("\n");

        sb.append("Cantidad de Movimientos realizado por cada bot").append("\n");
        for (int i = 0; i < bots.obtenerSize(); i++) {
            try {
                sb.append("Bot ").append(i).append(": ").append(bots.obtenerValor(i)).append("\n");
            } catch (ListaException ex) {
                Logger.getLogger(Partidas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        sb.append("Cantidad de oro ganado en el minijuego wuacamole: ").append(cantidadOroWuacamole).append("\n");
        sb.append("Cantidad de veces que se inicio el minijuego wuacamole: ").append(wuacamoleJugados);
        return sb.toString();
    }

    public String getNombreDePartida() {
        return nombreDePartida;
    }

    public int getOroRecolectado() {
        return oroRecolectado;
    }

    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

    public int getCantidadOroWuacamole() {
        return cantidadOroWuacamole;
    }

    public int getWuacamoleJugados() {
        return wuacamoleJugados;
    }

    public int getContadorVictoria() {
        return contadorVictoria;
    }

    public int getContadorAtrapado() {
        return contadorAtrapado;
    }
    
    
}

