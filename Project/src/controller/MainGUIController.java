package controller;


import javafx.scene.image.ImageView;
import model.*;
//import model.User;
import view.mainStage.SouthPanel;
import view.recipeStage.RecipeCenterPanel;
import view.recipeStage.RecipeMainScene;
import view.recipeStage.RecipeNorthPanel;
import view.recipeStage.RecipeSouthPanel;

import java.util.ArrayList;

public class MainGUIController {

    private RecipeGUIController recipeGUIController;
    private UserGUIController userGUIController;
    private ClientController clientController;
    private RecipeController recipeController;
    private SouthPanel southPanel;

    public MainGUIController() {
        this.recipeGUIController = GetGUIController.getrecipeGUIController();
        this.userGUIController = GetGUIController.getUserGUIController();
    }



    public void updateListOfRecipes(ArrayList<Recipe> recipes) {
        southPanel.addRecipes(recipes);
    }

    public User getUserName(User userName) {
        return userName;
    }

    public RecipeGUIController getRecipeGUIController() {
        return recipeGUIController;
    }

    public UserGUIController getUserGUIController() {
        return userGUIController;
    }
}