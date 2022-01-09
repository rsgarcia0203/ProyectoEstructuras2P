/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import javafx.scene.image.Image;

/**
 *
 * @author rsgar
 */
public class Jugador {
    private int casillasLlenas;
    private Type e;
    private Image token;

    public Jugador() {
        this.casillasLlenas = 0;
    }   
    
    public Jugador(int casillasLlenas) {
        this.casillasLlenas = casillasLlenas;
    }

    public Jugador(int casillasLlenas, Type e) {
        this.casillasLlenas = casillasLlenas;
        this.e = e;    
        this.token = new Image(this.e.ruta);   
    }
    
    public int getCasillasLlenas() {
        return casillasLlenas;
    }

    public void setCasillasLlenas(int casillasLlenas) {
        this.casillasLlenas = casillasLlenas;
    }

    public Type getE() {
        return e;
    }

    public void setE(Type e) {
        this.e = e;
    }

    public String getToken() {
        return this.e.ruta;
    }

    public void setToken(String ruta) {
        this.token = new Image(ruta);
    }    
    
}
