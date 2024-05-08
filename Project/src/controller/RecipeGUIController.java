package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.Recipe;
import view.recipeStage.RecipeCenterPanel;
import view.recipeStage.RecipeMainScene;
import view.recipeStage.RecipeNorthPanel;
import view.recipeStage.RecipeSouthPanel;
import java.util.ArrayList;

/**
 * This class handles the communication and manipulation of data that is associated to the GUI that displays a
 * recipe
 *
 * @author Anton Persson
 */
public class RecipeGUIController {
    private RecipeNorthPanel recipeNorthPanel;
    private RecipeCenterPanel recipeCenterPanel;
    private RecipeSouthPanel recipeSouthPanel;
    private RecipeMainScene recipeMainScene;

    public RecipeGUIController() {
        recipeMainScene = new RecipeMainScene(this);
    }

    /**
     * This method alters the data of a chosen recipe from the list in {@link view.mainStage.SouthPanel} to
     * prepare it for being displayed in a new Stage. The method gathers the different parts of the {@link Recipe}
     * before creating a new Stage and placing the info of the recipe in it.
     *
     * @param recipe The recipe to show in a new Stage
     * @author Anton Persson
     */
    public void showSelectedRecipe(Object recipe) {
        Recipe recipeToShow = (Recipe) recipe;
        String recipeInstructions = recipeToShow.getInstructions();
        ArrayList<String> ingredientsInRecipe = getFormattedIngredients((recipeToShow.getDish().getIngredients()));
        String nameOfRecipe = recipeToShow.getRecipeName();
        ArrayList<String> recipesCategories = getFormattedCategories(recipeToShow.getDish().getTypeOfFood());
        double totalCostOfRecipe = getTotalCostOfRecipe(recipeToShow.getDish().getIngredients());

        Image tempImageInRecipe = recipeToShow.getImageOfRecipe().getImage();
        ImageView imageInRecipe = new ImageView(tempImageInRecipe);
        imageInRecipe.setFitHeight(209);
        imageInRecipe.setFitWidth(400);

        recipeMainScene.createRecipeWindow();
        recipeCenterPanel.insertIngredientInfo(ingredientsInRecipe, String.valueOf(totalCostOfRecipe), "ICA");
        recipeCenterPanel.insertImage(imageInRecipe);
        recipeSouthPanel.insertInstructions(recipeInstructions);
        recipeNorthPanel.setHeaders("Anton", nameOfRecipe, recipesCategories.toString());
    }

    /**
     * This method formats the ingredients to a String to easier insert the recipes into the GUI.
     *
     * @param ingredients The ingredients to convert to String
     * @return An {@link ArrayList} containing the formatted ingredients
     * @author Anton Persson
     */
    public ArrayList<String> getFormattedIngredients(ArrayList<Ingredient> ingredients) {
        ArrayList<String> formattedIngredients = new ArrayList<>();

        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                formattedIngredients.add(ingredient.toString() + "\n");
            }
        }

        return formattedIngredients;
    }

    /**
     * This method formats the categories to a String to easier insert the categories into the GUI.
     *
     * @param categories An {@link ArrayList} containing the categories
     * @return An ArrayList containing the formatted categories as Strings
     * @author Anton Persson
     */
    public ArrayList<String> getFormattedCategories(ArrayList<FoodCategory> categories) {
        ArrayList<String> formattedCategories = new ArrayList<>();
        if (categories != null) {
            for (FoodCategory category : categories) {
                formattedCategories.add(category.toString());
            }
        }
        return formattedCategories;
    }

    /**
     * This method calculates the sum of the cost of the ingredients in a {@link Recipe}
     *
     * @param ingredients The {@link Ingredient} in the recipe
     * @return The cost of the ingreidients in the recipe
     * @author Anton Persson
     */
    public double getTotalCostOfRecipe(ArrayList<Ingredient> ingredients) {
        double sumOfRecipe = 0;
        for (Ingredient ingredient : ingredients) {
            sumOfRecipe += ingredient.getPrice();
        }
        return sumOfRecipe;
    }

    public void setRecipeCenterPanel(RecipeCenterPanel recipeCenterPanel) {
        this.recipeCenterPanel = recipeCenterPanel;
    }

    public void setRecipeSouthPanel(RecipeSouthPanel recipeSouthPanel) {
        this.recipeSouthPanel = recipeSouthPanel;
    }

    public void setRecipeNorthPanel(RecipeNorthPanel recipeNorthPanel) {
        this.recipeNorthPanel = recipeNorthPanel;
    }
}