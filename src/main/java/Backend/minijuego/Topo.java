/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.minijuego;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Phoenix
 */
public class Topo extends JButton {

    private String ImagenTopo = "/imgMiniJuego/topo.jpg";
    private String imagenAgujero = "/imgMiniJuego/agujero.jpg";
    private boolean seAtrapo = false;

    public Topo() {
        cambiarImagenAgujero();
    }

    public void cambiarImagenTopo() {
        setEnabled(true);
        ImageIcon icono = new ImageIcon(getClass().getResource(ImagenTopo));
        setIcon(icono);
    }

    public void cambiarImagenAgujero() {
        setEnabled(false);
        ImageIcon icono = new ImageIcon(getClass().getResource(imagenAgujero));
        setIcon(icono);
    }

    public boolean getSeAtrapo() {
        return seAtrapo;
    }

    public void setSeAtrapo(boolean seAtrapo) {
        this.seAtrapo = seAtrapo;
    }
}
