package model;

import controller.MainGUIController;
import controller.RecipeCreationController;
import controller.RecipeGUIController;
import controller.UserGUIController;

public class GetGUIController {
    private static MainGUIController mainGuiController;
    private static UserGUIController userGUIController;
    private static RecipeGUIController recipeGUIController;
    private static RecipeCreationController recipeCreationController;

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
}