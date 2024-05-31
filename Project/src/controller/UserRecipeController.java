package controller;
import model.GetGUIController;
import model.Recipe;

import java.util.ArrayList;

public class UserRecipeController {
    private ArrayList<Recipe> favoriteRecipes;
    private ArrayList<Recipe> usersOwnRecipes;
    private ConnectionController connectionController;

    public UserRecipeController() {
        favoriteRecipes = new ArrayList<>();
        usersOwnRecipes = new ArrayList<>();
        GetGUIController.getRecipeCreationController().setUserRecipeController(this);
        this.connectionController = GetGUIController.getRecipeCreationController().getConnectionController();
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
        return usersOwnRecipes;
    }

    public void setUsersOwnRecipes(ArrayList<Recipe> usersOwnRecipes) {
        this.usersOwnRecipes = usersOwnRecipes;
    }

    public void addFavoriteRecipes(Recipe recipe) {
        favoriteRecipes.add(recipe);
        connectionController.addFavoriteRecipe(recipe);
    }

    public void addUsersOwnRecipe(Recipe recipe){
        usersOwnRecipes.add(recipe);
    }
}