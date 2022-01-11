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

import ec.edu.espol.model.Sonidos;
import ec.edu.espol.model.Type;
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private Type type = Type.PLAYER1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    private void setImagePlayer(Type type, ImageView imv) {
        if (type == Type.PLAYER1) {
            //imv
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

        if (ficha11.isHover()) {
            ficha11.setImage(new Image(type.ruta));
        } else if (ficha12.isHover()) {
            ficha12.setImage(new Image(type.ruta));
        } else if (ficha13.isHover()) {
            ficha13.setImage(new Image(type.ruta));
        } else if (ficha21.isHover()) {
            ficha21.setImage(new Image(type.ruta));
        } else if (ficha22.isHover()) {
            ficha22.setImage(new Image(type.ruta));
        } else if (ficha23.isHover()) {
            ficha23.setImage(new Image(type.ruta));
        } else if (ficha31.isHover()) {
            ficha31.setImage(new Image(type.ruta));
        } else if (ficha32.isHover()) {
            ficha32.setImage(new Image(type.ruta));
        } else if (ficha33.isHover()) {
            ficha33.setImage(new Image(type.ruta));
        }

    }

    @FXML
    private void mouseClick(MouseEvent event) {

        if (ficha11.isPressed()) {
            ficha11.setImage(new Image(type.ruta));
            ficha11.setOpacity(0.5);
        } else if (ficha12.isPressed()) {
            ficha12.setImage(new Image(type.ruta));
            ficha12.setOpacity(0.5);
        } else if (ficha13.isPressed()) {
            ficha13.setImage(new Image(type.ruta));
            ficha13.setOpacity(0.5);
        } else if (ficha21.isPressed()) {
            ficha21.setImage(new Image(type.ruta));
            ficha21.setOpacity(0.5);
        } else if (ficha22.isPressed()) {
            ficha22.setImage(new Image(type.ruta));
            ficha22.setOpacity(0.5);
        } else if (ficha23.isPressed()) {
            ficha23.setImage(new Image(type.ruta));
            ficha23.setOpacity(0.5);
        } else if (ficha31.isPressed()) {
            ficha31.setImage(new Image(type.ruta));
            ficha31.setOpacity(0.5);
        } else if (ficha32.isPressed()) {
            ficha32.setImage(new Image(type.ruta));
            ficha32.setOpacity(0.5);
        } else if (ficha33.isPressed()) {
            ficha33.setImage(new Image(type.ruta));
            ficha33.setOpacity(0.5);
        }

    }

    private void clickAnimation(ImageView imv) {
        FadeTransition ft = new FadeTransition(Duration.millis(2000), imv);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
    }
}
