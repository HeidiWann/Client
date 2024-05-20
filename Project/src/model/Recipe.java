package model;


import javafx.scene.image.ImageView;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable { // --------------------------------------------------------------------------------Klassen har ingen author
    @Serial
    private static final long serialVersionUID = 111222333L;
    private int recipeID;
    private String author;
    private String instructions;
    private transient ImageView imageViewOfRecipe;
    private byte[] imageOfRecipe;
    private Food dish;
    private String recipeName;

    public Recipe(String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        this(author, instructions, (byte[]) null, ingredients, nameOfFood, typeOfFood);
        this.imageViewOfRecipe = imageOfRecipe;
    }
    public Recipe(String author, String instructions, byte[] imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Food newDish = new Food(nameOfFood, typeOfFood, ingredients);
        this.author = author;
        this.dish = newDish;
        this.instructions = instructions;
        this.imageOfRecipe = imageOfRecipe;
        this.recipeName = nameOfFood;
    }

    public Recipe(int recipeID, String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Food newDish = new Food(nameOfFood, typeOfFood, ingredients);
        this.recipeID = recipeID;
        this.author = author;
        this.dish = newDish;
        this.instructions = instructions;
        this.imageViewOfRecipe = imageOfRecipe;
        this.recipeName = nameOfFood;
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public ImageView getImageViewOfRecipe() {
        return imageViewOfRecipe;
    }

    public void setImageViewOfRecipe(ImageView imageOfRecipe) {
        this.imageViewOfRecipe = imageOfRecipe;
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

    public byte[] getImageOfRecipe() {
        return imageOfRecipe;
    }

    public void setImageOfRecipe(byte[] imageOfRecipeByte) {
        this.imageOfRecipe = imageOfRecipeByte;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
