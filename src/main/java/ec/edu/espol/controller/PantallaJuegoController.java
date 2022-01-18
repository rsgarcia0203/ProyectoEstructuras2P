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
package ec.edu.espol.controller;

import TDA.Tree;
import ec.edu.espol.model.GameMode;
import ec.edu.espol.model.Jugador;
import ec.edu.espol.model.Partida;
import ec.edu.espol.model.Sonidos;
import ec.edu.espol.model.Tablero;
import ec.edu.espol.model.Type;
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class PantallaJuegoController implements Initializable {

    @FXML
    private ImageView ficha11;
    @FXML
    private ImageView ficha12;
    @FXML
    private ImageView ficha21;
    @FXML
    private ImageView ficha31;
    @FXML
    private ImageView ficha32;
    @FXML
    private ImageView ficha22;
    @FXML
    private ImageView ficha13;
    @FXML
    private ImageView ficha23;
    @FXML
    private ImageView ficha33;
    @FXML
    private ImageView diagonal1;
    @FXML
    private ImageView diagonal2;
    @FXML
    private ImageView horizontal1;
    @FXML
    private ImageView horizontal2;
    @FXML
    private ImageView horizontal3;
    @FXML
    private ImageView vertical1;
    @FXML
    private ImageView vertical2;
    @FXML
    private ImageView vertical3;
    @FXML
    private ImageView back_btn;

    private int segundos;
    private Jugador jugadorActual;
    private Timeline timer;
    private Timeline CPUtimer;
    private Tablero tablero;
    private int CPUtime;

    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Pane pane3;
    @FXML
    private Pane pane6;
    @FXML
    private Pane pane5;
    @FXML
    private Pane pane4;
    @FXML
    private Pane pane7;
    @FXML
    private Pane pane8;
    @FXML
    private Pane pane9;
    @FXML
    private HBox paneP1;
    @FXML
    private Text P1name;
    @FXML
    private VBox timerPane;
    @FXML
    private Label time;
    @FXML
    private HBox paneP2;
    @FXML
    private Text P2name;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        this.jugadorActual = Partida.jugadorUno;
        this.tablero = Partida.tablero;
        this.CPUtime = 3;

        if (Partida.gameMode == GameMode.PLAYERVSCPU) {
            P1name.setText("JUGADOR");
            P2name.setText("CPU");
        }

        if (Partida.gameMode == GameMode.PLAYERVSPLAYER) {
            P1name.setText("JUGADOR 1");
            P2name.setText("JUGADOR 2");
        }

        if (Partida.gameMode == GameMode.CPUVSCPU) {
            P1name.setText("CPU 1");
            P2name.setText("CPU 2");
        }

        if (Partida.xtreme == true) {
            segundos = 10;
            time.setText(String.format("0%d:%02d", 0, segundos));
            timerPane.setDisable(false);
            timerPane.setVisible(true);

            timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarTimers()));
            timer.setCycleCount(Timeline.INDEFINITE);
            timer.play();

        } else {
            timerPane.setDisable(true);
            timerPane.setVisible(false);
        }

        if (jugadorActual.getType() == Type.PLAYER1 || jugadorActual.getType() == Type.CPU1) {
            paneP1.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, #6bff5d, white);");
            paneP2.setStyle("");

            paneP2.setOpacity(0.5);
            paneP1.setOpacity(1);
        } else {
            paneP2.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, white, #FF6D6D);");
            paneP1.setStyle("");

            paneP1.setOpacity(0.5);
            paneP2.setOpacity(1);
        }

        if (Partida.gameMode != GameMode.CPUVSCPU) {
            if (verifyCPU() == true) {
                jugadaCPU();
            }
        } else {
            jugadaCPU(3);
            pane1.setDisable(true);
            pane2.setDisable(true);
            pane3.setDisable(true);
            pane4.setDisable(true);
            pane5.setDisable(true);
            pane6.setDisable(true);
            pane7.setDisable(true);
            pane8.setDisable(true);
            pane9.setDisable(true);
        }

    }

    private void cambiarTurno() {

        //Solo realiza cambio de turno cuando se ha realizado la jugada
        if (jugadorActual == Partida.jugadorUno) {
            jugadorActual = Partida.jugadorDos;
        } else if (jugadorActual == Partida.jugadorDos) {
            jugadorActual = Partida.jugadorUno;
        }

        segundos = 11;

        if (jugadorActual.getType() == Type.PLAYER1 || jugadorActual.getType() == Type.CPU1) {
            paneP1.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, #6bff5d, white);");
            paneP2.setStyle("");

            paneP2.setOpacity(0.5);
            paneP1.setOpacity(1);
        } else {
            paneP2.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, white, #FF6D6D);");
            paneP1.setStyle("");

            paneP1.setOpacity(0.5);
            paneP2.setOpacity(1);
        }

        if (Partida.gameMode != GameMode.CPUVSCPU) {
            if (verifyCPU() == true) {
                jugadaCPU();
            }
        } else {
            endGameCPU();
            jugadaCPU(3);
        }

    }

    private void jugadaCPU() {
        try {
            Tablero bestPlay = Partida.mejorJugada(tablero);
            int[] pos = bestPlay.getUltimaPosicion();
            visualiceToken(pos[0], pos[1], jugadorActual);
            this.tablero.actualizarTablero(pos[0], pos[1]);
            endGame();
            cambiarTurno();

        } catch (NullPointerException ex) {

        }
    }

    private void jugadaCPU(int time) {
        CPUtime = time;
        CPUtimer = new Timeline(new KeyFrame(Duration.seconds(2), e -> actualizarTimerCPU()));
        CPUtimer.setCycleCount(Timeline.INDEFINITE);
        CPUtimer.play();
    }

    private void actualizarTimerCPU() {
        endGameCPU();

        if (CPUtime > 0) {
            CPUtime--;
        } else {
            CPUtimer.stop();
            try {
                Tablero bestPlay = Partida.mejorJugada(tablero);
                int[] pos = bestPlay.getUltimaPosicion();
                visualiceToken(pos[0], pos[1], jugadorActual);
                this.tablero.actualizarTablero(pos[0], pos[1]);
                cambiarTurno();
            } catch (NullPointerException ex) {
                CPUtimer.stop();
            }
        }

    }
    
    private void visualiceDiagonal(Tablero tablero){
        
        if (tablero.coincidence().equals("fila0")){
            horizontal1.setVisible(true);
            coincidenceAnimation(horizontal1);
        } else if (tablero.coincidence().equals("fila1")){
            horizontal2.setVisible(true);
            coincidenceAnimation(horizontal2);
        } else if (tablero.coincidence().equals("fila2")){
            horizontal3.setVisible(true);
            coincidenceAnimation(horizontal3);
        } else if (tablero.coincidence().equals("columna0")){
            vertical1.setVisible(true);
            coincidenceAnimation(vertical1);
        } else if (tablero.coincidence().equals("columna1")){
            vertical2.setVisible(true);
            coincidenceAnimation(vertical2);
        } else if (tablero.coincidence().equals("columna2")){
            vertical3.setVisible(true);
            coincidenceAnimation(vertical3);
        } else if (tablero.coincidence().equals("diagonal1")){
            diagonal2.setVisible(true);
            coincidenceAnimation(diagonal1);
        } else if (tablero.coincidence().equals("diagonal2")){
            diagonal1.setVisible(true);
            coincidenceAnimation(diagonal2);
        }
    }
    
    private boolean verifyCPU() {

        if (Partida.gameMode == GameMode.PLAYERVSCPU) {

            if (jugadorActual.getType() == Type.CPU1 || jugadorActual.getType() == Type.CPU2) {
                return true;
            }

        } else if (Partida.gameMode == GameMode.CPUVSCPU) {
            return true;
        }

        return false;
    }

    private void actualizarTimers() {

        if (segundos <= 11 && segundos > 0) {
            segundos--;
        }

        time.setText(String.format("0%d:%02d", 0, segundos));

        if (segundos == 0) {
            this.automaticPlay(jugadorActual);
            Sonidos.click();
            endGame();
            cambiarTurno();
        }

    }

    public Jugador getJugadorActual() {
        return this.jugadorActual;
    }

    private void endGame() {

        if (!tablero.coincidence().equals("")) {
            if (Partida.xtreme == true) {
                timer.stop();
            }
            visualiceDiagonal(tablero);
            Sonidos.win();
            finalWindow();
        } else if (tablero.isFull() && tablero.coincidence().equals("")) {
            if (Partida.xtreme == true) {
                timer.stop();
            }
            Sonidos.lose();
            finalWindow();
        }
    }
        
    private void finalWindow(){
        Partida.tablero = this.tablero;
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> actualizarPantalla()));
        tl.setCycleCount(1);
        tl.setAutoReverse(false);
        tl.play();
    }
    
    private void actualizarPantalla(){
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("VentanaFinal");
            App.setRoot(fxmlloader);
            Sonidos.back();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
            ex.printStackTrace();
        }
    }
    
    private void endGameCPU() {
        if (!tablero.coincidence().equals("")) {
            CPUtimer.stop();
            Sonidos.win();
            toMain();
        } else if (tablero.isFull()) {
            CPUtimer.stop();
            Sonidos.lose();
            toMain();
        }

    }

    private void visualiceToken(int fila, int columna, Jugador player) {

        if (fila == 0) {

            if (columna == 0) {
                ficha11.setImage(new Image(player.getToken()));
                clickAnimation(ficha11);
                pane1.setDisable(true);
            } else if (columna == 1) {
                ficha12.setImage(new Image(player.getToken()));
                clickAnimation(ficha12);
                pane2.setDisable(true);
            } else if (columna == 2) {
                ficha13.setImage(new Image(player.getToken()));
                clickAnimation(ficha13);
                pane3.setDisable(true);
            }

        } else if (fila == 1) {

            if (columna == 0) {
                ficha21.setImage(new Image(player.getToken()));
                clickAnimation(ficha21);
                pane4.setDisable(true);
            } else if (columna == 1) {
                ficha22.setImage(new Image(player.getToken()));
                clickAnimation(ficha22);
                pane5.setDisable(true);
            } else if (columna == 2) {
                ficha23.setImage(new Image(player.getToken()));
                clickAnimation(ficha23);
                pane6.setDisable(true);
            }

        } else if (fila == 2) {

            if (columna == 0) {
                ficha31.setImage(new Image(player.getToken()));
                clickAnimation(ficha31);
                pane7.setDisable(true);
            } else if (columna == 1) {
                ficha32.setImage(new Image(player.getToken()));
                clickAnimation(ficha32);
                pane8.setDisable(true);
            } else if (columna == 2) {
                ficha33.setImage(new Image(player.getToken()));
                clickAnimation(ficha33);
                pane9.setDisable(true);
            }

        }

    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        back_btn.setEffect(new DropShadow());
        back_btn.setOpacity(1);
    }

    @FXML
    private void mouseExited(MouseEvent event) {
        back_btn.setOpacity(1);
    }

    @FXML
    private void mouseEntered(MouseEvent event) {
        back_btn.setOpacity(0.7);
    }

    private void setImagePlayer(Jugador jugador) {
        if (pane1.isHover()) {
            ficha11.setImage(new Image(jugador.getToken()));
        } else if (pane2.isHover()) {
            ficha12.setImage(new Image(jugador.getToken()));
        } else if (pane3.isHover()) {
            ficha13.setImage(new Image(jugador.getToken()));
        } else if (pane4.isHover()) {
            ficha21.setImage(new Image(jugador.getToken()));
        } else if (pane5.isHover()) {
            ficha22.setImage(new Image(jugador.getToken()));
        } else if (pane6.isHover()) {
            ficha23.setImage(new Image(jugador.getToken()));
        } else if (pane7.isHover()) {
            ficha31.setImage(new Image(jugador.getToken()));
        } else if (pane8.isHover()) {
            ficha32.setImage(new Image(jugador.getToken()));
        } else if (pane9.isHover()) {
            ficha33.setImage(new Image(jugador.getToken()));
        }
    }
    
    private void toMain() {
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("pantallaprincipal");
            App.setRoot(fxmlloader);
            Sonidos.back();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }
    }
    
    @FXML
    private void back(MouseEvent event) {
        toMain();
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        back_btn.setEffect(new InnerShadow());
        back_btn.setOpacity(0.55);
    }

    @FXML
    private void mouseClicked(MouseEvent event) {
        ficha11.setOpacity(1);
        ficha12.setOpacity(1);
        ficha13.setOpacity(1);
        ficha21.setOpacity(1);
        ficha22.setOpacity(1);
        ficha23.setOpacity(1);
        ficha31.setOpacity(1);
        ficha32.setOpacity(1);
        ficha33.setOpacity(1);
    }

    @FXML
    private void mouseNotHover(MouseEvent event) {
        if (!pane1.isDisabled()) {
            ficha11.setImage(null);
        }

        if (!pane2.isDisabled()) {
            ficha12.setImage(null);
        }

        if (!pane3.isDisabled()) {
            ficha13.setImage(null);
        }

        if (!pane4.isDisabled()) {
            ficha21.setImage(null);
        }

        if (!pane5.isDisabled()) {
            ficha22.setImage(null);
        }

        if (!pane6.isDisabled()) {
            ficha23.setImage(null);
        }

        if (!pane7.isDisabled()) {
            ficha31.setImage(null);
        }

        if (!pane8.isDisabled()) {
            ficha32.setImage(null);
        }

        if (!pane9.isDisabled()) {
            ficha33.setImage(null);
        }

    }

    @FXML
    private void mouseHover(MouseEvent event) {

        if (this.jugadorActual.getType() != Type.PLAYER1 || this.jugadorActual.getType() != Type.PLAYER2) {
            this.setImagePlayer(jugadorActual);
            Sonidos.hover();
        }

    }

    @FXML
    private void mouseClick(MouseEvent event) {

        if (pane1.isPressed() && !pane1.isDisabled()) {
            ficha11.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha11);
            ficha11.setOpacity(0.5);
            pane1.setDisable(true);
            this.tablero.actualizarTablero(0, 0);
            endGame();
            cambiarTurno();
        } else if (pane2.isPressed() && !pane2.isDisabled()) {
            ficha12.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha12);
            ficha12.setOpacity(0.5);
            pane2.setDisable(true);
            this.tablero.actualizarTablero(0, 1);
            endGame();
            cambiarTurno();
        } else if (pane3.isPressed() && !pane3.isDisabled()) {
            ficha13.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha13);
            ficha13.setOpacity(0.5);
            pane3.setDisable(true);
            this.tablero.actualizarTablero(0, 2);
            endGame();
            cambiarTurno();
        } else if (pane4.isPressed() && !pane4.isDisabled()) {
            ficha21.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha21);
            ficha21.setOpacity(0.5);
            pane4.setDisable(true);
            this.tablero.actualizarTablero(1, 0);
            endGame();
            cambiarTurno();
        } else if (pane5.isPressed() && !pane5.isDisabled()) {
            ficha22.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha22);
            ficha22.setOpacity(0.5);
            pane5.setDisable(true);
            this.tablero.actualizarTablero(1, 1);
            endGame();
            cambiarTurno();
        } else if (pane6.isPressed() && !pane6.isDisabled()) {
            ficha23.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha23);
            ficha23.setOpacity(0.5);
            pane6.setDisable(true);
            this.tablero.actualizarTablero(1, 2);
            endGame();
            cambiarTurno();
        } else if (pane7.isPressed() && !pane7.isDisabled()) {
            ficha31.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha31);
            ficha31.setOpacity(0.5);
            pane7.setDisable(true);
            this.tablero.actualizarTablero(2, 0);
            endGame();
            cambiarTurno();
        } else if (pane8.isPressed() && !pane8.isDisabled()) {
            ficha32.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha32);
            ficha32.setOpacity(0.5);
            pane8.setDisable(true);
            this.tablero.actualizarTablero(2, 1);
            endGame();
            cambiarTurno();
        } else if (pane9.isPressed() && !pane9.isDisabled()) {
            ficha33.setImage(new Image(this.jugadorActual.getToken()));
            clickAnimation(ficha33);
            ficha33.setOpacity(0.5);
            pane9.setDisable(true);
            this.tablero.actualizarTablero(2, 2);
            endGame();
            cambiarTurno();
        }

        Sonidos.click();
    }

    private void clickAnimation(ImageView imv) {
        FadeTransition ft = new FadeTransition(Duration.millis(500), imv);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    private void coincidenceAnimation(ImageView imv) {
        FadeTransition ft = new FadeTransition(Duration.millis(900), imv);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void automaticPlay(Jugador jugadorActual) {
        Tablero jugadaAutomatica = Partida.jugadaAutomatica(tablero);
        int[] pos = jugadaAutomatica.getUltimaPosicion();
        visualiceToken(pos[0], pos[1], jugadorActual);
        this.tablero.actualizarTablero(pos[0], pos[1]);
    }
}
