package controller;

import model.Recipe;
import model.User;

import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Controller class for handling account-related operations.
 * Manages the current user's information.
 *
 * @Author: Salma Omar
 */
public class HandleAccountController {
    private User currentUser;
    private ListView<Recipe> favoriteRecipesList;
    private ConnectionController connectionController;
    private TextField usernameField;
    private PasswordField passwordField;

    /**
     * Default constructor for HandleAccountController.
     *
     * @Author: Salma Omar
     */
    public HandleAccountController() {
    }

    /**
     * Sets the current user and updates the UI with the user's data.
     *
     * @param user The user to set as the current user.
     * @Author: Salma Omar
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUIWithUserData();
    }

    /**
     * Updates the UI components with the current user's data.
     *
     * @Author: Salma Omar
     */
    public void updateUIWithUserData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            favoriteRecipesList.getItems().clear();
            favoriteRecipesList.getItems().addAll(currentUser.getFavoriteRecipes());
        }
    }

    /**
     * Removes a recipe from the user's list of favorite recipes.
     *
     * @param recipe The recipe to remove from the favorites lists.
     * @Author: Salma Omar
     */
    public void removeFavoriteRecipe(Recipe recipe) {
        currentUser.getFavoriteRecipes().remove(recipe);
        favoriteRecipesList.getItems().remove(recipe);
    }

    /**
     * Changes the current user's username.
     *
     * @param newUserName The new username to set.
     * @Author: Salma Omar
     */
    public void changeUsername(String newUserName) {
        currentUser.setUserName(newUserName);
        connectionController.updateUserDetails(currentUser);
        System.out.println("Username changed to: " + newUserName);
    }

    /**
     * Changes the current user's password.
     *
     * @param newPassword The new password to set.
     * @Author: Salma Omar
     */
    public void changePassword(String newPassword) {
        currentUser.setPassWord(newPassword);
        connectionController.updateUserDetails(currentUser);
        System.out.println("Password changed to: " + newPassword);
    }

    /**
     * View the selected recipe. This method can be implemented to show the recipe details in the UI.
     *
     * @param selectedRecipe The recipe to view.
     * @Author: Salma Omar
     */
    public void viewRecipe(Recipe selectedRecipe) {
        // Implementation for viewing the selected recipe.
    }
}
