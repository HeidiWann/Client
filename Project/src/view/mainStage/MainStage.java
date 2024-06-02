package view.mainStage;


import controller.ConnectionController;
import controller.RecipeController;
import controller.UserController;
import controller.UserRecipeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This method launches JavaFX and starts the procces of reading the FXML files
 * @author Anton Persson
 * @author Heidi Wännman
 */

public class MainStage extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../mainStage/MainScene.fxml"));
        Scene scene = new Scene(root, 850, 550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        UserController userController = new UserController();
        RecipeController recipeController = new RecipeController();
        UserRecipeController userRecipeController = new UserRecipeController();
        ConnectionController connectionController = new ConnectionController(userController, recipeController,userRecipeController);
        connectionController.connectToServer();

        primaryStage.setOnCloseRequest(event -> {
            connectionController.serverDisconnected();
            System.exit(0);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
