package view.mainStage;

import controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This method launches JavaFX and starts the procces of reading the FXML files
 * @author Anton Persson
 */
public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../mainStage/MainScene.fxml"));
        primaryStage.setScene(new Scene(root, 850, 550));
        primaryStage.setResizable(false);
        primaryStage.show();
        new ClientController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
