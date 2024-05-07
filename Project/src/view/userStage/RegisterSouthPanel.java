package view.userStage;

import controller.UserGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        try {
            String firstPassword = userGUIController.getRegisterFirstPassword().getText().trim();
            String secondPassword = userGUIController.getRegisterSecondPassword().getText().trim();
            if (userGUIController.getRegisterUsername() != null && userGUIController.getRegisterFirstPassword() != null
                    && userGUIController.getRegisterSecondPassword() != null && firstPassword.equals(secondPassword)) {
                JOptionPane.showMessageDialog(null, "Du är registrerad");
                Stage stage = (Stage) registerButton.getScene().getWindow();
                stage.close();
            } else if (userGUIController.getRegisterUsername() == null) {
                JOptionPane.showMessageDialog(null, "Var vänligen ange ett användarnamn");
            } else if (userGUIController.getRegisterFirstPassword() == null) {
                JOptionPane.showMessageDialog(null, "Var vänlig ange ett lösenord");
            } else if (userGUIController.getRegisterSecondPassword() == null) {
                JOptionPane.showMessageDialog(null, "Vänligen upprepa lösenord");
            } else if (!firstPassword.equals(secondPassword)) {
                JOptionPane.showMessageDialog(null, "Lösenorden stämmer inte överrens");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(null, "Något blev fel, försök igen");

        }
    }
}
