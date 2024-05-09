package controller;

import javafx.scene.image.ImageView;
import model.FoodCategory;
import model.Ingredient;
import model.Recipe;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class RecipeController {
    private ArrayList<Recipe> recipeArray;
    private HashMap<Recipe, ConnectionController> recipeInformation;
    private HashMap<String, Recipe> recipes;

    public RecipeController() {

        recipeInformation = new HashMap<>();
        recipes = new HashMap<>();
    }

    public void createNewRecipe(String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(author, instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);
        recipeArray.add(recipe);
        recipes.put(nameOfFood, recipe); //bara ett exempel då det måste vara unikt och det är inte nameOfFood ville bara visa
    }



    public ArrayList<Recipe> getRecipes() {
        return recipeArray;
    }
    /*public HashMap<String, Recipe> getRecipes() { //om man vill använda HashMap istället för ArrayList
        return new HashMap<>(recipes);
    }*/

    public HashMap<Recipe, ConnectionController> getRecipeInformation() {
        return new HashMap<>(recipeInformation);
    }

    public void handleTheRecipes(Recipe recipeUpdate) {
        for (Recipe recipe : recipeArray) {
            if (recipe.getRecipeName().equals(recipeUpdate.getRecipeName())) {
                recipe.setInstructions(recipeUpdate.getInstructions());
                recipe.setImageOfRecipe(recipeUpdate.getImageOfRecipe()); //kan byggas ut mer vid behov om det bara ska användas HasMap får vi göra om denna
            }
        }
    }
}
