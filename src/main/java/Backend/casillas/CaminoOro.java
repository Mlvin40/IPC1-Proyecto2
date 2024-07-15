/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.casillas;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Phoenix
 */
public class CaminoOro extends Casilla {

    Random random = new Random();

    private int oro;

    public CaminoOro() {
        this.oro = oroAleatorio();
        this.setText(oro + "");
        setBackground(Color.YELLOW);
        setOpaque(true);
    }

    private int oroAleatorio() {
        //4-9 de oro por casilla
        int rnd = random.nextInt(6) + 4;
        return rnd;
    }

    @Override
    public void realizarAccionCasilla(Jugador jugador) {
        int oroActual = jugador.getCantidadOro();
        int oroTotal = oroActual + oro;
        jugador.setCantidadOro(oroTotal);
    }

    /**
     * Probabilidad de iniciar el minujuego en un 40%
     * @return
     */
    public boolean probalibilidadIniciarMinijuego() {
        Random random = new Random();

        int probalidad = random.nextInt(101);
        if (probalidad < 30) {

            return true;
        } else {
            return false;
        }
    }
}
