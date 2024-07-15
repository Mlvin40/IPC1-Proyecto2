/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.io.Serializable;

/**
 *
 * @author Phoenix
 */
public class Laberinto implements Serializable {

    private int vecesJugado;
    private int vecesGanado;
    private int vecesPerdido;

    private String configuracionLaberinto;

    //Este constructor lo usamos cuando creamos un nuevo laberinto valido
    public Laberinto(String configuracionLaberinto) {
        this.configuracionLaberinto = configuracionLaberinto;

        this.vecesJugado = 0;
        this.vecesGanado = 0;
        this.vecesPerdido = 0;
    }

    public void aumentarVecesJugado() {
        vecesJugado += 1;
    }

    public void aumentarVecesPerdido() {
        vecesPerdido += 1;
    }

    public void aumentarVecesGanado() {
        vecesGanado += 1;
    }

    public String getConfiguracionLaberinto() {
        return configuracionLaberinto;
    }

    public void setConfiguracionLaberinto(String configuracionLaberinto) {
        this.configuracionLaberinto = configuracionLaberinto;
    }

    public int getVecesJugado() {
        return vecesJugado;
    }

    public int getVecesGanado() {
        return vecesGanado;
    }

    public int getVecesPerdido() {
        return vecesPerdido;
    }

    public void setVecesJugado(int vecesJugado) {
        this.vecesJugado = vecesJugado;
    }

    
    
    
}
