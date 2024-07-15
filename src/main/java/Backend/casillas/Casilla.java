/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.casillas;

import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Phoenix
 */
public abstract class Casilla extends JLabel implements Serializable {

    protected String imagenJugador = "/imgCasillas/personaje.png";
    protected String imagenBot = "/imgCasillas/bot.png";
    protected String imagenPared = "/imgCasillas/pared.png";
    protected String imagenSalida = "/imgCasillas/salida.png";
    protected String imagenCamino = "/imgCasillas/camino.jpg";

    private String PATH_IMAGEN;

    //Para las clase hijas que no ocupan imagen como el oro
    public Casilla() {
    }

    public Casilla(String PATH_IMAGEN) {
        this.PATH_IMAGEN = PATH_IMAGEN;
        //setIcon(new ImageIcon(getClass().getResource(this.PATH_IMAGEN)));
        cambiarImagen(PATH_IMAGEN);
    }

    public void cambiarImagen(String rutaImagen) {
        ImageIcon icono = new ImageIcon(getClass().getResource(rutaImagen));
        setIcon(icono);
    }

    public String getPATH_IMAGEN() {
        return PATH_IMAGEN;
    }

    public abstract void realizarAccionCasilla(Jugador jugador);

}
