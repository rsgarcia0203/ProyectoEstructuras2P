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

import java.util.Comparator;

public abstract class Partida {

    public static Jugador jugadorUno;
    public static Jugador jugadorDos;
    public static Tablero tablero;
    public static boolean startFirst;

    private static final Comparator<Tablero> cmp = (t1, t2) -> {

        if (t1.getCasillasVacias() > t2.getCasillasVacias()) {
            return 1;
        }
        if (t1.getCasillasVacias() < t2.getCasillasVacias()) {
            return -1;
        }

        for (int i = 0; i < t1.getTable().length; i++) {

            for (int j = 0; j < t1.getTable().length; j++) {

                if (t1.getTable()[i][j] != t2.getTable()[i][j]) {
                    return -2;
                }
            }

        }
        return 0;

    };

    public static void nuevaPartidaUnJugador(boolean startFirst, Jugador jugadorUno, Jugador jugadorDos) {

        if (startFirst) {
            jugadorUno = new Jugador(Type.PLAYER1);
            jugadorDos = new Jugador(Type.CPU);
        } else {
            jugadorUno = new Jugador(Type.CPU);
            jugadorDos = new Jugador(Type.PLAYER1);
        }

        tablero = new Tablero();
    }

    public static void nuevaPartidaDosJugadores(boolean startFirst) {
        
        if (startFirst) {
            jugadorUno = new Jugador(Type.PLAYER1);
            jugadorDos = new Jugador(Type.PLAYER2);
        } else {
            jugadorUno = new Jugador(Type.PLAYER2);
            jugadorDos = new Jugador(Type.PLAYER1);
        }
        
        tablero = new Tablero();
    }

}
