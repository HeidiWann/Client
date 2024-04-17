package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.Measurement;
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
        ArrayList<String> ingredientsInRecipe = getFormattedIngredients((recipeToShow.getDish().getIngredients()));
        String nameOfRecipe = recipeToShow.getRecipeName();
        ArrayList<String> recipesCategories = getFormattedCategories(recipeToShow.getDish().getTypeOfFood());
        double totalCostOfRecipe = getTotalCostOfRecipe(recipeToShow.getDish().getIngredients());

        Image tempImageInRecipe = recipeToShow.getImageOfRecipe().getImage();
        ImageView imageInRecipe = new ImageView(tempImageInRecipe);
        imageInRecipe.setFitHeight(209);
        imageInRecipe.setFitWidth(400);

        recipeMainScene.createRecipeWindow();
        recipeCenterPanel.insertIngredientInfo(ingredientsInRecipe, String.valueOf(totalCostOfRecipe));
        recipeCenterPanel.insertImage(imageInRecipe);
        recipeSouthPanel.insertInstructions(recipeInstructions);
        recipeNorthPanel.setHeaders("Anton", nameOfRecipe, recipesCategories.toString());
    }

    public ArrayList<String> getFormattedIngredients(ArrayList<Ingredient> ingredients) {  // --------------Går att göra till en for loop
        ArrayList<String> formattedIngredients = new ArrayList<>();

        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                formattedIngredients.add(ingredient.toString() + "\n");
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

    public double getTotalCostOfRecipe (ArrayList<Ingredient> ingredients) {
        double sumOfRecipe = 0;
        for (Ingredient ingredient : ingredients) {
            sumOfRecipe += ingredient.getCostOfIngredient();
        }
        return sumOfRecipe;
    }

    public void setRecipeSouthPanel(RecipeSouthPanel recipeSouthPanel) {
        this.recipeSouthPanel = recipeSouthPanel;
    }

    public void setRecipeNorthPanel(RecipeNorthPanel recipeNorthPanel) {
        this.recipeNorthPanel = recipeNorthPanel;
    }
}