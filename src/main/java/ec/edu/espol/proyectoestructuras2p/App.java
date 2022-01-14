package ec.edu.espol.proyectoestructuras2p;

import ec.edu.espol.model.Partida;
import ec.edu.espol.model.Sonidos;
import ec.edu.espol.model.Tablero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("PantallaPrincipal"),700,700);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("TICTACTOE");
        stage.setResizable(false);
        stage.getIcons().add(new Image("ec/edu/espol/img/icon.png"));
        Sonidos.playBGMusic();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
     
    public static void setRoot(FXMLLoader fxmlloader) throws IOException {
        scene.setRoot(fxmlloader.load());
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static FXMLLoader loadFXMLoader(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }

    public static void main(String[] args) {
        //PRUEBA DEL METODO MEJOR JUGADA
        System.out.println("TABLERO INICIAL");
        Tablero tablero = new Tablero();
        /*tablero.getTable()[0][0]='X';
        tablero.getTable()[0][1]='X';
        tablero.getTable()[0][2]='O';
        tablero.getTable()[1][0]='-';
        tablero.getTable()[1][1]='O';
        tablero.getTable()[1][2]='-';
        tablero.getTable()[2][0]='-';
        tablero.getTable()[2][1]='O';
        tablero.getTable()[2][2]='X';*/
        for (char[] c : tablero.getTable()) {
            for (char c2 : c) {
                System.out.print(c2 + "|");
            }
            System.out.println();
        }
        System.out.println("MEJOR JUGADA");
        Tablero bestPlay = Partida.mejorJugada(tablero);
        for (char[] c : bestPlay.getTable()) {
            for (char c2 : c) {
                System.out.print(c2 + "|");
            }
            System.out.println();
        }
        launch();
    }

}