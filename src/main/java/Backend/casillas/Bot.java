/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.casillas;

import Backend.ControladorPartida;
import Backend.listas.ListaGenerica;
import java.io.Serializable;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class Bot extends Casilla implements Runnable, Serializable {

    private boolean estaIniciado;

    private int cantidadMovimientos = 0;

    ListaGenerica<Integer> movimientosEnX = new ListaGenerica<>();
    ListaGenerica<Integer> movimientosEnY = new ListaGenerica<>();

    public ListaGenerica<Integer> getMovimientosEnX() {
        return movimientosEnX;
    }

    public ListaGenerica<Integer> getMovimientosEnY() {
        return movimientosEnY;
    }

    private boolean terminado = false;

    private final Casilla[][] tableroLaberinto;
    private final JPanel panelTablero;

    //filas = x  columnas = y
    private final int filasTablero;
    private final int columnasTablero;

    ControladorPartida controladorPartida;

    Casilla guardarPaso = new Camino();
    private int posicionX;
    private int posicionY;

    private final int velocidadDeMovimiento;

    public Bot(ControladorPartida controladorPartida, Casilla[][] tableroLaberinto, JPanel panelTablero, int filas, int columnas, int posicionX, int posicionY, int velocidadBot) {
        super("/imgCasillas/bot.png");

        this.controladorPartida = controladorPartida;
        this.tableroLaberinto = tableroLaberinto;
        this.panelTablero = panelTablero;
        this.filasTablero = filas;
        this.columnasTablero = columnas;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.velocidadDeMovimiento = definirVelocidad(velocidadBot);
        this.estaIniciado = true;

    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            if (estaIniciado) {
                int movimiento = random.nextInt(4);

                // Realizar un movimiento aleatorio
                switch (movimiento) {
                    case 0:
                        cambiarPosicion(posicionX, posicionY - 1);
                        break;

                    case 1:
                        cambiarPosicion(posicionX, posicionY + 1);
                        break;

                    case 2:
                        cambiarPosicion(posicionX - 1, posicionY);
                        break;

                    case 3:
                        cambiarPosicion(posicionX + 1, posicionY);
                        break;

                    default:
                        break;
                }

                try {
                    Thread.sleep(velocidadDeMovimiento); //Se duerme el hilo en base a lo definido en el constructor de laberintos
                } catch (InterruptedException e) {
                    // Manejar la interrupción si es necesario
                    System.out.println("algo paso.. ver si llega hasta aqui");
                    //e.printStackTrace();
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("ErrorCapturrado 2404");
                    //e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(100);  // Si está pausado, esperar antes de volver a verificar
                } catch (InterruptedException e) {
                    System.out.println("El hilo del bot ha sido interrumpido mientras está pausado.");
                }
            }
            if (terminado) {
                break;
            }
        }
        {
        }
        System.out.println("el hilo bot ha finalizado");
    }

    /**
     * Metodo que verifica si una posicion es valida. de ser asi se realiza el
     * movimiento
     *
     * @param x
     * @param y
     */
    public void finalizarEjecucion() {
        terminado = true;
    }

    private void cambiarPosicion(int x, int y) {
        //guarda todos los movimientos hechos para luego utilizarlos en la repeticion
        movimientosEnX.agregarDato(x);
        movimientosEnY.agregarDato(y);

        if (x < 0 || x >= filasTablero || y < 0 || y >= columnasTablero) {
            System.out.println("Movimiento inválido: coordenadas fuera de los límites del tablero");
            return;
        }
        if (tableroLaberinto[x][y] instanceof Camino) {
            //si es camino debe avanzar
            avanceBot(x, y);
            repintar();
        } else if (tableroLaberinto[x][y] instanceof CaminoOro) {
            //si se encuentra con una casilla que contiene oro      
            avanceBot(x, y);
            repintar();
        } else if (tableroLaberinto[x][y] instanceof Jugador) {

            //Para mostrar la animacion correctamente
            this.cambiarImagen(imagenCamino);
            //si el bot atrapa  al jugador se realizan lo programado en el metodo de la clas Jugador
            Jugador player = (Jugador) tableroLaberinto[x][y];
            tableroLaberinto[x][y].realizarAccionCasilla(player);
        } else {
            //No hacer ningun Movimiento
        }
    }

    private void avanceBot(int x, int y) {
        cantidadMovimientos++;
        devolverAnterior();
        guardarPaso = tableroLaberinto[x][y];
        verificarDireccion(x, y);
        tableroLaberinto[posicionX][posicionY] = this;

    }

    private void devolverAnterior() {
        tableroLaberinto[posicionX][posicionY] = guardarPaso;
    }

    //este metodo se encarga de ver hacia que direccio se movio el jugador
    private void verificarDireccion(int x, int y) {
        if (x == posicionX - 1) {
            posicionX--;
        } else if (x == posicionX + 1) {
            posicionX++;
        } else if (y == posicionY - 1) {
            posicionY--;
        } else if (y == posicionY + 1) {
            posicionY++;
        }
    }

    private void repintar() {
        controladorPartida.repintarTablero();
    }

    @Override
    public void realizarAccionCasilla(Jugador jugador) {
        //Si se paran en esta casilla
        //Avisar que se perdio al frontend

        controladorPartida.setFueAtrapado(true);
        jugador.cambiarImagen(imagenCamino);
        controladorPartida.matarBots();
        JOptionPane.showMessageDialog(null, "Has perdido, Te atrapo un bot");
        controladorPartida.setResultadoPartida("Perdio");
        controladorPartida.getLaberintoSeleccionado().aumentarVecesPerdido();

        jugador.getControladorPartida().finalizarPartida();
    }

    public void apagar() {
        estaIniciado = false;
    }

    public void prender() {
        estaIniciado = true;
    }

    /**
     * Con este metodo se define cuantas casilla se puede mover un bot como
     * maximo en un segundo
     *
     * @param velocidad
     * @return
     */
    private int definirVelocidad(int velocidad) {

        int milisegundos = 1000;

        if (velocidad <= 0) {
            return milisegundos;
        } else {
            return milisegundos / velocidad;
        }
    }

    public int getCantidadMovimientos() {
        return cantidadMovimientos;
    }

}
