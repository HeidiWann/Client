package controller;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeController {
    private MainGUIController mainGUIController;
    private ArrayList<Recipe> recipes;
    private ArrayList<FoodCategory> recipeFilters;

    public RecipeController() {
        mainGUIController = GetGUIController.getGuiController();
        mainGUIController.setRecipeController(this);
        recipes = new ArrayList<>();
        recipeFilters = new ArrayList<>();
        //insertRecipes();
        GetGUIController.getRecipeCreationController().setRecipeController(this);
    }

    public void addRecipeToArray(Recipe recipe){
        recipes.add(recipe);
    }

    public void updateListOfRecipes(){
        mainGUIController.updateListOfRecipes(recipes);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<String> gatherFoodCategories() {
        ArrayList<FoodCategory> foodCategories = new ArrayList<>(Arrays.asList(FoodCategory.values()));
        ArrayList<String> categoriesToReturn = new ArrayList<>();

        for (FoodCategory category : foodCategories) {
            categoriesToReturn.add(category.name());
        }
        return categoriesToReturn;
    }

    public ArrayList<FoodCategory> getRecipeFilters() {
        return recipeFilters;
    }

    public void addFilter(FoodCategory categoryToAdd) {
        recipeFilters.add(categoryToAdd);
    }

    public void removeFilter(FoodCategory foodCategory) {
        recipeFilters.remove(foodCategory);
    }

    public ArrayList<String> gatherMeasurements() {
        ArrayList<Measurement> measurements = new ArrayList<>(Arrays.asList(Measurement.values()));
        ArrayList<String> measurementsToReturn = new ArrayList<>();

        for (Measurement measurment : measurements) {
            measurementsToReturn.add(measurment.name());
        }
        return measurementsToReturn;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
        mainGUIController.updateListOfRecipes(this.recipes);
    }

    public void resetSearchAndFilters() {
        recipeFilters.clear();
        mainGUIController.updateListOfRecipes(recipes);
    }
}