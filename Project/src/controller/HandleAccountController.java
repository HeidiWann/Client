package controller;

import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Recipe;
import model.User;
import view.handleAccountStage.HandleAccountStage;
import view.handleAccountStage.RecipeDetailStage;

public class HandleAccountController {
    private User currentUser;
    private ListView<Recipe> favoriteRecipesList;
    private ConnectionController connectionController;
    private TextField usernameField;
    private PasswordField passwordField;

    public HandleAccountController() {
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUIWithUserData();
    }

    public void setConnectionController(ConnectionController connectionController) {
        this.connectionController = connectionController;
    }

    public void setUIComponents(TextField usernameField, PasswordField passwordField, ListView<Recipe> favoriteRecipesList) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.favoriteRecipesList = favoriteRecipesList;
        updateUIWithUserData();
    }

    public void updateUIWithUserData() {
        if (currentUser != null) {
            usernameField.setText(currentUser.getUserName());
            passwordField.setText(currentUser.getPassword());
            favoriteRecipesList.getItems().clear();
            favoriteRecipesList.getItems().addAll(currentUser.getFavoriteRecipes());
        }
    }

    public void saveAccountSettings() {
        String newUserName = usernameField.getText();
        String newPassword = passwordField.getText();

        if (!newUserName.isEmpty() && !newPassword.isEmpty()) {
            currentUser.setUserName(newUserName);
            currentUser.setPassWord(newPassword);
            connectionController.updateUserDetails(currentUser);
        } else {
            System.out.println("Error: Both username and password fields must be filled.");
        }
    }

    public void viewRecipe(Recipe recipe) {
        RecipeDetailStage recipeDetailStage = new RecipeDetailStage();
        recipeDetailStage.displayRecipeDetails(recipe);
        System.out.println("Viewing recipe: " + recipe.getRecipeName());
    }

    public void removeFavoriteRecipe(Recipe recipe) {
        currentUser.getFavoriteRecipes().remove(recipe);
        favoriteRecipesList.getItems().remove(recipe);
    }

    public void changeUsername(String newUserName) {
        currentUser.setUserName(newUserName);
        connectionController.updateUserDetails(currentUser);
        System.out.println("Username changed to: " + newUserName);
    }

    public void changePassword(String newPassword) {
        currentUser.setPassWord(newPassword);
        connectionController.updateUserDetails(currentUser);
        System.out.println("Password changed to: " + newPassword);
    }
}
