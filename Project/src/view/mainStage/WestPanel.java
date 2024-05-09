package view.mainStage;

import controller.MainGUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.GetGUIController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WestPanel implements Initializable {
    private MainGUIController mainGUIController;
    @FXML
    private Button createRecipeButton;
    @FXML
    private Button logOutButton;

    public WestPanel() {
        this.mainGUIController = GetGUIController.getGuiController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createRecipeButton.setOnAction(new ButtonHandler());
        logOutButton.setOnAction(new ButtonHandler());
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            if (clickedButton.getText().equals("Skapa eget recept")) {
                try {
                    mainGUIController.openRecipeCreationScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (clickedButton.getText().equals("Logga ut")) {
                mainGUIController.logOut();
            }
        }
    }
}