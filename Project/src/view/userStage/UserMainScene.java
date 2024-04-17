package view.userStage;

import controller.UserGUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class UserMainScene {
    private UserGUIController userGUIController;

    public UserMainScene(UserGUIController userGUIController){
        this.userGUIController = userGUIController;
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

    public UserGUIController getUserGUIController() {
        return userGUIController;
    }
}
