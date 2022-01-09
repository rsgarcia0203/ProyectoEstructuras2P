/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.model.Sonidos;
import ec.edu.espol.proyectoestructuras2p.App;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author rsgar
 */
public class PantallaPrincipalController {

    @FXML
    private Button btn_ng;
    @FXML
    private Button btn_exit;

    @FXML
    private void mouseReleased(MouseEvent event) {
        btn_ng.setEffect(new DropShadow());
        btn_ng.setOpacity(1);
        btn_exit.setEffect(new DropShadow());
        btn_exit.setOpacity(1);
    }

    @FXML
    private void mouseExited(MouseEvent event) {
        btn_ng.setOpacity(1);
        btn_exit.setOpacity(1);
    }

    @FXML
    private void mouseEntered(MouseEvent event) {
        if (btn_ng.isHover()) {
            btn_ng.setOpacity(0.7);
        }

        if (btn_exit.isHover()) {
            btn_exit.setOpacity(0.7);
        }

        Sonidos.hover();
    }

    @FXML
    private void newGame(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = App.loadFXMLoader("VentanaEspera");
            App.setRoot(fxmlloader);
            Sonidos.goButton();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "Error al cargar la ventana.");
            a.show();
        }
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

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

}
