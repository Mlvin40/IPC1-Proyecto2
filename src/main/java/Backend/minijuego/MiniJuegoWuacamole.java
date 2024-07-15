/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.minijuego;

import Backend.casillas.Casilla;
import Frontend.minijuego.VentanaMiniJuego;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class MiniJuegoWuacamole extends Thread {

    private int cantidadTopos;
    private int velocidadTopo;
    private final int dimensionTablero;
    private final Topo[][] topos;
    private VentanaMiniJuego ventanaMiniJuego;
    private JPanel panelTablero;
    private int oroRecolectado = 0;

    public MiniJuegoWuacamole(VentanaMiniJuego ventanaJuego, JPanel panelTablero) {
        this.dimensionTablero = elegirDimensionTablero();
        this.cantidadTopos = elegirCantidadDeTopos();
        this.ventanaMiniJuego = ventanaJuego;
        this.panelTablero = panelTablero;

        //en base a la dimension elegida se crea el tablero que contendra a los topos
        topos = new Topo[dimensionTablero][dimensionTablero];

        ventanaJuego.actualizarTopos(cantidadTopos);

        iniciarComponente();
        funcionBotones();

    }

    @Override
    public void run() {
        Random random = new Random();

        while (cantidadTopos != 0) {
            try {
                Thread.sleep(300); //Pausa Entre la salida de cada topo
            } catch (InterruptedException e) {
                System.out.println("pausa");
            }

            //Logica de la aparicion de cada topo
            int posicionX = random.nextInt(dimensionTablero);
            int posicionY = random.nextInt(dimensionTablero);

            //elije una velocidad para el topo
            velocidadTopo = random.nextInt(500) + 1000;
            topos[posicionX][posicionY].cambiarImagenTopo();

            try {
                Thread.sleep(velocidadTopo); //Tiempo de espera que el topo esta afuera

                if (!topos[posicionX][posicionY].getSeAtrapo()) {
                    //se quita un topo al contador si el jugador no lo atrapo
                    restarTopo();
                    topos[posicionX][posicionY].cambiarImagenAgujero();
                }
                //reiniciar la variable que indica si el topo fue atrapado o no
                topos[posicionX][posicionY].setSeAtrapo(false);

            } catch (InterruptedException e) {
                System.out.println("error 2024");
            }
        }
        JOptionPane.showMessageDialog(null, "Se ha terminado el minijuego. has ganado " + oroRecolectado + " de oro");
        ventanaMiniJuego.finalizarJuego(oroRecolectado);
        //Fin del Minijuego
    }

    private int elegirDimensionTablero() {
        //Puede ser 3x3, 4x4, 5x5

        Random random = new Random();
        int elegir = random.nextInt(3);

        switch (elegir) {
            case 0:
                return 3;

            case 1:
                return 4;

            case 2:
                return 5;

            default:
                //Por si alguna razon llega aqui, el tablero sera de 3x3
                return 3;
        }
    }

    private int elegirCantidadDeTopos() {
        //pueden ser de 6 a 10 topos

        Random random = new Random();
        int elegir = random.nextInt(5) + 6;

        return elegir;
    }

    private void iniciarComponente() {
        panelTablero.setLayout(new GridLayout(dimensionTablero, dimensionTablero));

        for (int x = 0; x < dimensionTablero; x++) {
            for (int y = 0; y < dimensionTablero; y++) {

                topos[x][y] = new Topo();
                panelTablero.add(topos[x][y]);
            }
        }

        panelTablero.revalidate();
        panelTablero.repaint();
    }

    private void funcionBotones() {
        for (int x = 0; x < dimensionTablero; x++) {
            for (int y = 0; y < dimensionTablero; y++) {
                final int filaActual = x;
                final int columnaActual = y;

                topos[x][y].addActionListener(new ActionListener() {
                    //Agregar la funcion al presionar un boton
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //Acciones a realizar si se atrapa a un topo
                        ganarOro();
                        restarTopo();
                        topos[filaActual][columnaActual].cambiarImagenAgujero();
                        topos[filaActual][columnaActual].setSeAtrapo(true);
                    }
                });
            }
        }
    }

    private void ganarOro() {
        //cada vez que  el jugador atrapa a un topo gana 1 de oro
        oroRecolectado++;

        ventanaMiniJuego.actualizarOroRecolectado(oroRecolectado);
    }

    private void restarTopo() {
        cantidadTopos--;
        ventanaMiniJuego.actualizarTopos(cantidadTopos);
    }
}
