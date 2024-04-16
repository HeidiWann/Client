package controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.Recipe;

import java.util.ArrayList;

public class RecipeController {
    private ArrayList<Recipe> recipes;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<FoodCategory> fooCategories;
    private Image imageOfRecipe;
    public RecipeController() {
        recipes = new ArrayList<>();
    }
    public void createNewRecipe (String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(instructions, imageOfRecipe, ingredients, nameOfFood,typeOfFood);
        recipes.add(recipe);
    }


    /*public void createRecipe(Recipe recipe) {
    }*/
}
