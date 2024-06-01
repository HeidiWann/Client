package controller;

import model.Recipe;
import model.User;

import javafx.scene.control.ListView;

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
    private ListView<Recipe> listOfRecipes;
    private ConnectionController connectionController;

    /**
     * Default constructor for HandleAccountController.
     *
     * @Author: Salma Omar
     */
    public HandleAccountController() {

    }

    public void setUserRecipeController(UserRecipeController userRecipeController) {
        this.userRecipeController = userRecipeController;
    }

    /**
     * Sets the current user and updates the UI with the user's data.
     *
     * @param user The user to set as the current user.
     * @Author: Salma Omar
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
//        updateUIWithFavoriteRecipes();
//        updateUIWithCreatedRecipes();
    }

    /**
     * Updates the UI components with the current user's data.
     *
     * @Author: Salma Omar
     */
    public void updateUIWithFavoriteRecipes(ListView listView) {
        listOfRecipes = listView;
        listOfRecipes.getItems().clear();
        ArrayList<Recipe> favoriteRecipes = userRecipeController.getFavoriteRecipes();
        for (Recipe recipe : favoriteRecipes) {
            System.out.println(recipe.toString());
        }
        listOfRecipes.getItems().addAll(favoriteRecipes);
    }

    public void updateUIWithCreatedRecipes() {
        System.out.println("Reched method that updates GUI with created recipes ------------------------");
        listOfRecipes.getItems().clear();
        ArrayList<Recipe> createdRecipes = userRecipeController.getUsersOwnRecipes();
        System.out.println(createdRecipes);
        for (Recipe recipe: createdRecipes) {
            System.out.println(recipe.toString());
        }
        listOfRecipes.getItems().addAll(createdRecipes);
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

    public void setListOfRecipes(ListView<Recipe> listOfRecipes) {
        this.listOfRecipes = listOfRecipes;
    }

    public void removeFavoriteRecipe(Recipe selectedRecipe) {
        userRecipeController.getFavoriteRecipes().remove(selectedRecipe);
        listOfRecipes.getItems().remove(selectedRecipe);
    }

    public void removeCreatedRecipe(Recipe selectedRecipe) {
        userRecipeController.getUsersOwnRecipes().remove(selectedRecipe);
        listOfRecipes.getItems().remove(selectedRecipe);
    }
}