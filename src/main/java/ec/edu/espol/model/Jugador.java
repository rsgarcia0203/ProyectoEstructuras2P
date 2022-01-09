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

    public Jugador(Type e) {
        this.casillasLlenas = 0;
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
