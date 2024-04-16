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
                /*
        ingredients = new ArrayList<>();
        foodCategories = new ArrayList<>();
        recipes = new ArrayList<>();

        ingredients.add(new Ingredient("Nässlor", 10, 10));
        foodCategories.add(FoodCategory.Vegan);
        testing = new Image(new File("C:\\Users\\2002a\\Pictures\\Screenshots\\Screenshot 2024-04-02 173350.png").toURI().toString());

        for (int i = 0; i < 15; i++) {
            createNewRecipe("Heidis Nässlesoppa", testing, ingredients, "NässleSoppa", foodCategories);
            System.out.print(i + ", ");
        }
        //for (Recipe recipe : recipes) {System.out.println(recipe.getRecipeName());}

        SouthPanel southPanel = new SouthPanel();
        ArrayList<String> testing1 = new ArrayList<>();
        ArrayList<Image> testing2 = new ArrayList<>();
        for (Recipe recipe : recipes) {testing1.add(recipe.getRecipeName());}
        for (Recipe recipe : recipes) { testing2.add(recipe.getImageOfRecipe());}

        //southPanel.insertRecipesToMap(testing1.toArray(new String[0]), testing2.toArray(new Image[0]));

         */
    }
    public void createNewRecipe (String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(instructions, imageOfRecipe, ingredients, nameOfFood,typeOfFood);
        recipes.add(recipe);
    }


    /*public void createRecipe(Recipe recipe) {
    }*/
}
