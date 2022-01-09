/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author rsgar
 */
public class Tablero {
    
    private final int casillas = 9;
    private int casillasVacias;
    private int casillasLlenas;

    public Tablero(int casillasVacias, int casillasLlenas) throws Exception{
        if(casillasVacias > this.casillas) {this.casillasVacias = casillasVacias;}
        else{throw new Exception("");}
        
        if(casillasLlenas > this.casillas) {this.casillasLlenas = casillasLlenas;}
        else{throw new Exception("");}
    }

    public int getCasillasVacias() {
        return casillasVacias;
    }

    public void setCasillasVacias(int casillasVacias) {
        this.casillasVacias = casillasVacias;
    }

    public int getCasillasLlenas() {
        return casillasLlenas;
    }

    public void setCasillasLlenas(int casillasLlenas) {
        this.casillasLlenas = casillasLlenas;
    }
    
    
    
}
