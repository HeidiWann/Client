package model;

import controller.MainGUIController;
import controller.RecipeGUIController;
import controller.UserGUIController;

public class GetGUIController {
    private static MainGUIController mainGuiController;
    private static UserGUIController userGUIController;
    private static RecipeGUIController recipeGUIController;

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
}