/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.archivos.ArchivosPrograma;
import Backend.casillas.Bloqueador;
import Backend.casillas.Bot;
import Backend.casillas.Camino;
import Backend.casillas.Casilla;
import Backend.casillas.Pared;
import Backend.casillas.CaminoOro;
import Backend.casillas.Jugador;
import Backend.casillas.Salida;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import Backend.repeticiones.AccionesEnPartida;
import Frontend.VentanaJuego;
import Frontend.VentanaPedirNombrePartida;
import Frontend.minijuego.VentanaMiniJuego;
import java.awt.GridLayout;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class ControladorPartida implements Serializable {

    ArchivosPrograma archivosPrograma = new ArchivosPrograma();

    private String nombreDePartida;

    //Lista que almacena a todos los bots existentes en el mapa 
    ListaGenerica<Thread> botLista = new ListaGenerica<>();
    ListaGenerica<Bot> bots = new ListaGenerica<>();

    //Para acceder al frontend
    private final VentanaJuego ventanaJuego;
    private final JPanel panelTablero;

    //Para almacenar las dimensiones del laberinto
    private int filas;
    private int columnas;

    private int visionDelJugador = 0;
    private int velocidadDelBot = 0;
    private int oroParaEscapar = 0;

    //Nombre del laberinto y todas sus casillas
    private String nombreLaberinto;
    private Casilla[][] tableroLaberinto;
    private Casilla[][] tableroSinVision;

    private Jugador jugador;
    private int posicionX;
    private int posicionY;

    //este sirve para acceder a la configuracion del laberinto y metodos propios
    Laberinto laberintoSeleccionado;

    //Para almacenarlo despues en los reportes
    private int cantidadOroWuacamole;
    //Mostrar la cantidad de veces que se inició el minijuego wuacamole
    private int wuacamoleIniciados;

    //guarda si el jugador perdio o gano
    private String resultadoPartida;

    //sirve para definir al final de la partida si gano o fue atrapado
    private boolean siGano;
    private boolean fueAtrapado;

    //CONSTRUCT0R
    public ControladorPartida(JPanel panelTablero, VentanaJuego ventanaJuego, Laberinto laberintoSeleccionado) {
        this.panelTablero = panelTablero;
        this.ventanaJuego = ventanaJuego;
        this.laberintoSeleccionado = laberintoSeleccionado;
        wuacamoleIniciados = 0;
        siGano = false;
        fueAtrapado = false;
    }

    public void iniciarComponentes() throws ListaException {
        String tablero = laberintoSeleccionado.getConfiguracionLaberinto();

        //Se aumenta las veces que se ha jugado ese laberinto
        laberintoSeleccionado.aumentarVecesJugado();

        //Para obtener  un nombre que identifique a esta partida
        VentanaPedirNombrePartida pedirNombre = new VentanaPedirNombrePartida(ventanaJuego, true, this);
        pedirNombre.setVisible(true);

        //con esto nos aseguramos de que el archivo que se lee tenga la informacion correspondiente a un tablero
        if (tablero != null && !tablero.isEmpty()) {
            String[] lineas = tablero.split("\n");

            // Extraer el nombre del tablero y las dimensiones
            this.nombreLaberinto = lineas[0];
            String[] informacionDimensiones = lineas[1].split("=");

            //Con el split obtenemos las filas y columnas de este tablero
            String[] dimensiones = informacionDimensiones[1].split("X");
            this.filas = Integer.parseInt(dimensiones[0]);
            this.columnas = Integer.parseInt(dimensiones[1]);

            //Se obtiene el rango de vision del jugador
            String[] sobreLaVision = lineas[2].split("=");
            this.visionDelJugador = Integer.parseInt(sobreLaVision[1]);

            //Se obtien la velocidad que tendran los bots
            String[] sobreElBot = lineas[3].split("=");
            this.velocidadDelBot = Integer.parseInt(sobreElBot[1]);

            //se obtiene la cantidad necesaria de oro para salir del laberinto
            String[] SobreLaSalida = lineas[4].split("=");
            this.oroParaEscapar = Integer.parseInt(SobreLaSalida[1]);

            //para el tablero que no tendra vision
            tableroSinVision = new Casilla[filas][columnas];
            crearTableroOfuscado();

            //Para el tablero que contiene las diferentes casillas
            tableroLaberinto = new Casilla[filas][columnas];
            panelTablero.setLayout(new GridLayout(filas, columnas));

            for (int x = 0; x < filas; x++) {
                String[] caracteres = lineas[x + 5].split(",");
                for (int y = 0; y < columnas; y++) {
                    switch (caracteres[y]) {
                        case "C":
                            tableroLaberinto[x][y] = new Camino();
                            break;
                        case "P":
                            tableroLaberinto[x][y] = new Pared();
                            break;
                        case "O":
                            tableroLaberinto[x][y] = new CaminoOro();
                            break;
                        case "B":
                            Thread botHilo;
                            tableroLaberinto[x][y] = new Bot(this, tableroLaberinto, panelTablero, filas, columnas, x, y, velocidadDelBot);
                            botHilo = new Thread((Bot) tableroLaberinto[x][y]);

                            //se agrega a la lista de botLista
                            botLista.agregarDato(botHilo);
                            bots.agregarDato((Bot) tableroLaberinto[x][y]);
                            break;
                        case "S":
                            tableroLaberinto[x][y] = new Salida(oroParaEscapar);
                            break;
                        default:
                            tableroLaberinto[x][y] = new Pared(); // Si el caracter no coincide, colocamos una pared por defecto
                    }
                    panelTablero.add(tableroLaberinto[x][y]);
                }
            }
            agregarJugador();
            ventanaJuego.actualizarNombreLaberinto(nombreLaberinto);
            panelTablero.revalidate();
            panelTablero.repaint();
        }

        System.out.println("Nombre del tablero: " + nombreLaberinto);
        System.out.println("Filas: " + filas);
        System.out.println("Columnas: " + columnas);
        ventanaJuego.mostrarInformacion(oroParaEscapar, bots.obtenerSize(), velocidadDelBot);

        iniciarBots();
    }

    //metodo que se utiliza para que el jugador encuentre una posicion en el tablero 
    private void agregarJugador() {
        boolean encontroLugar = false;
        Random random = new Random();
        int rndFila;
        int rndColumna;

        do {
            rndFila = random.nextInt(filas);
            rndColumna = random.nextInt(columnas);

            if (tableroLaberinto[rndFila][rndColumna] instanceof Camino) {
                tableroLaberinto[rndFila][rndColumna].cambiarImagen("/imgCasillas/personaje.png");
                tableroLaberinto[rndFila][rndColumna] = new Jugador(tableroLaberinto, panelTablero, ventanaJuego, filas, columnas, this, visionDelJugador);

                //Para que el tablero conozca al jugador
                jugador = (Jugador) tableroLaberinto[rndFila][rndColumna];
                ventanaJuego.setJugador(jugador);

                posicionX = rndFila;
                posicionY = rndColumna;

                jugador.setPosicionX(posicionX);
                jugador.setPosicionY(posicionY);

                encontroLugar = true;
            }
        } while (!encontroLugar);
    }

    //Con este metodo se inicia todos los bots almacenados en la lista enlazada
    public void iniciarBots() {

        int tamanio = botLista.obtenerSize();
        for (int i = 0; i < tamanio; i++) {
            Thread botActual;
            try {
                botActual = botLista.obtenerValor(i);
                botActual.start();
            } catch (ListaException ex) {
                System.out.println("error 2024");
            } catch (Exception e) {
                System.out.println("Error al iniciar el bot: " + e.getMessage());
            }
        }
    }

    public void pausarBots() {

        int tamanio = bots.obtenerSize();
        for (int i = 0; i < tamanio; i++) {
            Bot botActual;
            try {
                botActual = bots.obtenerValor(i);
                botActual.apagar();
            } catch (ListaException ex) {
                System.out.println("error 2024");
            } catch (Exception e) {
                System.out.println("Error al iniciar el bot: " + e.getMessage());
            }
        }
    }

    public void prenderBots() {
        int tamanio = bots.obtenerSize();
        for (int i = 0; i < tamanio; i++) {
            Bot botActual;
            try {
                botActual = bots.obtenerValor(i);
                botActual.prender();
            } catch (ListaException ex) {
                System.out.println("error 2024");
            } catch (Exception e) {
                System.out.println("Error al iniciar el bot: " + e.getMessage());
            }
        }
    }

    //cuando se inicia  un minijuego los bots se pausan para evitar que atrapen al usuario
    //mientras esta visualizando la ventana del minujuego
    public void iniciarMiniJuegoWuacamole() {
        pausarBots();

        JOptionPane.showMessageDialog(null, "Se iniciara el MiniJuego Wuacamole");
        ventanaJuego.setVisible(false);
        VentanaMiniJuego ventanaMiniJuego = new VentanaMiniJuego(jugador, ventanaJuego, this);
        ventanaMiniJuego.setVisible(true);
    }

    public void matarBots() {
        int tamanio = bots.obtenerSize();
        for (int i = 0; i < tamanio; i++) {
            Bot botActual;
            try {
                botActual = bots.obtenerValor(i);
                botActual.finalizarEjecucion();
            } catch (ListaException ex) {
                System.out.println("error 2024");
            } catch (Exception e) {
                System.out.println("Error 2024");
            }
        }
    }

    public void finalizarPartida() {

        ventanaJuego.terminarElJuegoActual();
        matarBots();

        //Obtener los movimientos para la repeticion
        AccionesEnPartida accionesEnPartida = new AccionesEnPartida(obtenerDatosBotEnX(), obtenerDatosBotEnY(),
                jugador.getMovimientosEnX(), jugador.getMovimientosEnY(),
                laberintoSeleccionado.getConfiguracionLaberinto());

        //Se debe de guardar aqui todo lo necesario  para los reportes y repeticiones
        Partidas partida = new Partidas(nombreLaberinto, jugador.getCantidadOro(), nombreDePartida, jugador.getCantidadDeMovimientos(),
                listaMovimientoDeLosBots(), cantidadOroWuacamole,
                wuacamoleIniciados, resultadoPartida, verificarSiGano(), accionesEnPartida, verificarSiFueAtrapado());

        archivosPrograma.getListaPartidas().agregarDato(partida);
        archivosPrograma.serializarListas();
    }

    private ListaGenerica<ListaGenerica> obtenerDatosBotEnX() {
        ListaGenerica<ListaGenerica> listadoDeLosBotsX = new ListaGenerica<>();

        for (int i = 0; i < bots.obtenerSize(); i++) {
            try {
                listadoDeLosBotsX.agregarDato(bots.obtenerValor(i).getMovimientosEnX());
            } catch (ListaException ex) {
                Logger.getLogger(ControladorPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listadoDeLosBotsX;
    }

    private ListaGenerica<ListaGenerica> obtenerDatosBotEnY() {
        ListaGenerica<ListaGenerica> listadoDeLosBotsY = new ListaGenerica<>();

        for (int i = 0; i < bots.obtenerSize(); i++) {
            try {
                listadoDeLosBotsY.agregarDato(bots.obtenerValor(i).getMovimientosEnY());
            } catch (ListaException ex) {
                Logger.getLogger(ControladorPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listadoDeLosBotsY;
    }

    /**
     * Este metodo sirve para obtener la cantidad total de movimientos realizado
     * por un bot
     *
     * @return
     */
    private ListaGenerica<Integer> listaMovimientoDeLosBots() {
        ListaGenerica<Integer> listaMovimientosBots = new ListaGenerica<>();

        for (int i = 0; i < bots.obtenerSize(); i++) {
            try {
                listaMovimientosBots.agregarDato(bots.obtenerValor(i).getCantidadMovimientos());
            } catch (ListaException ex) {
                Logger.getLogger(ControladorPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaMovimientosBots;
    }

    /**
     * El area de vision siempre sera en base a la posicion actual del jugador
     * por lo que cuando un bot actualiza el mapa no se pierde el enfoque en el
     * jugador y su area de vision
     */
    public void repintarTablero() {
        panelTablero.removeAll();

        posicionX = jugador.getPosicionX();
        posicionY = jugador.getPosicionY();

        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                if (esCasillaVisible(posicionX, posicionY, x, y)) {
                    panelTablero.add(tableroLaberinto[x][y]);
                } else {
                    panelTablero.add(tableroSinVision[x][y]);
                }
            }
        }
        panelTablero.revalidate();
        panelTablero.repaint();

    }

    /**
     * retorna una posicion valida dentro del rango de vision
     *
     * @param posicionX
     * @param posicionY
     * @param x
     * @param y
     * @return
     */
    private boolean esCasillaVisible(int posicionX, int posicionY, int x, int y) {
        //Se obtiene el valor absoluto
        int distanciaX = Math.abs(x - posicionX);
        int distanciaY = Math.abs(y - posicionY);

        //Con la formula de distancia entre dos puntos
        //d = √(x1-x2)²+(y1-y2)²
        int distanciaXCuadrado = distanciaX * distanciaX;
        int distanciaYCuadrado = distanciaY * distanciaY;

        double distanciaEuclidiana = Math.sqrt(distanciaXCuadrado + distanciaYCuadrado);

        //Verifica si la distancia cumple 
        return distanciaEuclidiana < visionDelJugador;
    }

    private void crearTableroOfuscado() {
        for (int x = 0; x < filas; x++) {
            for (int y = 0; y < columnas; y++) {
                tableroSinVision[x][y] = new Bloqueador();
            }
        }
    }

    private int verificarSiGano() {
        if (siGano) {
            return 1;
        } else {
            return 0;
        }
    }

    private int verificarSiFueAtrapado() {
        if (fueAtrapado) {
            return 1;
        } else {
            return 0;
        }
    }

    //METODOS GETTERS AND SETTERS   
    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public String getNombreDePartida() {
        return nombreDePartida;
    }

    public void setNombreDePartida(String nombreDePartida) {
        this.nombreDePartida = nombreDePartida;
    }

    public VentanaJuego getVentanaJuego() {
        return ventanaJuego;
    }

    public void setCantidadOroWuacamole(int cantidadOroWuacamole) {
        this.cantidadOroWuacamole = cantidadOroWuacamole;
    }

    public int getCantidadOroWuacamole() {
        return cantidadOroWuacamole;
    }

    public void setResultadoPartida(String resultadoPartida) {
        this.resultadoPartida = resultadoPartida;
    }

    public int getWuacamoleIniciados() {
        return wuacamoleIniciados;
    }

    public void setWuacamoleIniciados(int wuacamoleIniciados) {
        this.wuacamoleIniciados = wuacamoleIniciados;
    }

    public ArchivosPrograma getArchivosPrograma() {
        return archivosPrograma;
    }

    public void setSiGano(boolean siGano) {
        this.siGano = siGano;
    }

    public Laberinto getLaberintoSeleccionado() {
        return laberintoSeleccionado;
    }

    public void setFueAtrapado(boolean fueAtrapado) {
        this.fueAtrapado = fueAtrapado;
    }

}
