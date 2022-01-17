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

/**
 *
 * @author rsgar
 */
public enum Type {
    PLAYER1("ec/edu/espol/img/clear.png", "JUGADOR 1"), PLAYER2("ec/edu/espol/img/circle.png", "JUGADOR 2"),CPU1("ec/edu/espol/img/clear.png", "CPU 1"), CPU2("ec/edu/espol/img/circle.png", "CPU 2");
    
    public final String ruta;
    public final String name;

    private Type(String ruta, String name) {
        this.ruta = ruta;
        this.name = name;
    }
        
}
