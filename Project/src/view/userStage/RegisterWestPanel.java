package view.userStage;

import controller.UserGUIController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.GetGUIController;

public class RegisterWestPanel {
    @FXML
    private PasswordField registerSecondPassword;
    @FXML
    private PasswordField registerFirstPassword;
    @FXML
    private TextField registerUserName;
    private UserGUIController userGUIController;

    public RegisterWestPanel() {
        this.userGUIController = GetGUIController.getUserGUIController();
        userGUIController.setRegisterWestPanel(this);
    }

    public PasswordField getRegisterSecondPassword() {
        return registerSecondPassword;
    }

    public PasswordField getRegisterFirstPassword() {
        return registerFirstPassword;
    }

    public TextField getRegisterUserName() {
        return registerUserName;
    }
}
