package view.mainStage;

import controller.ClientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../mainStage/MainScene.fxml"));
        Scene scene = new Scene(root, 850, 550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);


        ClientController clientController = new ClientController();


        primaryStage.setOnCloseRequest(event -> {
            try {
                clientController.closeConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
