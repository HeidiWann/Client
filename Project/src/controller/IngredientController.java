package controller;

import model.Ingredient;

import java.util.ArrayList;
import java.util.HashMap;

public class IngredientController {
    HashMap<String, Ingredient> ingredientHashMap;

    public IngredientController(){
        ingredientHashMap = new HashMap<>();
    }

    public ArrayList<Ingredient> getIngredients() {
        return new ArrayList<>(ingredientHashMap.values());
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            String key = ingredient.toString2();
            ingredientHashMap.put(key, ingredient);
        }
    }

    public Ingredient getIngredient(String key) {
        return ingredientHashMap.get(key);
    }

}
