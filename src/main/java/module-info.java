module ec.edu.espol.proyectoestructuras2p {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.media;


    opens ec.edu.espol.proyectoestructuras2p to javafx.fxml;
    opens ec.edu.espol.controller to javafx.fxml;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.proyectoestructuras2p;
    exports ec.edu.espol.controller;
    exports ec.edu.espol.model;
}
