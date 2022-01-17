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
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author rsgar
 */
public class VentanaEsperaController implements Initializable {

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
    private Button btnP1;
    @FXML
    private Button btnP2;
    @FXML

    private ImageView btn_back;
    private boolean timer = false;
    private GameMode gm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setTimer(boolean timer) {
        this.timer = timer;
    }

    public void setGameMode(GameMode gm) {
        this.gm = gm;

        if (gm == GameMode.PLAYERVSCPU) {
            btnP1.setText("YO");
            btnP2.setText("CPU");
        }

        if (gm == GameMode.PLAYERVSPLAYER) {
            btnP1.setPrefSize(160, 50);
            btnP2.setPrefSize(160, 50);
            btnP1.setText("JUGADOR 1");
            btnP2.setText("JUGADOR 2");
        }

    }

    @FXML
    private void mouseReleased(MouseEvent event) {
        btnP1.setEffect(new DropShadow());
        btnP2.setEffect(new DropShadow());
        btn_back.setEffect(new DropShadow());
    }

    @FXML
    private void mouseExited(MouseEvent event) {
        this.clearImages();
        btn_back.setOpacity(0.80);
    }

    @FXML
    private void mouseEntered(MouseEvent event) {
        if (btnP1.isHover()) {
            this.setImages("ec/edu/espol/img/clear.png");
        }

        if (btnP2.isHover()) {
            this.setImages("ec/edu/espol/img/circle.png");
        }

        if (btn_back.isHover()) {
            btn_back.setOpacity(1);
        }

        Sonidos.hover();
    }

    @FXML
    private void back(MouseEvent event) {

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
    private void mousePressed(MouseEvent event) {
        if (btnP1.isPressed()) {
            btnP1.setEffect(new InnerShadow());
        }

        if (btnP2.isPressed()) {
            btnP2.setEffect(new InnerShadow());
        }

        if (btn_back.isPressed()) {
            btn_back.setOpacity(0.55);
            btn_back.setEffect(new InnerShadow());
        }
    }

    @FXML
    private void start1(MouseEvent event) {

        if (gm == GameMode.PLAYERVSPLAYER) {
            Partida.nuevaPartidaDosJugadores(true, timer);
            
            try {
                FXMLLoader fxmlloader = App.loadFXMLoader("pantallajuego");
                App.setRoot(fxmlloader);             
                Sonidos.goButton();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
                a.show();
            }

        } else {
            Partida.nuevaPartidaUnJugador(true, timer);

            try {
                FXMLLoader fxmlloader = App.loadFXMLoader("pantallajuego");
                App.setRoot(fxmlloader);
                Sonidos.goButton();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
                a.show();
            }

        }
    }

    @FXML
    private void start2(MouseEvent event) {

        if (gm == GameMode.PLAYERVSPLAYER) {
            Partida.nuevaPartidaDosJugadores(false, timer);

            try {
                FXMLLoader fxmlloader = App.loadFXMLoader("pantallajuego");
                App.setRoot(fxmlloader);
                Sonidos.goButton();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
                a.show();
            }

        } else {
            Partida.nuevaPartidaUnJugador(false, timer);

            try {
                FXMLLoader fxmlloader = App.loadFXMLoader("pantallajuego");
                App.setRoot(fxmlloader);
                Sonidos.goButton();
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
                a.show();
            }

        }

    }

    private void setImages(String ruta) {
        ficha11.setImage(new Image(ruta));
        ficha12.setImage(new Image(ruta));
        ficha13.setImage(new Image(ruta));
        ficha21.setImage(new Image(ruta));
        ficha22.setImage(new Image(ruta));
        ficha23.setImage(new Image(ruta));
        ficha31.setImage(new Image(ruta));
        ficha32.setImage(new Image(ruta));
        ficha33.setImage(new Image(ruta));
    }

    private void clearImages() {
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
}
