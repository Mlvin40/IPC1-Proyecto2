/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.casillas;

import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public class Salida extends Casilla {

    private int oroParaSalir = 0;

    public Salida(int oroParaSalir) {
        super("/imgCasillas/salida.png");
        this.oroParaSalir = oroParaSalir;
    }

    public Salida() {
        //Constructor solamente para visualizar
        super("/imgCasillas/salida.png");
        //Constructor Para Visualizar
    }

    @Override
    public void realizarAccionCasilla(Jugador jugador) {
        if (jugador.getCantidadOro() >= oroParaSalir) {
            jugador.getControladorPartida().matarBots();
            JOptionPane.showMessageDialog(null, "Felicidades, has escapado del laberinto");
            jugador.getControladorPartida().getLaberintoSeleccionado().aumentarVecesGanado();
            jugador.getControladorPartida().setResultadoPartida("Ganó");
            jugador.getControladorPartida().setSiGano(true);
            jugador.getControladorPartida().finalizarPartida();
        } else {
            JOptionPane.showMessageDialog(null, "Te hace falta " + (oroParaSalir - jugador.getCantidadOro()) + " de oro para escapar del laberinto");
            System.out.println("No tienes suficiente oro para escapar");
            jugador.aumentarMovimientosInvalidos();
        }
    }

    public int getOroParaSalir() {
        return oroParaSalir;
    }
}
