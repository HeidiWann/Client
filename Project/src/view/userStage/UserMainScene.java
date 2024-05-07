package view.userStage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class UserMainScene {
    public UserMainScene() {

    }

    public void createUserWindow() {
        Stage userStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../userStage/UserMainScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        userStage.setScene(new Scene(root, 660, 345));
        userStage.setResizable(false);
        userStage.show();
    }
}
