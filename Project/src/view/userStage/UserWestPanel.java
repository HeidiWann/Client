package view.userStage;

import controller.UserGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.GetGUIController;

public class UserWestPanel {
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField userNameTextField;
    private UserGUIController userGUIController;

    public UserWestPanel() {
        this.userGUIController = GetGUIController.getUserGUIController();
        userGUIController.setUserWestPanel(this);
    }

    public PasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }
}
