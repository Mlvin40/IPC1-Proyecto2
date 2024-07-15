/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend.archivos;

import Backend.Laberinto;
import Backend.Partidas;
import Backend.listas.ListaGenerica;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Phoenix
 */
public final class ArchivosPrograma implements Serializable {

    ListaGenerica<Laberinto> listaLaberintos;
    ListaGenerica<Partidas> listaPartidas;

    private final String laberintoPorDefecto = """
                                               Tablero Inicial
                                               Dimension=30X30
                                               VisionDelJugador=3
                                               VelocidadBot=1
                                               OroNecesarioParaSalir=100
                                               P,P,P,P,P,P,P,P,S,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P
                                               S,C,C,C,C,C,C,P,C,C,C,C,C,C,C,C,C,P,C,C,C,C,C,C,C,C,P,C,C,S
                                               P,P,P,P,P,P,C,C,P,P,P,C,P,P,P,C,P,C,C,P,P,P,P,P,P,C,C,P,C,P
                                               P,C,C,O,C,P,C,P,O,O,P,C,P,O,P,C,P,O,P,C,P,C,C,C,P,P,C,C,C,P
                                               P,C,P,P,C,P,C,C,C,C,P,O,P,C,P,C,P,P,C,C,P,C,P,C,C,P,C,P,C,P
                                               P,O,P,C,C,P,C,P,P,P,P,P,P,C,P,C,C,C,P,C,P,C,P,P,P,P,C,P,C,P
                                               P,C,P,P,C,C,C,C,C,C,P,C,P,C,C,C,C,C,C,C,C,C,P,C,C,C,C,P,P,P
                                               P,P,C,C,C,P,C,P,C,P,C,C,P,P,C,P,P,C,P,P,P,P,P,C,P,C,P,P,O,P
                                               P,C,C,P,C,P,C,C,C,P,C,P,C,P,C,C,P,C,P,C,C,C,C,C,P,C,C,P,O,P
                                               P,C,P,P,C,P,P,P,C,P,C,P,C,P,P,C,P,C,P,P,P,C,P,P,P,P,O,P,C,P
                                               P,C,C,C,C,P,C,P,C,P,C,P,C,P,C,C,P,C,C,O,P,C,C,P,O,P,C,P,C,P
                                               P,P,P,O,P,C,C,C,C,P,C,P,C,P,P,C,P,P,P,P,P,C,P,C,C,P,C,P,C,P
                                               P,O,C,P,C,C,P,P,P,C,C,P,C,P,C,C,C,C,C,C,C,C,C,C,C,P,C,P,C,P
                                               P,P,C,P,P,C,P,C,C,P,C,P,C,C,C,P,C,P,P,C,P,C,C,P,C,P,C,P,C,P
                                               P,C,C,P,C,C,P,C,P,P,C,P,O,P,B,P,P,C,C,P,C,C,P,C,C,P,C,P,C,P
                                               P,P,C,P,P,C,C,C,O,P,C,P,P,P,C,P,C,C,C,C,C,P,C,C,P,P,C,P,C,P
                                               P,C,C,P,C,C,P,C,P,P,C,C,P,C,C,P,C,P,P,P,P,P,C,P,C,P,O,P,C,P
                                               P,P,C,C,C,P,P,P,C,P,C,C,P,P,C,C,C,P,O,C,C,C,C,C,C,C,P,P,C,P
                                               P,O,P,C,P,C,C,C,C,P,C,C,P,P,P,P,P,P,P,P,P,P,P,P,P,C,C,C,C,P
                                               P,O,P,C,C,C,P,P,C,C,C,C,C,O,C,C,C,O,C,C,C,O,C,C,P,P,C,P,C,P
                                               P,C,P,P,P,P,P,C,C,P,P,P,P,P,P,P,P,P,P,P,P,P,P,C,C,P,C,P,P,P
                                               P,C,P,C,C,C,P,C,P,C,C,C,C,C,C,C,C,C,C,C,C,C,C,P,C,P,C,C,C,S
                                               P,C,C,C,P,C,P,C,C,P,C,P,P,P,P,P,P,C,P,P,P,C,C,C,C,P,C,P,P,P
                                               P,P,P,P,P,C,P,C,P,C,C,P,O,C,C,C,P,C,C,C,P,C,P,P,P,P,C,P,C,P
                                               P,C,P,C,C,C,P,C,O,P,C,P,P,P,P,C,P,P,P,C,P,C,C,C,C,P,C,C,C,P
                                               P,C,P,P,P,C,P,P,P,P,C,P,C,C,C,C,P,O,P,C,P,P,P,C,P,O,P,P,C,P
                                               P,C,P,C,C,C,C,C,C,C,C,C,C,P,C,C,P,C,P,C,C,C,C,C,P,C,P,C,C,P
                                               P,C,P,P,P,P,P,P,P,P,P,C,P,C,P,P,P,C,P,C,P,P,P,P,P,C,C,C,P,P
                                               P,C,C,C,C,C,O,C,C,C,C,C,P,C,C,C,C,C,C,C,C,C,C,C,C,C,P,C,O,P
                                               P,P,P,P,P,P,P,P,P,P,P,P,P,S,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P,P""";

    private static final String PATH_ARCHIVO = "RegistroJuego.dat";
    private static final String PATH_REPORTE = "Reporte.html";

    public ArchivosPrograma() {
        //Al instanciar a la clase se tiene que verificar si ya existe un archivo o no
        verificarArchivosJuego();
    }

    public void serializarListas() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH_ARCHIVO))) {
            oos.writeObject(listaLaberintos);
            oos.writeObject(listaPartidas);

            System.out.println("Lista serializada con éxito.");

        } catch (IOException e) {
            System.err.println("Error al serializar la lista: " + e.getMessage());
        }
    }

    // Método para deserializar una lista enlazada
    public void deserializarListas() {
        try (ObjectInputStream oisRegistros = new ObjectInputStream(new FileInputStream(PATH_ARCHIVO))) {

            Object objLaberintos = oisRegistros.readObject();
            Object objPartidas = oisRegistros.readObject();

            //verifica que el objeto deserealizado si sea una instancia de ListaGenerica
            //Se le agrega el valor de la lista guardada a la variable local
            listaLaberintos = (ListaGenerica<Laberinto>) objLaberintos;
            listaPartidas = (ListaGenerica<Partidas>) objPartidas;

            System.out.println("Lista deserializada con éxito.");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al deserializar la lista: " + e.getMessage());
        }
    }

    //Metodo que se utiliza para ver si ya existe un archivo binario o no
    private void verificarArchivosJuego() {
        File archivo = new File(PATH_ARCHIVO);

        if (archivo.exists()) {
            // Si el archivo existe, deserializar la lista de tableros
            deserializarListas();
        } else {
            // Si el archivo no existe, crear uno nuevo y agregar el tablero por defecto
            System.out.println("Se ha creado el tablero por defecto");

            //Se inicializa la lista de tableros
            listaLaberintos = new ListaGenerica<>();
            // Agregar el tablero por defecto a la lista
            Laberinto tableroDefecto = new Laberinto(laberintoPorDefecto);
            listaLaberintos.agregarDato(tableroDefecto);

            System.out.println("Se ha creado una nueva lista de partidas");
            //Se inicializa la lista
            listaPartidas = new ListaGenerica<>();

            // Serializar la lista de tableros al nuevo archivo
            serializarListas();
        }
    }

    public void exportarReporteHTML(String Contenido) {
        File reporte = new File(PATH_REPORTE);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_REPORTE))) {

            //Si no existe el archivo lo crea
            if (!reporte.exists()) {
                reporte.createNewFile();  // Crea el archivo si no existe
            }

            StringBuilder builder = new StringBuilder();
            String[] lineas = Contenido.split("\n");
            for (int i = 0; i < lineas.length; i++) {
                builder.append("<p>").append(lineas[i]).append("</p>").append("\n");
            }

            bw.write("<html>" + "\n"
                    + " <head>" + "\n"
                    + "     <title>Reporte</title>" + "\n"
                    + " </head>" + "\n"
                    + "     <body>" + "\n"
                    + builder.toString()
                    + "     </body>" + "\n"
                    + "</html>");

            JOptionPane.showMessageDialog(null, "Reporte exportado exitosamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al exportar el reporte");
        }
    }

    //Metodo para obtener la lista generica
    public ListaGenerica<Laberinto> getListaLaberintos() {
        return listaLaberintos;
    }

    public ListaGenerica<Partidas> getListaPartidas() {
        return listaPartidas;
    }

    public void agregarUnLaberinto(Laberinto laberinto) {
        listaLaberintos.agregarDato(laberinto);
        System.out.println("laberinto agregado exitosamente");
    }
}
