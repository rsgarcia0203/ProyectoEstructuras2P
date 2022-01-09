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
    private final char J1 = 'X';
    private final char J2 = 'O';
    private boolean turno;
    private char table[][];
    private int casillasVacias;
    private int casillasLlenas;

    public Tablero() {
        this.table = new char[3][3];
        initTablero();
        this.casillasLlenas = 0;
        this.casillasVacias = 0;
        this.turno = true;
    }

    public Tablero(boolean turno) {
        this.turno = turno;
        this.table = new char[3][3];
        initTablero();
        this.casillasLlenas = 0;
        this.casillasVacias = 0;
        this.turno = true;
    }

    public Tablero(int casillasVacias, int casillasLlenas) throws Exception {
        if (casillasVacias > this.casillas) {
            this.casillasVacias = casillasVacias;
        } else {
            throw new Exception("");
        }

        if (casillasLlenas > this.casillas) {
            this.casillasLlenas = casillasLlenas;
        } else {
            throw new Exception("");
        }
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

    private void initTablero() {
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                this.table[i][j] = '-';
            }
        }
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public char[][] getTable() {
        return table;
    }

    public void setTable(char[][] table) {
        this.table = table;
    }

    public void cambiarTurno() {
        this.turno = !this.turno;
    }

    public void insertInto(int row, int column) throws PositionException, RuntimeException {

        if (validatePosition(row, column)) {
            
            if (table[row][column] == '-') {

                if (turno) {
                    this.table[row][column] = J1;
                } else {
                    this.table[row][column] = J2;
                }
                
            } else {
                throw new PositionException("");
            }
            
        } else {
            throw new RuntimeException("");
        }
    }

    public boolean validatePosition(int row, int column) {
        return row >= 0 && row < table.length && column >= 0 && column < table.length;
    }
    
    public boolean isFull(){
        
        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                
                if(table[i][j] == '-'){
                    return false;
                }
                
            }
        }
        return true;        
    }
    
    public boolean columnCoincidence(){
        
        for(int j = 0; j < table.length; j++){
            
            if(table[0][j] != '-'){
                
                for(int i = 0; i < table.length; i++){
                    
                    if(table[0][j] != table[i][j]){
                        return false;
                    } 
                    
                }
                
            } else {
                return false;
            }
            
        }
        
        return true;
    }
    
    public boolean rowCoincidence(){
        
        for(int i = 0; i < table.length; i++){
            
            if(table[i][0] != '-'){
                
                for(int j = 0; j < table.length; j++){
                    
                    if(table[i][0] != table[i][j]){
                        return false;
                    } 
                    
                }
                
            } else {
                return false;
            }
            
        }
        
        return true;
    }
    
    public boolean diagonalCoincidence(){
        
        //Verificamos la diagonal principal
        if(table[0][0] != '-'){
            
            for(int i = 1; i < table.length; i++){
                
                if(table[0][0] != table[i][i]){
                    return false;
                }
            }
        }
        
        //Verificamos la otra diagonal
        if(table[0][2] != '-'){
            
            for(int i = 1, j = 1; i < table.length; i++, j++){
                
                if(table[0][0] != table[i][j]){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean end(){
        return isFull() || columnCoincidence() || rowCoincidence() || diagonalCoincidence();
    }
}
