package view.mainStage;


import controller.ConnectionController;
import controller.RecipeController;
import controller.UserController;
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
        Scene scene = new Scene(root, 850, 550);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);

        UserController userController = new UserController();
        RecipeController recipeController = new RecipeController();
        ConnectionController connectionController = new ConnectionController(userController);
        connectionController.connectToServer();

        /*
        Thread.sleep(1000); // Måste vänta så uppkopplingen hinner skapas
        connectionController.sendIntention(30);
        connectionController.sendObject(new User("Anton","1234"));

        connectionController.sendIntention(30);
        connectionController.sendObject(new User("Anton","1234"));

         */
        
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("Lambada works");
            connectionController.serverDisconnected();
            System.exit(0);
        });

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
