package model;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Recipe {
    private String instructions;
    private Image imageOfRecipe;
    private Food dish;
    private String recipeName;
    private boolean isPrivate;

    public Recipe(String instructions, Image imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
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

    public Node getImageOfRecipe() {
        return new ImageView(imageOfRecipe);
    }

    public void setImageOfRecipe(Image imageOfRecipe) {
        this.imageOfRecipe = imageOfRecipe;
    }    public void setImageOfRecipe(ImageView imageOfRecipe) {
        this.imageOfRecipe = imageOfRecipe.getImage();
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
