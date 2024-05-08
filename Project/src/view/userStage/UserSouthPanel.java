package view.userStage;

import controller.UserGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.GetGUIController;

public class UserSouthPanel {
    @FXML
    private Button register;
    private UserGUIController userGUIController;

    public UserSouthPanel() {
        this.userGUIController = GetGUIController.getUserGUIController();
    }
    public UserGUIController getUserGUIController() {
        return userGUIController;
    }
    public void buttonClicked(ActionEvent event) {
        userGUIController.createRegisterWindow();
        Stage stage = (Stage) register.getScene().getWindow();
        stage.close();
    }
}
