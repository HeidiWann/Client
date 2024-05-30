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

/**
 * Controller class for the west panel in the main stage of the GUI.
 * Manages the actions for buttons related to creating recipes, logging out, and handling account settings.
 * Implements Initializable for JavaFX initialization.
 *
 * @Author: Salma Omar
 * @Author: Anton Persson
 */
public class WestPanel implements Initializable {
    private MainGUIController mainGUIController;
    @FXML
    private Button createRecipeButton;
    @FXML
    private Button logOutButton;
    @FXML
    private Button handleAccountButton;

    /**
     * Default constructor initializes the main GUI controller.
     *
     * @Author: Anton Persson
     */
    public WestPanel() {
        this.mainGUIController = GetGUIController.getGuiController();
    }

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     * @Author: Salma Omar
     * @Author: Anton Persson
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        handleAccountButton.setOnAction(new ButtonHandler());
        createRecipeButton.setOnAction(new ButtonHandler());
        logOutButton.setOnAction(new ButtonHandler());
    }

    /**
     * Inner class for handling button actions.
     *
     * @Author: Salma Omar
     * @Author: Anton Persson
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        /**
         * Handles button click events and performs actions based on which button was clicked.
         *
         * @param event The event that occurred.
         * @Author: Salma Omar
         * @Author: Anton Persson
         */
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
            } else if (clickedButton.getText().equals("Hantera mitt konto")) {
                if (!mainGUIController.openHandleAccountScene()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Not logged in");
                    alert.setHeaderText(null);
                    alert.setContentText("Please login or register an account in order to handle your account!");
                    alert.showAndWait();
                }

            }
        }
    }
}