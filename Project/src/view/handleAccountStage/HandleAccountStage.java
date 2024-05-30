package view.handleAccountStage;

import controller.HandleAccountController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.GetGUIController;
import model.Recipe;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HandleAccountStage implements Initializable {
    private final HandleAccountController handleAccountController;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ListView<Recipe> favoriteRecipesList;
    @FXML
    private Button viewRecipeButton;
    @FXML
    private Button removeRecipeButton;
    @FXML
    private Button changeUserName;
    @FXML
    private Button changePassword;

    public HandleAccountStage() {
        handleAccountController = GetGUIController.getHandleAccountController();
    }

    public void setUser(User user) {
        handleAccountController.setCurrentUser(user);
    }

    public void start(Stage handleAccountStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../handleAccountStage/HandleAccount.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        handleAccountStage.setScene(new Scene(root));
        handleAccountStage.setResizable(false);
        handleAccountStage.setX(550);
        handleAccountStage.setY(30);
        handleAccountStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewRecipeButton.setOnAction(new ButtonHandler());
        removeRecipeButton.setOnAction(new ButtonHandler());
        changeUserName.setOnAction(new ButtonHandler());
        changePassword.setOnAction(new ButtonHandler());
    }

    private void handleViewRecipeButton() {
        Recipe selectedRecipe = favoriteRecipesList.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            handleAccountController.viewRecipe(selectedRecipe);
        } else {
            showAlert("Error", "No recipe selected");
        }
    }

    private void handleRemoveRecipeButton() {
        Recipe selectedRecipe = favoriteRecipesList.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            handleAccountController.removeFavoriteRecipe(selectedRecipe);
        } else {
            showAlert("Error", "No recipe selected.");
        }
    }

    private void handleChangeUsernameButton() {
        String newUserName = usernameField.getText();
        if (!newUserName.isEmpty()) {
            handleAccountController.changeUsername(newUserName);
        } else {
            showAlert("Error", "Username cannot be empty.");
        }
    }

    private void handleChangePasswordButton() {
        String newPassword = passwordField.getText();
        if (!newPassword.isEmpty()) {
            handleAccountController.changePassword(newPassword);
        } else {
            showAlert("Error", "Password cannot be empty.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            if (clickedButton == viewRecipeButton) {
                handleViewRecipeButton();
            } else if (clickedButton == removeRecipeButton) {
                handleRemoveRecipeButton();
            } else if (clickedButton == changeUserName) {
                handleChangeUsernameButton();
            } else if (clickedButton == changePassword) {
                handleChangePasswordButton();
            }
        }
    }
}
