/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.laberintos;

import Backend.Laberinto;
import Backend.casillas.Bot;
import Backend.casillas.Camino;
import Backend.casillas.CaminoOro;
import Backend.casillas.Casilla;
import Backend.casillas.Pared;
import Backend.casillas.Salida;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Phoenix
 */
public class ConstructorDeLaberintos {

    public boolean verificarLaberintoValido(String laberinto) {
        //Inicializacion de las variables
        String[] lineas = null;
        String[] dimensionesLinea = null;
        int filas = 0;
        int columnas = 0;

        //Por si un tablero no tiene definidas las dimensiones se captura el error
        try {

            // Separar el laberinto en líneas
            lineas = laberinto.split("\n");
            // Obtener las dimensiones del laberinto desde la primera línea
            dimensionesLinea = lineas[1].split("="); // Las dimensiones están en la segunda línea
            String[] dimension = dimensionesLinea[1].split("X");
            // Verificar cada fila y columna con las dimensiones
            filas = Integer.parseInt(dimension[0]);
            columnas = Integer.parseInt(dimension[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("error 2024");
        }

        for (int i = 5; i < lineas.length; i++) {
            //Con el metodo trim eliminamos los posibles espacios en blanco antes de iniciar la verificacion
            String[] elementos = lineas[i].trim().split(",");
            if (elementos.length != columnas) {
                return false; // Si la fila no tiene el número correcto de elementos
            }
        }

        return lineas.length - 5 == filas; // Verificar el número de filas
        //Si todo esta bien significa que el tablero si es valido  y retorna verdadero para aplicar los cambios
    }

    //Metodo que muestra los laberintos que existen 
    public String mostrarLaberintosExistentes(ListaGenerica<Laberinto> listaLaberintos) throws ListaException {
        //este string builder sirve para ir agregando a una cadena de texto el nombre del laberinto y en que posicion de la lista está
        StringBuilder stringBuilder = new StringBuilder();
        int tamanio = listaLaberintos.obtenerSize();

        Laberinto laberintoActual;

        //se recorre el total de nodos para poder ver todos los tableros
        for (int i = 0; i < tamanio; i++) {
            laberintoActual = listaLaberintos.obtenerValor(i);
            String contenido = laberintoActual.getConfiguracionLaberinto();
            String[] nombre = contenido.split("\n");
            //conociendo el nombre y el indice en la lista se agrega al builder
            stringBuilder.append(i).append(": ").append(nombre[0]).append("\n");
        }
        return stringBuilder.toString();
    }

    /**
     * Este metodo solamente se utiliza para la visualizacion de un mapa. en el
     * controlador de partida cada uno tiene las acciones agregadas
     *
     * @param laberintoSeleccionado
     * @param nombreLaberinto
     * @param panelTablero
     */
    public void visualizarCasillaDelLaberinto(Laberinto laberintoSeleccionado, JLabel nombreLaberinto, JPanel panelTablero) {
        Casilla[][] tableroLaberinto;
        int filas = 0;
        int columnas = 0;

        String tablero = laberintoSeleccionado.getConfiguracionLaberinto();

        //con esto nos aseguramos de que el archivo que se lee tenga la informacion correspondiente a un tablero
        if (tablero != null && !tablero.isEmpty()) {
            String[] lineas = tablero.split("\n");

            // Extraer el nombre del tablero y las dimensiones
            nombreLaberinto.setText(lineas[0]);
            String[] informacionDimensiones = lineas[1].split("=");

            //Con el split obtenemos las filas y columnas de este tablero
            String[] dimensiones = informacionDimensiones[1].split("X");
            filas = Integer.parseInt(dimensiones[0]);
            columnas = Integer.parseInt(dimensiones[1]);

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
                            tableroLaberinto[x][y].setText("");
                            break;
                        case "B":
                            //Sola para visualizacion
                            tableroLaberinto[x][y] = new Bot(null, tableroLaberinto, panelTablero, filas, columnas, x, y, 0);
                            break;
                        case "S":
                            tableroLaberinto[x][y] = new Salida();
                            break;
                        default:
                            // Si el caracter no coincide, colocamos una pared por defecto
                            tableroLaberinto[x][y] = new Pared();
                    }
                    panelTablero.add(tableroLaberinto[x][y]);
                }
            }
            panelTablero.revalidate();
            panelTablero.repaint();
        }
    }
}
