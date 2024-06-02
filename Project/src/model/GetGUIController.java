package model;

import controller.*;

/**
 * This class contains static variables in order to make the application work with the same instaces since JavaFX creates
 * everything on its own
 * @author Anton Persson
 */
public class GetGUIController {
    private static MainGUIController mainGuiController;
    private static UserGUIController userGUIController;
    private static RecipeGUIController recipeGUIController;
    private static RecipeCreationController recipeCreationController;
    private static AddIngredientGUIController addIngredientGUIController;
    private static IngredientController ingredientController;
    private static HandleAccountController handleAccountController;

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

    public static HandleAccountController getHandleAccountController() {
        if (handleAccountController == null) {
            handleAccountController = new HandleAccountController();
        }
        return handleAccountController;
    }
}