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
import javafx.scene.layout.GridPane;
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
    private int turno;
    private Jugador jugadorActual;
    private Timeline timer;
    private Tablero tablero;

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
    private Tree<Tablero> arbol;
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

    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
        this.turno = 1;
        this.arbol = Partida.generarArbol(tablero);
    }

    private void cambiarTurno() {

        //Solo realiza cambio de turno cuando se ha realizado la jugada
        if (jugadorActual == Partida.jugadorUno) {
            jugadorActual = Partida.jugadorDos;
        } else if (jugadorActual == Partida.jugadorDos) {
            jugadorActual = Partida.jugadorUno;
        }

        segundos = 10;

        if (jugadorActual == Partida.jugadorUno) {
            paneP1.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, #65C0FF, white);");
            paneP2.setStyle("");

            paneP2.setOpacity(0.5);
            paneP1.setOpacity(1);
        } else {
            paneP2.setStyle("-fx-background-color: linear-gradient(from 0% 50% to 100% 50%, white, #FF6D6D);");
            paneP1.setStyle("");

            paneP1.setOpacity(0.5);
            paneP2.setOpacity(1);
        }

    }

    private void actualizarTimers() {

        if (segundos <= 10 && segundos > 0) {
            segundos--;
        }

        time.setText(String.format("0%d:%02d", 0, segundos));

        if (segundos == 0) {
            this.automaticPlay(jugadorActual);
            cambiarTurno();
        }

    }

    private int getTurno() {

        if (jugadorActual == Partida.jugadorUno) {
            return 1;
        } else {
            return 2;
        }

    }

    public void visualiceTable(Tablero tablero) {
        int[] pos = tablero.getUltimaPosicion();

        if (turno == 2) {
            visualiceToken(pos[0], pos[1], jugadorActual);
        }
    }

    private void visualiceToken(int fila, int columna, Jugador player) {

        if (fila == 0) {

            if (columna == 0) {
                ficha11.setImage(new Image(player.getToken()));
                pane1.setDisable(true);
            } else if (columna == 1) {
                ficha12.setImage(new Image(player.getToken()));
                pane2.setDisable(true);
            } else if (columna == 2) {
                ficha13.setImage(new Image(player.getToken()));
                pane3.setDisable(true);
            }

        } else if (fila == 1) {

            if (columna == 0) {
                ficha21.setImage(new Image(player.getToken()));
                pane4.setDisable(true);
            } else if (columna == 1) {
                ficha22.setImage(new Image(player.getToken()));
                pane5.setDisable(true);
            } else if (columna == 2) {
                ficha23.setImage(new Image(player.getToken()));
                pane6.setDisable(true);
            }

        } else if (fila == 2) {

            if (columna == 0) {
                ficha31.setImage(new Image(player.getToken()));
                pane7.setDisable(true);
            } else if (columna == 1) {
                ficha32.setImage(new Image(player.getToken()));
                pane8.setDisable(true);
            } else if (columna == 2) {
                ficha33.setImage(new Image(player.getToken()));
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

    @FXML
    private void back(MouseEvent event) {

        try {
            Sonidos.back();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION, "¿Está seguro de regresar a la pantalla principal?, se perderán todos los datos de la partida actual.");
            a.setTitle("TIC-TAC-TOE");
            a.setHeaderText("Confirmación de salida");
            Optional<ButtonType> resultado = a.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                FXMLLoader fxmlloader = App.loadFXMLoader("pantallaprincipal");
                App.setRoot(fxmlloader);
            }

        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }

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
        ficha11.setImage(null);
        ficha12.setImage(null);
        ficha13.setImage(null);
        ficha21.setImage(null);
        ficha22.setImage(null);
        ficha23.setImage(null);
        ficha31.setImage(null);
        ficha32.setImage(null);
        ficha33.setImage(null);
    }

    @FXML
    private void mouseHover(MouseEvent event) {
        this.setImagePlayer(jugadorActual);
        Sonidos.hover();
    }

    @FXML
    private void mouseClick(MouseEvent event) {

        if (pane1.isPressed()) {
            ficha11.setImage(new Image(this.jugadorActual.getToken()));
            ficha11.setOpacity(0.5);
        } else if (pane2.isPressed()) {
            ficha12.setImage(new Image(this.jugadorActual.getToken()));
            ficha12.setOpacity(0.5);
        } else if (pane3.isPressed()) {
            ficha13.setImage(new Image(this.jugadorActual.getToken()));
            ficha13.setOpacity(0.5);
        } else if (pane4.isPressed()) {
            ficha21.setImage(new Image(this.jugadorActual.getToken()));
            ficha21.setOpacity(0.5);
        } else if (pane5.isPressed()) {
            ficha22.setImage(new Image(this.jugadorActual.getToken()));
            ficha22.setOpacity(0.5);
        } else if (pane6.isPressed()) {
            ficha23.setImage(new Image(this.jugadorActual.getToken()));
            ficha23.setOpacity(0.5);
        } else if (pane7.isPressed()) {
            ficha31.setImage(new Image(this.jugadorActual.getToken()));
            ficha31.setOpacity(0.5);
        } else if (pane8.isPressed()) {
            ficha32.setImage(new Image(this.jugadorActual.getToken()));
            ficha32.setOpacity(0.5);
        } else if (pane9.isPressed()) {
            ficha33.setImage(new Image(this.jugadorActual.getToken()));
            ficha33.setOpacity(0.5);
        }

        Sonidos.click();
    }

    private void clickAnimation(ImageView imv) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), imv);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }

    @FXML
    private void selectPane(MouseEvent event) {

    }

    private void automaticPlay(Jugador jugadorActual) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
