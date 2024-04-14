package controller;




import javafx.scene.image.Image;
import model.FoodCategory;
import model.Ingredient;
import model.User;

import java.util.ArrayList;

public class ClientController {
    private GUIController guiController;

    private RecipeController recipeController;
    private UserController userController;
    private User connectedUser;
    public ClientController() {

        this.recipeController = new RecipeController();
        this.userController = new UserController();
    }

    /** TODO
     * Koppla login och register med GUI för att få namn och password.
     */


    /*public void uploadRecipe(Recipe recipe){

        connectionController.sendObject(recipe);
    }

     */
    public void createNewRecipe(String instructions, Image imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood){
        recipeController.createNewRecipe(instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);
    }
}
