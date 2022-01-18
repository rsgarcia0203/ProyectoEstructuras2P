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

import ec.edu.espol.model.GameMode;
import ec.edu.espol.model.Partida;
import ec.edu.espol.model.Sonidos;
import ec.edu.espol.model.Tablero;
import ec.edu.espol.model.Type;
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class VentanaFinalController implements Initializable {

    @FXML
    private ImageView ficha11;
    @FXML
    private ImageView ficha12;
    @FXML
    private ImageView ficha13;
    @FXML
    private ImageView ficha21;
    @FXML
    private ImageView ficha31;
    @FXML
    private ImageView ficha32;
    @FXML
    private ImageView ficha22;
    @FXML
    private ImageView ficha23;
    @FXML
    private ImageView ficha33;
    @FXML
    private Pane opacity_pane;
    @FXML
    private Pane cont_pane;
    @FXML
    private VBox confirmation_pane;
    @FXML
    private Button btnNewGame;
    @FXML
    private Button btnToMain;
    @FXML
    private HBox paneP1;
    @FXML
    private Text P1name;
    @FXML
    private VBox timerPane;
    @FXML
    private HBox paneP2;
    @FXML
    private Text P2name;
    @FXML
    private Text msj;
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

    private boolean timer = true;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (Partida.xtreme == false) {
            timerPane.setVisible(false);
            timer = false;
        }

        visualiceTable(Partida.tablero);

        if (Partida.gameMode == GameMode.PLAYERVSCPU) {
            P1name.setText(Tablero.jugador1);
            P2name.setText(Tablero.jugador2);
        }

        if (Partida.gameMode == GameMode.PLAYERVSPLAYER) {
            P1name.setText(Tablero.jugador1);
            P2name.setText(Tablero.jugador2);
        }

        if (Partida.gameMode == GameMode.CPUVSCPU) {

            P1name.setText(Tablero.jugador1);
            P2name.setText(Tablero.jugador2);
        }

        if (Partida.tablero.isTurno()) {
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

        if (Partida.tablero.coincidence().length() != 0) {
            if(Tablero.resultado.equals("PERDISTE")){
                Sonidos.lose();
            }
            msj.setText(Tablero.resultado);
            visualiceCoincidence(Partida.tablero);

        } else{
            Sonidos.lose();
            msj.setText(Tablero.resultado);
        }
        appearAnimation2(opacity_pane);
        appearAnimation1(cont_pane);
        returnAnimation(confirmation_pane);
    }

    private void visualiceTable(Tablero tablero) {

        for (int i = 0; i < tablero.getTable().length; i++) {
            for (int j = 0; j < tablero.getTable().length; j++) {
                if (tablero.getTable()[i][j] == 'X') {
                    visualiceToken(i, j, Type.PLAYER1);
                } else if (tablero.getTable()[i][j] == 'O') {
                    visualiceToken(i, j, Type.PLAYER2);
                }
            }
        }

    }

    private void visualiceToken(int fila, int columna, Type type) {

        if (fila == 0) {

            if (columna == 0) {
                ficha11.setImage(new Image(type.ruta));
            } else if (columna == 1) {
                ficha12.setImage(new Image(type.ruta));
            } else if (columna == 2) {
                ficha13.setImage(new Image(type.ruta));
            }

        } else if (fila == 1) {

            if (columna == 0) {
                ficha21.setImage(new Image(type.ruta));
            } else if (columna == 1) {
                ficha22.setImage(new Image(type.ruta));
            } else if (columna == 2) {
                ficha23.setImage(new Image(type.ruta));
            }

        } else if (fila == 2) {

            if (columna == 0) {
                ficha31.setImage(new Image(type.ruta));
            } else if (columna == 1) {
                ficha32.setImage(new Image(type.ruta));
            } else if (columna == 2) {
                ficha33.setImage(new Image(type.ruta));
            }

        }

    }

    private void visualiceCoincidence(Tablero tablero) {

        if (tablero.coincidence().equals("fila0")) {
            horizontal1.setVisible(true);
        } else if (tablero.coincidence().equals("fila1")) {
            horizontal2.setVisible(true);
        } else if (tablero.coincidence().equals("fila2")) {
            horizontal3.setVisible(true);
        } else if (tablero.coincidence().equals("columna0")) {
            vertical1.setVisible(true);
        } else if (tablero.coincidence().equals("columna1")) {
            vertical2.setVisible(true);
        } else if (tablero.coincidence().equals("columna2")) {
            vertical3.setVisible(true);
        } else if (tablero.coincidence().equals("diagonal1")) {
            diagonal2.setVisible(true);
        } else if (tablero.coincidence().equals("diagonal2")) {
            diagonal1.setVisible(true);
        }
    }

    @FXML
    private void toMain(MouseEvent event) {
        returnAnimation();
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("pantallaprincipal");
            App.setRoot(fxmlloader);
            Sonidos.back();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }
    }

    private void returnAnimation() {
        moveAnimation(confirmation_pane);
        disappearAnimation(cont_pane);
        disappearAnimation(opacity_pane);
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1)));
        tl.setCycleCount(1);
        tl.setAutoReverse(false);
        tl.play();
    }

    private void moveAnimation(VBox vbox) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(700), vbox);
        tt.setToY(-150f);
        tt.setCycleCount(1);
        tt.setAutoReverse(false);
        tt.play();
    }

    private void disappearAnimation(Pane pane) {
        FadeTransition ft = new FadeTransition(Duration.millis(900), pane);
        ft.setFromValue(pane.getOpacity());
        ft.setToValue(0);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void returnAnimation(VBox vbox) {
        TranslateTransition tt = new TranslateTransition(Duration.millis(700), vbox);
        tt.setToY(277);
        tt.setCycleCount(1);
        tt.setAutoReverse(false);
        tt.play();
    }

    private void appearAnimation1(Pane pane) {
        FadeTransition ft = new FadeTransition(Duration.millis(900), pane);
        ft.setFromValue(pane.getOpacity());
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    private void appearAnimation2(Pane pane) {
        FadeTransition ft = new FadeTransition(Duration.millis(900), pane);
        ft.setFromValue(pane.getOpacity());
        ft.setFromValue(0);
        ft.setToValue(0.55);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void newGame(MouseEvent event) {
        returnAnimation();
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("VentanaEspera");
            App.setRoot(fxmlloader);
            VentanaEsperaController vec = fxmlloader.getController();
            vec.setTimer(timer);
            vec.setGameMode(Partida.gameMode);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }
    }

}
