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

import TDA.NodeTree;
import TDA.Tree;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public abstract class Partida {

    public static Jugador jugadorUno;
    public static Jugador jugadorDos;
    public static Tablero tablero;
    public static boolean startFirst;
    public static boolean xtreme;

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

    public static void nuevaPartidaUnJugador(boolean startFirst, boolean xtreme_mode) {

        if (startFirst == true) {
            jugadorUno = new Jugador(Type.PLAYER1);
            jugadorDos = new Jugador(Type.CPU2);          
        } else {
            jugadorUno = new Jugador(Type.CPU1);
            jugadorDos = new Jugador(Type.PLAYER1);
        }
        
        xtreme = xtreme_mode;
        tablero = new Tablero(startFirst);
    }

    public static void nuevaPartidaDosJugadores(boolean startFirst, boolean xtreme_mode) {

        if (startFirst == true) {
            jugadorUno = new Jugador(Type.PLAYER1);
            jugadorDos = new Jugador(Type.PLAYER2);
        } else {
            jugadorUno = new Jugador(Type.PLAYER2);
            jugadorDos = new Jugador(Type.PLAYER1);
        }
        xtreme = xtreme_mode;
        tablero = new Tablero(startFirst);
    }
    
    public static void nuevaPartidaDosCPU(){
        
        Random rd = new Random();
        int turno = rd.nextInt(2);
        
        if(turno == 0){
            jugadorUno = new Jugador(Type.CPU1);
            jugadorDos = new Jugador(Type.CPU2);
        } else {
            jugadorUno = new Jugador(Type.CPU2);
            jugadorDos = new Jugador(Type.CPU1);  
        }
        
        tablero = new Tablero(startFirst);
    }
    
    //se generan los hijos de un tablero
    public static void generarHijos(Tree<Tablero> tree) {
        
        ArrayList<String> posicion = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tree.getRoot().getContent().getTable()[i][j] == '-') {
                    String pos = String.valueOf(i) + "x" + String.valueOf(j);
                    posicion.add(pos);
                }
            }
        }
        
        for (String s : posicion) {
            String[] pos = s.split("x");
            Tablero tableroCopia = new Tablero(tree.getRoot().getContent());

            if (tableroCopia.isTurno()) {
                tableroCopia.getTable()[Integer.valueOf(pos[0])][Integer.valueOf(pos[1])] = 'X';

            } else {
                tableroCopia.getTable()[Integer.valueOf(pos[0])][Integer.valueOf(pos[1])] = 'O';
            }

            int[] ultP = {Integer.valueOf(pos[0]), Integer.valueOf(pos[1])};
            tableroCopia.setUltimaPosicion(ultP);
            NodeTree<Tablero> nodoHijo = new NodeTree(tableroCopia);
            Tree<Tablero> treeHijo = new Tree(nodoHijo);
            tree.getRoot().getChildren().add(treeHijo);
        }

    }

    //se genera el arbol completo
    public static Tree<Tablero> generarArbol(Tablero tablero) {
        
        NodeTree<Tablero> nodo = new NodeTree(tablero);
        Tree<Tablero> tree = new Tree(nodo);
        generarHijos(tree);
        LinkedList<Tree<Tablero>> tableros = tree.getRoot().getChildren();
        
        for (int i = 0; i < tableros.size(); i++) {
            // cambiar el turno
            tableros.get(i).getRoot().getContent().setTurno(!tableros.get(i).getRoot().getContent().isTurno());
            generarHijos(tableros.get(i));
        }

        return tree;
    }

    //se elige el tablero de la mejor jugada usando el arbol
    public static Tablero mejorJugada(Tablero tablero) {
        
        Tree<Tablero> treeGenerado = generarArbol(tablero);
        
        for (int i = 0; i < treeGenerado.getRoot().getChildren().size(); i++) {
            Integer minUtility = 1000;
            
            for (int i2 = 0; i2 < treeGenerado.getRoot().getChildren().get(i).getRoot().getChildren().size(); i2++) {
                int utilidad = treeGenerado.getRoot().getChildren().get(i).getRoot().getChildren().get(i2).getRoot().getContent().getUtility();
                
                if (utilidad < minUtility) {
                    minUtility = utilidad;
                }
                
            }
            
            treeGenerado.getRoot().getChildren().get(i).getRoot().getContent().setUtilidad(minUtility);
        }
        
        Tablero bestPlay = null;
        
        for (int i = 0; i < treeGenerado.getRoot().getChildren().size(); i++) {
            Tablero t = treeGenerado.getRoot().getChildren().get(i).getRoot().getContent();
            
            if (i == 0) {
                bestPlay = t;
            } else {
                
                if (t.getUtilidad() > bestPlay.getUtilidad()) {
                    bestPlay = t;
                }
                
            }
            
        }
        
        return bestPlay;
    }

}
