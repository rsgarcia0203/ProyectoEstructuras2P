/*
 * Copyright 2022 rsgar.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ec.edu.espol.model;

import java.util.Arrays;

/**
 *
 * @author rsgar
 */
public class Tablero {

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
        if (casillasVacias > 9) {
            this.casillasVacias = casillasVacias;
        } else {
            throw new Exception("");
        }

        if (casillasLlenas > 9) {
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

    public boolean isFull() {

        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {

                if (table[i][j] == '-') {
                    return false;
                }

            }
        }
        return true;
    }

    public boolean columnCoincidence() {

        for (int j = 0; j < table.length; j++) {

            if (table[0][j] != '-') {

                for (int i = 0; i < table.length; i++) {

                    if (table[0][j] != table[i][j]) {
                        return false;
                    }

                }

            } else {
                return false;
            }

        }

        return true;
    }

    public boolean rowCoincidence() {

        for (int i = 0; i < table.length; i++) {

            if (table[i][0] != '-') {

                for (int j = 0; j < table.length; j++) {

                    if (table[i][0] != table[i][j]) {
                        return false;
                    }

                }

            } else {
                return false;
            }

        }

        return true;
    }

    public boolean diagonalCoincidence() {

        //Verificamos la diagonal principal
        if (table[0][0] != '-') {

            for (int i = 1; i < table.length; i++) {

                if (table[0][0] != table[i][i]) {
                    return false;
                }
            }
        }

        //Verificamos la otra diagonal
        if (table[0][2] != '-') {

            for (int i = 1, j = 1; i < table.length; i++, j++) {

                if (table[0][0] != table[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isEnd() {
        return isFull() || columnCoincidence() || rowCoincidence() || diagonalCoincidence();
    }

    private int getP(char c) {

        int p = 0;

        // verificamos las filas
        for (int i = 0; i < table.length; i++) {

            if (table[i][0] == c || table[i][0] == '-') {

                int cont = 0;

                for (int j = 0; j < table.length; j++) {

                    if (table[i][j] == c || table[i][j] == '-') {
                        cont++;
                    }

                }

                if (cont == 3) {
                    i++;
                }

            }
        }

        //verificamos las columnas
        for (int j = 0; j < table.length; j++) {

            if (table[0][j] == c || table[0][j] == '-') {

                int cont = 0;

                for (int i = 0; i < table.length; i++) {

                    if (table[i][j] == c || table[i][j] == '-') {
                        cont++;
                    }

                }

                if (cont == 3) {
                    p++;
                }
            }
        }

        //Verificamos la diagonal principal
        if (table[0][0] == '-' || table[0][0] == c) {

            int cont = 0;

            for (int i = 1; i < table.length; i++) {

                if (table[i][i] == '-' || table[i][i] == c) {
                    cont++;
                }
            }

            if (cont == 3) {
                p++;
            }
        }

        //Verificamos la otra diagonal
        if (table[0][2] != '-' || table[0][2] == c) {

            int cont = 0;

            for (int i = 1, j = 1; i < table.length; i++, j++) {

                if (table[i][j] == '-' || table[i][j] == c) {
                    cont++;
                }
            }
            
            if(cont == 3){
                p++;
            }
        }
        
        return p;

    }

    public int getUtility() {
        return getP('X') - getP('O');
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Arrays.deepHashCode(this.table);
        hash = 97 * hash + this.casillasVacias;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Tablero other = (Tablero) obj;
        
        if (this.casillasVacias != other.casillasVacias) {
            return false;
        }
        
        for(int i = 0; i < this.table.length; i++){
            for(int j = 0; j < this.table.length; j++){
                if(this.table[i][j] != other.getTable()[i][j]){
                    return false;
                }
            }
        }
        
        return true;
    }
    
}
