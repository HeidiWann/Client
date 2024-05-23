package model;

import controller.*;

public class GetGUIController {
    private static MainGUIController mainGuiController;
    private static UserGUIController userGUIController;
    private static RecipeGUIController recipeGUIController;
    private static RecipeCreationController recipeCreationController;
    private static AddIngredientGUIController addIngredientGUIController;
    private static IngredientController ingredientController;

    public static MainGUIController getGuiController() {
        if (mainGuiController == null) {
            mainGuiController = new MainGUIController();
        }
        return mainGuiController;
    }

    public static UserGUIController getUserGUIController() {
        if (userGUIController == null) {
            userGUIController = new UserGUIController();
        }
        return userGUIController;
    }

    public static RecipeGUIController getrecipeGUIController() {
        if (recipeGUIController == null) {
            recipeGUIController = new RecipeGUIController();
        }
        return recipeGUIController;
    }

    public static RecipeCreationController getRecipeCreationController() {
        if (recipeCreationController == null) {
            recipeCreationController = new RecipeCreationController();
        }
        return recipeCreationController;
    }
    public static AddIngredientGUIController getAddIngredientGUIController() {
        if (addIngredientGUIController == null) {
            addIngredientGUIController = new AddIngredientGUIController();
        }
        return addIngredientGUIController;
    }

    public static IngredientController getIngredientController() {
        if (ingredientController==null){
            ingredientController = new IngredientController();
        }
        return ingredientController;
    }
}