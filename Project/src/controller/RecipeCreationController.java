package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import model.*;
import view.recipeCreationStage.RecipeCreationBottomLayer;
import view.recipeCreationStage.RecipeCreationBottomMiddleLayer;
import view.recipeCreationStage.RecipeCreationTopMiddleLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller class for handling the creation of recipes in the application.
 * It manages the user input from the GUI and interacts with the model to create and store new recipes.
 *
 * @Author: Salma Omar
 * @Author: Anton Persson
 * @Author: Christoffer Salomonsson
 */
public class RecipeCreationController {
    private RecipeCreationBottomLayer recipeCreationBottomLayer;
    private RecipeCreationBottomMiddleLayer recipeCreationBottomMiddleLayer;
    private RecipeCreationTopMiddleLayer recipeCreationTopMiddleLayer;
    private RecipeController recipeController;
    private UserRecipeController userRecipeController;
    private User currentUser;
    private ConnectionController connectionController;

    public RecipeCreationController() {
    }

    /**
     * Closes the window associated with the given button.
     *
     * @param button the button whose window should be closed.
     */
    public void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /**
     * Creates a recipe using the data provided by the GUI layers.
     *
     * @return true if the recipe is successfully created, false otherwise
     * @Author: Anton Persson
     * @Author: Salma Omar
     */
    public boolean createRecipe() {
        String author = GetGUIController.getGuiController().getUserController().getLoggedInUser().getUserName();
        String recipeName = recipeCreationTopMiddleLayer.getRecipeName();
        String recipeInstruction = recipeCreationTopMiddleLayer.getInstruction();
        ArrayList<Ingredient> ingredients = convertToIngredientArray(recipeCreationBottomMiddleLayer.getIngredients());
        ImageView imageOfRecipe = recipeCreationBottomLayer.getImageOfRecipe();
        ArrayList<FoodCategory> categories = convertToFoodCategory(recipeCreationBottomLayer.getListOfCategories());

        if (recipeName.isEmpty() || recipeInstruction.isEmpty() || ingredients.isEmpty() || imageOfRecipe == null || categories.isEmpty()) {
            return false;
        } else {
            createNewRecipe(author, recipeInstruction, imageOfRecipe, ingredients, recipeName, categories);
        }


        return true;
    }

    /**
     * Creates a new Recipe object and adds it to the recipe controller and connection controller.
     *
     * @param author         the author of the recipe
     * @param instructions   the instructions for the recipe
     * @param imageOfRecipe  the image of the recipe
     * @param ingredients    the ingredients for the recipe
     * @param nameOfFood     the name of the recipe
     * @param typeOfFood     the categories of the recipe
     *
     * @Author: Christoffer Salomonsson
     */
    public void createNewRecipe(String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(author, instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);

        recipeController.addRecipeToArray(recipe);
        recipeController.updateListOfRecipes();
        userRecipeController.addUsersOwnRecipe(recipe);

        connectionController.createNewRecipe(recipe);

    }

    /**
     * Converts a given Recipe object to a new Recipe object with an ImageView created from a byte array.
     *
     * @param recipe the recipe to convert
     * @return the converted recipe
     * @Author: Christoffer Salomonsson
     */
    public Recipe convertRecipe(Recipe recipe) {
        ImageView imageview = byteArrayToImageView(recipe.getImageOfRecipe());
        Recipe convertedRecipe = new Recipe(recipe.getAuthor(), recipe.getInstructions(), imageview, recipe.getDish().getIngredients(), recipe.getRecipeName(), recipe.getDish().getTypeOfFood());
        if(recipe.getImageOfRecipe() == null){
            System.out.println("Fungerar inte ens här");
        }
        return convertedRecipe;
    }

    /**
     * Converts a list of category names to a list of FoodCategory enums.
     *
     * @param listToConvert the list of category names to convert
     * @return the list of FoodCategory enums
     * @Author: Anton Persson
     */
    public ArrayList<FoodCategory> convertToFoodCategory (ArrayList<String> listToConvert) {
        ArrayList<FoodCategory> listOfCategories = new ArrayList<>();
        if (listToConvert != null) {
            for (int i = 0; i < listToConvert.size(); i++) {
                listOfCategories.add(FoodCategory.valueOf(listToConvert.get(i)));
            }
        }
        return listOfCategories;
    }

    /**
     * Converts a list of ingredient strings to a list of Ingredient objects.
     *
     * @param listToConvert the list of ingredient strings to convert
     * @return the list of Ingredient objects
     * @Author: Anton Persson
     */
    public ArrayList<Ingredient> convertToIngredientArray (ArrayList<String> listToConvert) {
        ArrayList<Ingredient> listOfCategories = new ArrayList<>();
        String ingredientName = "";
        String ingredientCost = "";
        String amountOfIngredient = "";
        String measurement = "";

        for (int i = 0; i < listToConvert.size(); i++) {
            String[] splitList = listToConvert.get(i).split("\\|");
            for (int j = 0; j < 4; j++) {
                String currentWord = splitList[j].trim();
                if (j == 0) {
                    ingredientName = currentWord;
                } else if (j == 1) {
                    ingredientCost = currentWord;
                } else if (j ==2) {
                    amountOfIngredient = currentWord;
                } else {
                    measurement = currentWord;
                    listOfCategories.add(new Ingredient(ingredientName, Double.parseDouble(ingredientCost), Double.parseDouble(amountOfIngredient), Measurement.valueOf(measurement)));
                }
            }
        }
        return listOfCategories;
    }

    public ArrayList<String> getEveryCategory() {
        return recipeController.gatherFoodCategories();
    }

    public ArrayList<String> getEveryMeasurement() {
        return recipeController.gatherMeasurements();
    }

    public void setRecipeCreationBottomLayer(RecipeCreationBottomLayer recipeCreationBottomLayer) {
        this.recipeCreationBottomLayer = recipeCreationBottomLayer;
    }

    public void setRecipeCreationBottomMiddleLayer(RecipeCreationBottomMiddleLayer recipeCreationBottomMiddleLayer) {
        this.recipeCreationBottomMiddleLayer = recipeCreationBottomMiddleLayer;
    }

    public void setRecipeCreationTopMiddleLayer(RecipeCreationTopMiddleLayer recipeCreationTopMiddleLayer) {
        this.recipeCreationTopMiddleLayer = recipeCreationTopMiddleLayer;
    }

    public void setRecipeController(RecipeController recipeController) {
        this.recipeController = recipeController;
    }
    public void setConnectionController(ConnectionController connectionController){
        this.connectionController = connectionController;

    }

    /**
     * Converts a byte array to an ImageView.
     *
     * @param byteArray the byte array to convert
     * @return the ImageView created from the byte array
     * @Author: Christoffer Salomonsson
     */
    public static ImageView byteArrayToImageView(byte[] byteArray) {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
    Image image = new Image(inputStream);
    ImageView imageView = new ImageView(image);
    return imageView;
    }
    public RecipeCreationBottomMiddleLayer getRecipeCreationBottomMiddleLayer() {
        return recipeCreationBottomMiddleLayer;
    }

    public void setUserRecipeController(UserRecipeController userRecipeController) {
        this.userRecipeController = userRecipeController;
    }

    public ConnectionController getConnectionController() {
        return connectionController;
    }
}
