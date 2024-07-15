/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.archivos.ArchivosPrograma;
import Backend.listas.ListaException;
import Backend.listas.ListaGenerica;

/**
 *
 * @author Phoenix
 */
public class ReportesGenerales {

    ArchivosPrograma archivosPrograma = new ArchivosPrograma();

    ListaGenerica<Laberinto> listaLaberintos = new ListaGenerica<>();
    ListaGenerica<Partidas> listaPartidas = new ListaGenerica<>();

    //Retornar todo ya en  un formato
    public ReportesGenerales() {

        this.listaLaberintos = archivosPrograma.getListaLaberintos();
        this.listaPartidas = archivosPrograma.getListaPartidas();
    }

    public String mostrarReporteGeneral() throws ListaException {
        StringBuilder sb = new StringBuilder();

        sb.append("Cantidad total de veces que fue atrapado por los bots: ").append(cantidadTotalVecesAtrapado()).append("\n");
        sb.append("Cantidad total de partidas ganadas: ").append(cantidadTotalPartidasGanadas()).append("\n");
        sb.append("Promedio de oro por partida: ").append(calcularPromedioOro()).append("\n");
        sb.append("Promedio de movimientos por partidas: ").append(calcularPromedioMovimientos()).append("\n");
        sb.append(mostrarLaberintoMasJugado()).append("\n");
        sb.append(mostrarLaberintoMasPerdido()).append("\n");
        sb.append(mostrarLaberintoMasGanado()).append("\n");
        sb.append("Total de Laberintos Existentes: ").append(listaLaberintos.obtenerSize()).append("\n");
        sb.append("Cantidad de oro total ganado con el minijuego wuacamole: ").append(cantidadDeOroTotalMinujuego()).append("\n");
        sb.append("Cantidad total de veces que se inicio el minujuego wuacamole: ").append(cantidadMinujuegoIniciados());

        return sb.toString();
    }

    private double calcularPromedioOro() throws ListaException {
        int sumaOro = 0;

        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaOro += listaPartidas.obtenerValor(i).getOroRecolectado();
        }

        double promedioOro = (double) sumaOro / listaPartidas.obtenerSize();
        return promedioOro;
    }

    private double calcularPromedioMovimientos() throws ListaException {
        int sumaMovimientos = 0;

        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaMovimientos += listaPartidas.obtenerValor(i).getCantidadMovimientos();
        }

        double promedioMovimientos = (double) sumaMovimientos / listaPartidas.obtenerSize();

        return promedioMovimientos;
    }

    private String mostrarLaberintoMasJugado() throws ListaException {
        Laberinto[] laberintoMasJugado = new Laberinto[listaLaberintos.obtenerSize()];

        for (int i = 0; i < laberintoMasJugado.length; i++) {
            laberintoMasJugado[i] = listaLaberintos.obtenerValor(i);
        }

        //se ordena en base a la variable vecesJugado
        int n = laberintoMasJugado.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (laberintoMasJugado[j].getVecesJugado() > laberintoMasJugado[j + 1].getVecesJugado()) {
                    //Intercambiar laberintos[j] y laberintos[j + 1]
                    Laberinto temp = laberintoMasJugado[j];
                    laberintoMasJugado[j] = laberintoMasJugado[j + 1];
                    laberintoMasJugado[j + 1] = temp;
                }
            }
        }

        String[] nombreLaberinto = laberintoMasJugado[0].getConfiguracionLaberinto().split("\n");
        String resultado = (nombreLaberinto[0] + " - Veces que ha sido jugado: " + laberintoMasJugado[0].getVecesJugado());

        //Retorar solo lo que se necesita
        return resultado;
    }

    private String mostrarLaberintoMasPerdido() throws ListaException {
        Laberinto[] laberintoMasPerdido = new Laberinto[listaLaberintos.obtenerSize()];

        for (int i = 0; i < laberintoMasPerdido.length; i++) {
            laberintoMasPerdido[i] = listaLaberintos.obtenerValor(i);
        }
        //se ordena en base a la variable vecesJugado
        int n = laberintoMasPerdido.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (laberintoMasPerdido[j].getVecesPerdido() > laberintoMasPerdido[j + 1].getVecesPerdido()) {
                    //Intercambiar laberintos[j] y laberintos[j + 1]
                    Laberinto temp = laberintoMasPerdido[j];
                    laberintoMasPerdido[j] = laberintoMasPerdido[j + 1];
                    laberintoMasPerdido[j + 1] = temp;
                }
            }
        }

        String[] nombreLaberinto = laberintoMasPerdido[0].getConfiguracionLaberinto().split("\n");
        String resultado = (nombreLaberinto[0] + " - Veces que se ha perdido: " + laberintoMasPerdido[0].getVecesPerdido());
        //Retorar solo lo que se necesita
        return resultado;
    }

    private String mostrarLaberintoMasGanado() throws ListaException {
        Laberinto[] laberintoMasGanado = new Laberinto[listaLaberintos.obtenerSize()];

        for (int i = 0; i < laberintoMasGanado.length; i++) {
            laberintoMasGanado[i] = listaLaberintos.obtenerValor(i);
        }
        //se ordena en base a la variable vecesJugado
        int n = laberintoMasGanado.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (laberintoMasGanado[j].getVecesGanado() > laberintoMasGanado[j + 1].getVecesGanado()) {
                    //Intercambiar laberintos[j] y laberintos[j + 1]
                    Laberinto temp = laberintoMasGanado[j];
                    laberintoMasGanado[j] = laberintoMasGanado[j + 1];
                    laberintoMasGanado[j + 1] = temp;
                }
            }
        }

        String[] nombreLaberinto = laberintoMasGanado[0].getConfiguracionLaberinto().split("\n");
        String resultado = (nombreLaberinto[0] + " - Veces que se ha ganado: " + laberintoMasGanado[0].getVecesGanado());
        //Retorar solo lo que se necesita
        return resultado;
    }

    private int cantidadDeOroTotalMinujuego() throws ListaException {
        int sumaOro = 0;

        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaOro += listaPartidas.obtenerValor(i).getCantidadOroWuacamole();
        }
        return sumaOro;
    }

    private int cantidadMinujuegoIniciados() throws ListaException {
        int sumaMinujuego = 0;

        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaMinujuego += listaPartidas.obtenerValor(i).getWuacamoleJugados();
        }
        return sumaMinujuego;
    }

    //Desde la casilla salida
    private int cantidadDePartidasGanadas() throws ListaException {
        int sumaPartidas = 0;

        for (int i = 0; i < listaLaberintos.obtenerSize(); i++) {
            sumaPartidas += listaLaberintos.obtenerValor(i).getVecesGanado();
        }
        return sumaPartidas;
    }

    private int cantidadTotalPartidasGanadas() throws ListaException {
        int sumaPartida = 0;

        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaPartida += listaPartidas.obtenerValor(i).getContadorVictoria();
        }

        return sumaPartida;
    }

    private int cantidadTotalVecesAtrapado() throws ListaException {
        int sumaAtrapado = 0;
        
        for (int i = 0; i < listaPartidas.obtenerSize(); i++) {
            sumaAtrapado += listaPartidas.obtenerValor(i).getContadorAtrapado();
        }

        return sumaAtrapado;
    }
}
