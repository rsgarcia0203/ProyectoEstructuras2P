module ec.edu.espol.proyectoestructuras2p {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.proyectoestructuras2p to javafx.fxml;
    exports ec.edu.espol.proyectoestructuras2p;
}
