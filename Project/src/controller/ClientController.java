package controller;

import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.User;
import java.io.IOException;
import java.util.ArrayList;

public class ClientController {
    private MainGUIController mainGuiController;
    private ConnectionController connectionController;
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
    public void login(String userName, String password) throws IOException {
        connectionController.sendObject(new User(userName,password));
    }
    public void register(String userName, String password) throws IOException {
        User newUser = userController.createNewUser(userName, password);
        connectionController.sendObject(newUser);
    }


    /*public void uploadRecipe(Recipe recipe){

        connectionController.sendObject(recipe);
    }

     */
    public void createNewRecipe(String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood){
        recipeController.createNewRecipe(instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);
    }
}
