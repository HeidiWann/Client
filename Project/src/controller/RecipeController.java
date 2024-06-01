package controller;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;

public class RecipeController {
    private MainGUIController mainGUIController;
    private ArrayList<Recipe> recipes;
    private ArrayList<FoodCategory> recipeFilters;

    /**
     * Initializes a new RecipeController, setting up the main GUI controller and recipe creation controller,
     * and initializing the lists for recipes and recipe filters.
     */
    public RecipeController() {
        mainGUIController = GetGUIController.getGuiController();
        mainGUIController.setRecipeController(this);
        recipes = new ArrayList<>();
        recipeFilters = new ArrayList<>();
        //insertRecipes();
        GetGUIController.getRecipeCreationController().setRecipeController(this);
    }

    /**
     * Adds a recipe to the list of recipes.
     *
     * @param recipe the recipe to add
     */
    public void addRecipeToArray(Recipe recipe){
        recipes.add(recipe);
    }

    /**
     * Updates the list of recipes in the main GUI controller.
     */
    public void updateListOfRecipes(){
        mainGUIController.updateListOfRecipes(recipes);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Gathers all food categories and returns their names as a list of strings.
     *
     * @return a list of food category names
     */
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

    /**
     * Adds a food category to the list of recipe filters.
     *
     * @param categoryToAdd the food category to add to the filters
     */
    public void addFilter(FoodCategory categoryToAdd) {
        recipeFilters.add(categoryToAdd);
    }

    /**
     * Removes a food category from the list of recipe filters.
     *
     * @param foodCategory the food category to remove from the filters
     */
    public void removeFilter(FoodCategory foodCategory) {
        recipeFilters.remove(foodCategory);
    }

    /**
     * Gathers all measurement types and returns their names as a list of strings.
     *
     * @return a list of measurement names
     */
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