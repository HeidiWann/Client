package controller;

import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.Recipe;
import view.recipeStage.RecipeCenterPanel;
import view.recipeStage.RecipeMainScene;
import view.recipeStage.RecipeNorthPanel;
import view.recipeStage.RecipeSouthPanel;

import java.util.ArrayList;

public class RecipeGUIController {
    private RecipeNorthPanel recipeNorthPanel;
    private RecipeCenterPanel recipeCenterPanel;
    private RecipeSouthPanel recipeSouthPanel;
    private RecipeMainScene recipeMainScene;
    public RecipeGUIController() {
        recipeMainScene = new RecipeMainScene(this);
    }

    public void showSelectedRecipe(Object recipe) {
        Recipe recipeToShow = (Recipe) recipe;
        String recipeInstructions = recipeToShow.getInstructions();
        ImageView imageInRecipe = recipeToShow.getImageOfRecipe();
        ArrayList<String> ingredientsInRecipe = getFormattedIngredients((recipeToShow.getDish().getIngredients()));
        String nameOfIngredient = recipeToShow.getRecipeName();
        ArrayList<String> recipesCategories = getFormattedCategories(recipeToShow.getDish().getTypeOfFood());

        recipeMainScene.createRecipeWindow();
        recipeCenterPanel.insertText("Funkar detta?");
    }

    public ArrayList<String> getFormattedIngredients(ArrayList<Ingredient> ingredients) {
        ArrayList<String> formattedIngredients = new ArrayList<>();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                formattedIngredients.add(ingredient.toString());
            }
        }
        return formattedIngredients;
    }

    public ArrayList<String> getFormattedCategories(ArrayList<FoodCategory> categories) {
        ArrayList<String> formattedCategories = new ArrayList<>();
        if (categories != null) {
            for (FoodCategory category : categories) {
                formattedCategories.add(category.toString());
            }
        }
        return formattedCategories;
    }

    public void setRecipeCenterPanel(RecipeCenterPanel recipeCenterPanel) {
        this.recipeCenterPanel = recipeCenterPanel;
    }
}
