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
import ec.edu.espol.model.Sonidos;
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 *
 * @author rsgar
 */
public class PantallaPrincipalController {

    @FXML
    private ImageView btn_ng;
    @FXML
    private ImageView btn_exit;
    @FXML
    private ImageView btn_timer;
    @FXML
    private Pane btnR;
    @FXML
    private Pane btnL;
    @FXML
    private ImageView gameMode;

    private GameMode gm = GameMode.PLAYERVSCPU;
    private boolean timer = false;

    @FXML
    private void mouseReleased(MouseEvent event) {
        btn_ng.setEffect(new DropShadow());
        btn_ng.setOpacity(0.80);
        btn_exit.setEffect(new DropShadow());
        btn_exit.setOpacity(0.80);

        if (timer) {
            btn_timer.setEffect(new DropShadow());
            btn_timer.setOpacity(1);
        } else {
            btn_timer.setEffect(new DropShadow());
            btn_timer.setOpacity(0.50);
        }

        btnR.setEffect(new DropShadow());
        btnR.setOpacity(0.80);
        btnL.setEffect(new DropShadow());
        btnL.setOpacity(0.80);
    }

    @FXML
    private void mouseExited(MouseEvent event) {
        btn_ng.setOpacity(0.80);
        btn_exit.setOpacity(0.80);

        if (timer) {
            btn_timer.setOpacity(1);
        } else {
            btn_timer.setOpacity(0.50);
        }

        btnR.setOpacity(0.80);
        btnL.setOpacity(0.80);
    }

    @FXML
    private void mouseEntered(MouseEvent event) {
        if (btn_ng.isHover()) {
            btn_ng.setOpacity(1);
        }

        if (btn_exit.isHover()) {
            btn_exit.setOpacity(1);
        }

        if (btn_timer.isHover()) {

            if (timer) {
                btn_timer.setOpacity(0.80);
            } else {
                btn_timer.setOpacity(0.70);
            }

        }

        if (btnR.isHover()) {
            btnR.setOpacity(1);
        }

        if (btnL.isHover()) {
            btnL.setOpacity(1);
        }

        Sonidos.hover();
    }

    @FXML
    private void mousePressed(MouseEvent event) {
        if (btn_ng.isPressed()) {
            btn_ng.setEffect(new InnerShadow());
            btn_ng.setOpacity(0.55);
        }

        if (btn_exit.isPressed()) {
            btn_exit.setEffect(new InnerShadow());
            btn_exit.setOpacity(0.55);
            Sonidos.click();
        }

        if (btn_timer.isPressed()) {

            if (timer) {
                btn_timer.setEffect(new InnerShadow());
                btn_timer.setOpacity(0.55);
                Sonidos.click();
            } else {
                btn_timer.setEffect(new InnerShadow());
                btn_timer.setOpacity(0.40);
                Sonidos.click();
            }

        }

        if (btnR.isPressed()) {
            btnR.setEffect(new InnerShadow());
            btnR.setOpacity(1);
            Sonidos.click();
            changeGM();
        }

        if (btnL.isPressed()) {
            btnL.setEffect(new InnerShadow());
            btnL.setOpacity(1);
            Sonidos.click();
            changeGM();
        }

    }

    @FXML
    private void timer(MouseEvent event) {
        timer = !timer;
    }

 
    private void changeGM() {

        if (btnR.isPressed()) {
            
            if (gm == GameMode.PLAYERVSCPU) {
                gm = GameMode.PLAYERVSPLAYER;
            } else if (gm == GameMode.PLAYERVSPLAYER) {
                gm = GameMode.CPUVSCPU;
            } else if (gm == GameMode.CPUVSCPU) {
                gm = GameMode.PLAYERVSCPU;
            }
            
        } else if(btnL.isPressed()){
            
            if (gm == GameMode.PLAYERVSCPU) {
                gm = GameMode.CPUVSCPU;
            } else if (gm == GameMode.PLAYERVSPLAYER) {
                gm = GameMode.PLAYERVSCPU;
            } else if (gm == GameMode.CPUVSCPU) {
                gm = GameMode.PLAYERVSPLAYER;
            }
            
        }
        
        gameMode.setImage(new Image(gm.ruta));
    }

    @FXML
    private void exit(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void newGame(MouseEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("VentanaEspera");
            App.setRoot(fxmlloader);
            Sonidos.goButton();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }
    }

}
