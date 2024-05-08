package view.userStage;

import controller.UserGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.GetGUIController;
import javax.swing.*;

public class UserEastPanel {
    @FXML
    private Button login;
    private UserGUIController userGUIController;

    public UserEastPanel() {
        this.userGUIController = GetGUIController.getUserGUIController();

    }

    public void buttonClicked(ActionEvent e) {
        if (userGUIController.getLoginUserNameTextField() != null && userGUIController.getLoginPasswordField() != null) {
            JOptionPane.showMessageDialog(null, "Du är inloggad");
            userGUIController.setLoggedInStatus(true);
            Stage stage = (Stage) login.getScene().getWindow();
            stage.close();
        } else if (userGUIController.getLoginUserNameTextField() == null) {
            JOptionPane.showMessageDialog(null, "Var vänligen ange ett användarnamn");
        } else if (userGUIController.getLoginPasswordField() == null) {
            JOptionPane.showMessageDialog(null, "Var vänlig ange ett lösenord");
        }
    }
}
