package controller;
import model.GetGUIController;
import model.Recipe;

import java.util.ArrayList;

/**
 * This class handles the recipes that a user has created and favorited
 * @author Christoffer Salomonsson
 * @author Anton Persson
 */
public class UserRecipeController {
    private HandleAccountController handleAccountController;
    private ArrayList<Recipe> favoriteRecipes;
    private ArrayList<Recipe> usersOwnRecipes;
    private ConnectionController connectionController;

    public UserRecipeController() {
        GetGUIController.getHandleAccountController().setUserRecipeController(this);
        favoriteRecipes = new ArrayList<>();
        usersOwnRecipes = new ArrayList<>();
        GetGUIController.getRecipeCreationController().setUserRecipeController(this);
        this.connectionController = GetGUIController.getRecipeCreationController().getConnectionController();

    }

    public void setHandleAccountController(HandleAccountController handleAccountController) {
        this.handleAccountController = handleAccountController;
    }

    public ArrayList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(ArrayList<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
        for (Recipe recipe : favoriteRecipes) {
            System.out.println(recipe.toString());
        }
    }

    public ArrayList<Recipe> getUsersOwnRecipes() {
        System.out.println("Nu är users own recipes" + usersOwnRecipes);
        return usersOwnRecipes;
    }

    public void setUsersOwnRecipes(ArrayList<Recipe> usersOwnRecipes) {
        this.usersOwnRecipes = usersOwnRecipes;
        for (Recipe recipe : usersOwnRecipes) {
            System.out.println("Receptet kom till UserRecipeController " + recipe.toString());
        }
    }

    public void addFavoriteRecipes(Recipe recipe) {
        favoriteRecipes.add(recipe);
        connectionController.addFavoriteRecipe(recipe);
    }

    public void addUsersOwnRecipe(Recipe recipe){
        usersOwnRecipes.add(recipe);
    }
}