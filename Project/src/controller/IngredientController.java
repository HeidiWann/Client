package controller;

import model.Ingredient;

import java.util.ArrayList;

public class IngredientController {
    private ArrayList<Ingredient> ingredients;

    public IngredientController(){
        ingredients=new ArrayList<>();

        testMethod();
    }

    private void testMethod() {
        for (int i=0; i<4;i++){
            ingredients.add(new Ingredient("TestData"+i+1,3));
        }

        ingredients.add(new Ingredient("RÅ fågel", 3));
        ingredients.add(new Ingredient("mjölk", 3));
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Ingredient getIngredient(int index) {
        return ingredients.get(index);
    }

}
