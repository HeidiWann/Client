package view.userStage;

import controller.UserGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        String enteredUserName = "";
        String enteredPassword = "";
        Alert alert;

        enteredUserName = userGUIController.getTextLogInUserNameTextField().getText();
        enteredPassword = userGUIController.getPasswordField().getText();


        if (!enteredUserName.isEmpty() && !enteredPassword.isEmpty()) {
            if (userGUIController.tryToLogIn(enteredUserName, enteredPassword)) {
                System.out.println("DET FUNKKAAAA");
                Stage stage = (Stage) login.getScene().getWindow();
                stage.close();
            } else {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Inloggning misslyckas");
                alert.setHeaderText(null);
                alert.setContentText("Antingen användarnamnet eller lösenordet var fel. Vänligen försök igen");
                alert.showAndWait();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Inloggning misslyckas");
            alert.setHeaderText(null);
            alert.setContentText("Vänligen ange både ett användarnamn och lösenord");
            alert.showAndWait();
        }
    }
}
