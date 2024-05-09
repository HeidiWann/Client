package view.userStage;

import controller.UserGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.GetGUIController;

import java.lang.String;

import javax.swing.*;

public class RegisterSouthPanel {
    private UserGUIController userGUIController;
    @FXML
    private Button registerButton;

    public RegisterSouthPanel() {
        userGUIController = GetGUIController.getUserGUIController();
    }

    public void clickedRegister(ActionEvent e) {
        String userName = userGUIController.getRegisterWestPanel().getRegisterUserName().getText();
        String firstPassword = userGUIController.getRegisterWestPanel().getRegisterFirstPassword().getText();
        String secondPassword = userGUIController.getRegisterWestPanel().getRegisterSecondPassword().getText();
        Alert alert;

        if (!userName.isEmpty() || !firstPassword.isEmpty() || !secondPassword.isEmpty()) {
            if (firstPassword.equals(secondPassword)) {
                if (userGUIController.tryToRegister(userName, firstPassword)) {
                    userGUIController.setLoggedInStatus(true);
                    Stage stage = (Stage) registerButton.getScene().getWindow();
                    stage.close();
                } else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registreringen misslyckades");
                    alert.setHeaderText(null);
                    alert.setContentText("Användarnamnet som angavs fanns redan. Vänligen försök med ett annat användarnamn");
                    alert.showAndWait();
                }
            }
            else {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Registreringen misslyckades");
                    alert.setHeaderText(null);
                    alert.setContentText("Lösenorden matchade inte. Vänligen försök igen");
                    alert.showAndWait();
                }
        }
        else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registreringen misslyckades");
                alert.setHeaderText(null);
                alert.setContentText("Vänligen ange värden i alla textfält");
                alert.showAndWait();
            }
    }
}
