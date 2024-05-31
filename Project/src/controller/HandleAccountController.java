package controller;

import javafx.fxml.FXML;
import model.Recipe;
import model.User;

import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;

/**
 * Controller class for handling account-related operations.
 * Manages the current user's information.
 *
 * @Author: Salma Omar
 */
public class HandleAccountController {
    private UserRecipeController userRecipeController;
    private User currentUser;
    private ListView<Recipe> favoriteRecipesList;
    private ListView<Recipe> createdRecipesList;
    private ConnectionController connectionController;

    /**
     * Default constructor for HandleAccountController.
     *
     * @Author: Salma Omar
     */
    public HandleAccountController() {
        this.userRecipeController = new UserRecipeController();
        this.userRecipeController.setHandleAccountController(this);
    }

    /**
     * Sets the current user and updates the UI with the user's data.
     *
     * @param user The user to set as the current user.
     * @Author: Salma Omar
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUIWithFavoriteRecipes();
        updateUIWithCreatedRecipes();
    }

    /**
     * Updates the UI components with the current user's data.
     *
     * @Author: Salma Omar
     */
    public void updateUIWithFavoriteRecipes() {
        favoriteRecipesList.getItems().clear();
        ArrayList<Recipe> favoriteRecipes = userRecipeController.getFavoriteRecipes();
        for (Recipe recipe : favoriteRecipes) {
            System.out.println(recipe.toString());
        }
        favoriteRecipesList.getItems().addAll(favoriteRecipes);
    }

    public void updateUIWithCreatedRecipes() {
        createdRecipesList.getItems().clear();
        ArrayList<Recipe> createdRecipes = userRecipeController.getUsersOwnRecipes();
        for (Recipe recipe: createdRecipes) {
            System.out.println(recipe.toString());
        }
        createdRecipesList.getItems().addAll(createdRecipes);
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

    public void setFavoriteRecipesList(ListView<Recipe> favoriteRecipesList) {
        this.favoriteRecipesList = favoriteRecipesList;
    }

    public void removeFavoriteRecipe(Recipe selectedRecipe) {
        userRecipeController.getFavoriteRecipes().remove(selectedRecipe);
        favoriteRecipesList.getItems().remove(selectedRecipe);
    }

    public void removeCreatedRecipe(Recipe selectedRecipe) {
        userRecipeController.getUsersOwnRecipes().remove(selectedRecipe);
        createdRecipesList.getItems().remove(selectedRecipe);
    }
}