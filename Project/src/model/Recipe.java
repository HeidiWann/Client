package model;



import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Recipe {
    private String instructions;
    private ImageView imageOfRecipe;
    private Food dish;
    private String recipeName;
    private boolean isPrivate;

    public Recipe(String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Food newDish = new Food(nameOfFood, typeOfFood, ingredients);
        this.dish = newDish;
        this.instructions = instructions;
        this.imageOfRecipe = imageOfRecipe;
        this.recipeName = nameOfFood;
        this.isPrivate = true;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ImageView getImageOfRecipe() {
        return imageOfRecipe;
    }

    public void setImageOfRecipe(ImageView imageOfRecipe) {
        this.imageOfRecipe = imageOfRecipe;
    }

    public Food getDish() {
        return dish;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
