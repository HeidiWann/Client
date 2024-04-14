package view;

import controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainScene.fxml"));
        primaryStage.setScene(new Scene(root, 850, 550));
        primaryStage.show();
        new ClientController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
