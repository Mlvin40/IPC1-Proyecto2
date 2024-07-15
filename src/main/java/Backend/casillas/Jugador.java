/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.casillas;

import Backend.ControladorPartida;
import Backend.listas.ListaGenerica;
import Frontend.VentanaJuego;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class Jugador extends Casilla implements Serializable {

    ListaGenerica<Integer> movimientosEnX = new ListaGenerica<>();
    ListaGenerica<Integer> movimientosEnY = new ListaGenerica<>();

    public ListaGenerica<Integer> getMovimientosEnX() {
        return movimientosEnX;
    }

    public void setMovimientosEnX(ListaGenerica<Integer> movimientosEnX) {
        this.movimientosEnX = movimientosEnX;
    }

    public ListaGenerica<Integer> getMovimientosEnY() {
        return movimientosEnY;
    }

    public void setMovimientosEnY(ListaGenerica<Integer> movimientosEnY) {
        this.movimientosEnY = movimientosEnY;
    }

    private final int rangoDeVision;

    private int cantidadOro = 0;
    private int movimientoInvalido;

    //filas = x  columnas = y
    private final int filasTablero;
    private final int columnasTablero;

    Casilla guardarPaso = new Camino();
    private int posicionX;
    private int posicionY;

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    private final Casilla[][] tableroLaberinto;

    private final JPanel panelTablero;
    private final VentanaJuego ventanaJuego;
    private ControladorPartida controladorPartida;

    private int cantidadDeMovimientos = 0;

    public Jugador(Casilla[][] tableroLaberinto, JPanel panelTablero, VentanaJuego ventanaJuego, int filas, int columnas, ControladorPartida controladorPartida, int rangoDeVision) {
        super("/imgCasillas/personaje.png");
        this.tableroLaberinto = tableroLaberinto;
        this.panelTablero = panelTablero;
        this.ventanaJuego = ventanaJuego;
        this.filasTablero = filas;
        this.columnasTablero = columnas;
        this.controladorPartida = controladorPartida;
        this.rangoDeVision = rangoDeVision;
        this.movimientoInvalido = 0;
    }

    public void realizarMovimiento(String direccion) {

        switch (direccion) {
            case "a":
                cambiarPosicion(posicionX, posicionY - 1);
                break;

            case "d":
                cambiarPosicion(posicionX, posicionY + 1);
                break;

            case "w":
                cambiarPosicion(posicionX - 1, posicionY);
                break;

            case "s":
                cambiarPosicion(posicionX + 1, posicionY);
                break;

            default:
                break;
        }

        ventanaJuego.actualizarMovimientosValidos(movimientoInvalido);
    }

    private void cambiarPosicion(int x, int y) {
        movimientosEnX.agregarDato(x);
        movimientosEnY.agregarDato(y);

        if (x < 0 || x >= filasTablero || y < 0 || y >= columnasTablero) {
            System.out.println("Coordenadas fuera de los límites del tablero");
            aumentarMovimientosInvalidos();
            return;
        }

        if (tableroLaberinto[x][y] instanceof Pared) {
            //si la casilla es pared no debe de realizar ninguna accion
            aumentarMovimientosInvalidos();

        } else if (tableroLaberinto[x][y] instanceof Camino) {//si es camino debe avanzar 
            reiniciarMovimientosInvalidos();
            avance(x, y);

        } else if (tableroLaberinto[x][y] instanceof CaminoOro) {
            //si se encuentra con una casilla que contiene oro
            reiniciarMovimientosInvalidos();

            //castear la casilla a oro para poder implementar el metodo de sumarOro
            CaminoOro caminoOro = (CaminoOro) tableroLaberinto[x][y];
            //caminoOro.sumarOro(this);*/
            tableroLaberinto[x][y].realizarAccionCasilla(this);
            avance(x, y);
            ventanaJuego.actualizarOro(cantidadOro);
            if (caminoOro.probalibilidadIniciarMinijuego()) {
                repintar();
                controladorPartida.iniciarMiniJuegoWuacamole();
            }
            System.out.println(cantidadOro);
        } else if (tableroLaberinto[x][y] instanceof Salida) {
            //Verificar si tiene el oro necesario para salir
            tableroLaberinto[x][y].realizarAccionCasilla(this);
        } else if (tableroLaberinto[x][y] instanceof Bot) {
            tableroLaberinto[x][y].realizarAccionCasilla(this);
        }
        repintar();
    }

    private void avance(int x, int y) {

        reiniciarMovimientosInvalidos();
        cantidadDeMovimientos++;

        devolverAnterior();
        guardarPaso = tableroLaberinto[x][y];
        verificarDireccion(x, y);

        /*//Para que el controlador sepa la posicion en tiempo real del jugador
        controladorPartida.setPosicionX(posicionX);
        controladorPartida.setPosicionY(posicionY);
         */
        tableroLaberinto[posicionX][posicionY] = this;

    }

    private void devolverAnterior() {
        if (guardarPaso instanceof Camino) {
            tableroLaberinto[posicionX][posicionY] = guardarPaso;
        } else if (guardarPaso instanceof Pared) {
            tableroLaberinto[posicionX][posicionY] = guardarPaso;
        } else if (guardarPaso instanceof CaminoOro) {
            //deberia converse en camino y agregarle oro al jugador
            tableroLaberinto[posicionX][posicionY] = new Camino();
        }
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

    public void aumentarMovimientosInvalidos() {
        movimientoInvalido++;
        System.out.println("movimientos invalidos: " + movimientoInvalido);

        //Verificar si el contador de movimientos invalidos es igual a 3
        if (movimientoInvalido == 3) {
            controladorPartida.matarBots();
            JOptionPane.showMessageDialog(null, "Has perdido por realizar 3 movimientos consecutivos");
            //para indicarle al controlador que el jugador perdio
            controladorPartida.setResultadoPartida("Perdio");
            controladorPartida.getLaberintoSeleccionado().aumentarVecesPerdido();
            controladorPartida.finalizarPartida();

            System.out.println("Has perdido por realizar movimientos invalidos consecutivos");
        }

    }

    private void reiniciarMovimientosInvalidos() {
        movimientoInvalido = 0;
    }

    private void repintar() {
        controladorPartida.repintarTablero();
    }

    public void ganarOro(int oroGanado) {
        cantidadOro += oroGanado;
    }

    @Override
    public void realizarAccionCasilla(Jugador jugador) {
        //Si se paran en esta casilla significa que un bot atrapo al jugador
        //Avisar que se perdio al frontend
        controladorPartida.setFueAtrapado(true);
        cambiarImagen(imagenBot);
        controladorPartida.matarBots();
        JOptionPane.showMessageDialog(null, "Te ha atrapado un bot");
        controladorPartida.setResultadoPartida("Perdio");
        controladorPartida.getLaberintoSeleccionado().aumentarVecesPerdido();
        controladorPartida.finalizarPartida();
    }

    //Metodos Getter and Setter
    public int getCantidadDeMovimientos() {
        return cantidadDeMovimientos;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getCantidadOro() {
        return cantidadOro;
    }

    public void setCantidadOro(int cantidadOro) {
        this.cantidadOro = cantidadOro;
    }

    public ControladorPartida getControladorPartida() {
        return controladorPartida;
    }
}
