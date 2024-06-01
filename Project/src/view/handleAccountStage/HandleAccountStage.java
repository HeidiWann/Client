package view.handleAccountStage;

import controller.HandleAccountController;
import javafx.scene.image.ImageView;
import model.GetGUIController;
import model.Recipe;
import model.User;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Class for handling account stage in the GUI.
 * It provides functionalities for viewing and managing user account details and favorite recipes.
 * Implements Initializable for JavaFX initialization.
 *
 * @Author: Salma Omar
 */
public class HandleAccountStage implements Initializable {
    @FXML
    public ListView<Recipe> recipesListView;
    @FXML
    ImageView imageOfSelectedRecipe;
    private final HandleAccountController handleAccountController;
    @FXML
    private Label usernameLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label categoryLabel;
    @FXML
    private Button viewRecipeButton;
    @FXML
    private Button removeRecipeButton;
    @FXML
    private Button changePassword;
    @FXML
    private Button toggleCategoryButton;
    private boolean showingFavoriteRecipes = true;

    /**
     * Default constructor initializes the handle account controller.
     *
     * @Author: Salma Omar
     */
    public HandleAccountStage() {
        handleAccountController = GetGUIController.getHandleAccountController();

    }

    /**
     * Sets the current user for this stage.
     *
     * @param user The user to set as the current user.
     * @Author: Salma Omar
     */
    public void setUser(User user) {
        handleAccountController.setCurrentUser(user);
        usernameLabel.setText(user.getUserName());
    }

    /**
     * Starts the handle account stage by loading the FXML and setting the scene.
     *
     * @param handleAccountStage The stage to set up.
     * @Author: Salma Omar
     */
    @FXML
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

    /**
     * Initializes the class. This method is automatically called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root objects, or null if the location is not known.
     * @param resourceBundle The resources used to localise the root object, or null if the root object was not localised.
     * @Author: Salma Omar
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewRecipeButton.setOnAction(new ButtonHandler());
        removeRecipeButton.setOnAction(new ButtonHandler());
        changePassword.setOnAction(new ButtonHandler());
        toggleCategoryButton.setOnAction(new ButtonHandler());
        handleAccountController.setListOfRecipes(recipesListView);
        setUser(GetGUIController.getGuiController().getUserController().getLoggedInUser());
    }

    /**
     * Loads the favorite recipes of a user.
     *
     * @Author: Salma Omar
     */
    @FXML
    private void loadFavoriteRecipes() {
        try {
            handleAccountController.updateUIWithFavoriteRecipes(recipesListView);
            categoryLabel.setText("Favorite Recipes");
            showingFavoriteRecipes = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the created recipes of a user on the GUI.
     *
     * @Author: Salma Omar
     */
    private void loadCreatedRecipes() {
        try {
            System.out.println("Reached loadCreatedRecipes method");
            handleAccountController.updateUIWithCreatedRecipes();
            categoryLabel.setText("Created Recipes");
            showingFavoriteRecipes = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action toggling between the two categories (favorite recipes & own recipes).
     *
     * @Author: Salma Omar
     */
    private void handleToggleCategoryButton() {
        try {
            if (showingFavoriteRecipes) {
                loadCreatedRecipes();
            } else {
                loadFavoriteRecipes();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Handles the action of viewing a selected recipe.
     *
     * @Author: Salma Omar
     */
    private void handleViewRecipeButton() {
        Recipe selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            handleAccountController.viewRecipe(selectedRecipe);
        } else {
            showAlert("Error", "No recipe selected");
        }
    }

    /**
     * Handles the action of removing a selected recipe from the recipes list.
     *
     * @Author: Salma Omar
     */
    private void handleRemoveRecipeButton() {
        Recipe selectedRecipe = recipesListView.getSelectionModel().getSelectedItem();
        if (selectedRecipe != null) {
            if (showingFavoriteRecipes) {
                handleAccountController.removeFavoriteRecipe(selectedRecipe);
                loadFavoriteRecipes();
            } else {
                handleAccountController.removeCreatedRecipe(selectedRecipe);
                loadCreatedRecipes();
            }
        } else {
            showAlert("Error", "No recipe selected.");
        }
    }
    

    /**
     * Handles the action of changing the password.
     *
     * @Author: Salma Omar
     */
    private void handleChangePasswordButton() {
        String newPassword = passwordField.getText();
        if (!newPassword.isEmpty()) {
            handleAccountController.changePassword(newPassword);
        } else {
            showAlert("Error", "Password cannot be empty.");
        }
    }

    /**
     * Shows an alert with the given title and content.
     *
     * @param title The title of the alert.
     * @param content The content of the alert.
     * @Author: Salma Omar
     */
    @FXML
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Inner class for handling button actions.
     *
     * @Author: Salma Omar
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();

            if (clickedButton == viewRecipeButton) {
                handleViewRecipeButton();
            } else if (clickedButton == removeRecipeButton) {
                handleRemoveRecipeButton();
            } else if (clickedButton == toggleCategoryButton) {
                handleToggleCategoryButton();
            } else if (clickedButton == changePassword) {
                handleChangePasswordButton();
            }
        }
    }
}
