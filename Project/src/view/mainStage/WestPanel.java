package view.mainStage;

import controller.MainGUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
                    if(!mainGUIController.openRecipeCreationScene()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Not logged in");
                        alert.setHeaderText(null);
                        alert.setContentText("You need to be signed in to create a recipe. Please sign in and try again");
                        alert.showAndWait();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (clickedButton.getText().equals("Logga ut")) {
                mainGUIController.logOut();
            }
        }
    }
}