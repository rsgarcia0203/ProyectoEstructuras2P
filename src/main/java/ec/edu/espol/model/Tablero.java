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

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author rsgar
 */
public class Tablero {

    private final char J1 = 'X';
    private final char J2 = 'O';
    private boolean turno; //turno = true Turno del jugador1: X.   turno= false Turno del jugador2: O
    private char table[][];
    private int casillasVacias;
    private int casillasLlenas;
    private int utilidad;
    private int[] ultimaPosicion;
    public static String jugador1 = "";
    public static String jugador2 = "";
    public static String resultado = "EMPATE";

    public Tablero() {
        this.table = new char[3][3];
        initTablero();
        this.casillasLlenas = 0;
        this.casillasVacias = 0;
        this.turno = true;
        this.ultimaPosicion = new int[2];
    }

    public Tablero(boolean turno) {
        this.turno = turno;
        this.table = new char[3][3];
        initTablero();
        this.casillasLlenas = 0;
        this.casillasVacias = 0;
        this.turno = true;
    }

    public Tablero(Tablero another) {
        this.table = new char[3][3];
        copyTablero(another);
        this.casillasLlenas = another.casillasLlenas;
        this.casillasVacias = another.casillasVacias;
        this.turno = another.isTurno();
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

    private void copyTablero(Tablero another) {
        for (int i = 0; i < another.table.length; i++) {
            for (int j = 0; j < another.table.length; j++) {
                this.table[i][j] = another.table[i][j];
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

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public int[] getUltimaPosicion() {
        return ultimaPosicion;
    }

    public void setUltimaPosicion(int[] ultimaPosicion) {
        this.ultimaPosicion = ultimaPosicion;
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

    //retorna un string en donde hay coincidencia ej: fila1, diagonal1
    public String coincidence() {
        resultado = "EMPATE";
        String coincidencia = "";

        for (int i = 0; i < 3; i++) {
            boolean estaUsado = (this.table[i][0] != '-') && (this.table[i][1] != '-') && (this.table[i][2] != '-');
            if ((this.table[i][0] == this.table[i][1]) && (this.table[i][1] == this.table[i][2]) && estaUsado) {
                if (this.table[i][0] == 'X') {
                    if (jugador1.equals("JUGADOR")) {
                        resultado = "¡GANASTE!";
                    } else if (jugador1.equals("CPU")) {
                        resultado = "PERDISTE";
                    } else if (jugador1.equals("JUGADOR 1: X")) {
                        resultado = "GANO JUGADOR 1: X";
                    } else if (jugador1.equals("JUGADOR 2: X")) {
                        resultado = "GANO JUGADOR 2: X";
                    }
                } else if (this.table[i][0] == 'O') {
                    if (jugador2.equals("JUGADOR")) {
                        resultado = "¡GANASTE!";
                    } else if (jugador2.equals("CPU")) {
                        resultado = "PERDISTE";
                    } else if (jugador2.equals("JUGADOR 1: O")) {
                        resultado = "GANO JUGADOR 1: O";
                    } else if (jugador2.equals("JUGADOR 2: O")) {
                        resultado = "GANO JUGADOR 2: O";
                    }

                }
                coincidencia = "fila" + String.valueOf(i);
            }
        }

        for (int j = 0; j < 3; j++) {
            boolean estaUsado = (this.table[0][j] != '-') && (this.table[1][j] != '-') && (this.table[2][j] != '-');
            if ((this.table[0][j] == this.table[1][j]) && (this.table[1][j] == this.table[2][j]) && estaUsado) {
                if (this.table[0][j] == 'X') {
                    if (jugador1.equals("JUGADOR")) {
                        resultado = "¡GANASTE!";
                    } else if (jugador1.equals("CPU")) {
                        resultado = "PERDISTE";
                    } else if (jugador1.equals("JUGADOR 1: X")) {
                        resultado = "GANO JUGADOR 1: X";
                    } else if (jugador1.equals("JUGADOR 2: X")) {
                        resultado = "GANO JUGADOR 2: X";
                    }
                } else if (this.table[0][j] == 'O') {
                    if (jugador2.equals("JUGADOR")) {
                        resultado = "¡GANASTE!";
                    } else if (jugador2.equals("CPU")) {
                        resultado = "PERDISTE";
                    } else if (jugador2.equals("JUGADOR 1: O")) {
                        resultado = "GANO JUGADOR 1: O";
                    } else if (jugador2.equals("JUGADOR 2: O")) {
                        resultado = "GANO JUGADOR 2: O";
                    }
                }
                coincidencia = "columna" + String.valueOf(j);
            }
        }

        boolean estaUsado = (this.table[0][0] != '-') && (this.table[1][1] != '-') && (this.table[2][2] != '-');
        if ((this.table[0][0] == this.table[1][1]) && (this.table[1][1] == this.table[2][2]) && estaUsado) {
            if (this.table[0][0] == 'X') {
                if (jugador1.equals("JUGADOR")) {
                    resultado = "¡GANASTE!";
                } else if (jugador1.equals("CPU")) {
                    resultado = "PERDISTE";
                } else if (jugador1.equals("JUGADOR 1: X")) {
                    resultado = "GANO JUGADOR 1: X";
                } else if (jugador1.equals("JUGADOR 2: X")) {
                    resultado = "GANO JUGADOR 2: X";
                }
            } else if (this.table[0][0] == 'O') {
                if (jugador2.equals("JUGADOR")) {
                    resultado = "¡GANASTE!";
                } else if (jugador2.equals("CPU")) {
                    resultado = "PERDISTE";
                } else if (jugador2.equals("JUGADOR 1: O")) {
                    resultado = "GANO JUGADOR 1: O";
                } else if (jugador2.equals("JUGADOR 2: O")) {
                    resultado = "GANO JUGADOR 2: O";
                }
            }
            coincidencia = "diagonal1"; //izquierda a derecha
        }

        boolean estaUsado2 = (this.table[0][2] != '-') && (this.table[1][1] != '-') && (this.table[2][0] != '-');
        if ((this.table[0][2] == this.table[1][1]) && (this.table[1][1] == this.table[2][0]) && estaUsado2) {
            if (this.table[0][2] == 'X') {
                if (jugador1.equals("JUGADOR")) {
                    resultado = "¡GANASTE!";
                } else if (jugador1.equals("CPU")) {
                    resultado = "PERDISTE";
                } else if (jugador1.equals("JUGADOR 1: X")) {
                    resultado = "GANO JUGADOR 1: X";
                } else if (jugador1.equals("JUGADOR 2: X")) {
                    resultado = "GANO JUGADOR 2: X";
                }
            } else if (this.table[0][2] == 'O') {
                if (jugador2.equals("JUGADOR")) {
                    resultado = "¡GANASTE!";
                } else if (jugador2.equals("CPU")) {
                    resultado = "PERDISTE";
                } else if (jugador2.equals("JUGADOR 1: O")) {
                    resultado = "GANO JUGADOR 1: O";
                } else if (jugador2.equals("JUGADOR 2: O")) {
                    resultado = "GANO JUGADOR 2: O";
                }
            }
            coincidencia = "diagonal2"; //izquierda a derecha
        }
        return coincidencia;

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
        ArrayList<Integer> filas = new ArrayList<>();
        filas.add(0);
        filas.add(1);
        filas.add(2);
        ArrayList<Integer> columnas = new ArrayList<>();
        columnas.add(0);
        columnas.add(1);
        columnas.add(2);
        ArrayList<Integer> diagonal1 = new ArrayList<>();
        ArrayList<Integer> diagonal2 = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (this.table[i][j] != c && this.table[i][j] != '-') {
                    if (filas.contains(i)) {
                        filas.remove(filas.indexOf(i));
                    }
                    if (columnas.contains(j)) {
                        columnas.remove(columnas.indexOf(j));
                    }
                } else {
                    if (i == j) {
                        diagonal1.add(i);
                    }
                    if (i + j == 2) {
                        diagonal2.add(i);
                    }
                }
            }
        }
        int resultado = filas.size() + columnas.size();
        if (diagonal1.size() == 3) {
            resultado++;
        }
        if (diagonal2.size() == 3) {
            resultado++;
        }
        return resultado;

    }

    public int getUtility() {
        //Cuando hay coincidencia no se haya utilidad
        if (coincidence().length() != 0) {
            return -100;
        }
        if (!turno) {
            return getP('X') - getP('O');
        } else {
            return getP('O') - getP('X');
        }

    }

    public void actualizarTablero(int fila, int columna) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == fila && j == columna) {
                    if (turno) {
                        this.table[i][j] = 'X';
                    } else {
                        this.table[i][j] = 'O';
                    }
                }
            }
        }
        this.turno = !this.turno;
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

        for (int i = 0; i < this.table.length; i++) {
            for (int j = 0; j < this.table.length; j++) {
                if (this.table[i][j] != other.getTable()[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

}
