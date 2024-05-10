package controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;

import java.util.ArrayList;

public class RecipeController {
    private MainGUIController mainGUIController;
    private ArrayList<Recipe> recipes;
    private ArrayList<FoodCategory> recipeFilters;

    public RecipeController() {
        mainGUIController = GetGUIController.getGuiController();
        mainGUIController.setRecipeController(this);
        recipes = new ArrayList<>();
        recipeFilters = new ArrayList<>();
        insertRecipes();
    }

    public void createNewRecipe(String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(author, instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);
    }



    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * This method is just used to create sample data.
     *
     * @author Anton Persson
     */
    public void insertRecipes() {
        ArrayList<Ingredient> salmonIngredients = new ArrayList<>();
        ArrayList<FoodCategory> salmonCategory = new ArrayList<>();
        String salmonInstructions;

        salmonIngredients.add(new Ingredient("Lax", 30, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Ris", 25, 1, Measurement.DL));
        salmonIngredients.add(new Ingredient("Broccoli", 15, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Champinjoner", 5, 5, Measurement.ST));
        salmonIngredients.add(new Ingredient("Kikomans svampsoja", 30, 5, Measurement.CL));
        salmonCategory.add(FoodCategory.Fisk);
        salmonInstructions = "Börja första med att skära alla grönsakerna. \n" +
                "Börja sedan koka riset och sätt en timer på 10 minuter. \n" +
                "Börja steka laxen på hög värme \n " +
                "Börja steka grönsakerna direkt efter laxen lagts på stekpannen. Använda rikligt med olja. \n" +
                "Häll i soja i grönsakerna och sänk värmen på laxen till låg när det är tre minuter kvar på klockan tills riset är klart.";

        Image salmonTemp = new Image("/view/SalmonImage.jpg");
        ImageView salmonImage = new ImageView(salmonTemp);
        salmonImage.setFitWidth(50);
        salmonImage.setFitHeight(50);

        recipes.add(new Recipe("Anton", salmonInstructions, salmonImage, salmonIngredients, "Lax med ris och stekta grönsaker", salmonCategory));

        ArrayList<Ingredient> oreoShakeIngredients = new ArrayList<>();
        ArrayList<FoodCategory> oreoShakeCategory = new ArrayList<>();
        String oreoShakeInstructions;
        oreoShakeIngredients.add(new Ingredient("Oreo", 30, 8, Measurement.ST));
        oreoShakeIngredients.add(new Ingredient("Mjölk", 15, 2, Measurement.DL));
        oreoShakeIngredients.add(new Ingredient("Vanlij glass", 25, 3, Measurement.DL));
        oreoShakeCategory.add(FoodCategory.Vegetarian);
        oreoShakeInstructions = "Häll i alla ingredienser i en mixer och mixa tills allt krossats. Skulle den bli för tjock så häll bara i mer mjölk";
        Image oreoTemp = new Image("/view/oreoMilkShake.jpg");
        ImageView oreoImage = new ImageView(oreoTemp);
        oreoImage.setFitWidth(50);
        oreoImage.setFitHeight(50);
        recipes.add(new Recipe("Anton", oreoShakeInstructions, oreoImage, oreoShakeIngredients, "Oreo Milkshake", oreoShakeCategory));

        ArrayList<Ingredient> tacoIngredient = new ArrayList<>();
        ArrayList<FoodCategory> tacoCategory = new ArrayList<>();
        String tacoInstructions;
        tacoIngredient.add(new Ingredient("Sallad", 15, 1, Measurement.ST));
        tacoIngredient.add(new Ingredient("Tortilla bröd", 20, 8, Measurement.ST));
        tacoIngredient.add(new Ingredient("Salsa", 15, 2, Measurement.DL));
        tacoIngredient.add(new Ingredient("Lök", 3, 1, Measurement.ST));
        tacoIngredient.add(new Ingredient("Tomat", 5, 3, Measurement.ST));
        tacoIngredient.add(new Ingredient("Annanas", 15, 1, Measurement.Burk));
        tacoCategory.add(FoodCategory.Nöt);
        tacoInstructions = "Skär alla grönsaker. Stek sedan köttfärsen. När köttfärsen fått färg häller du i salsan. " +
                "Gör sedan din tacos med din favoritsås";
        Image tacoTemp = new Image("/view/Tacos.jpg");
        ImageView tacoImage = new ImageView(tacoTemp);
        tacoImage.setFitWidth(50);
        tacoImage.setFitHeight(50);
        recipes.add(new Recipe("Anton", tacoInstructions, tacoImage, tacoIngredient, "Tacos", tacoCategory));

        mainGUIController.updateListOfRecipes(recipes);
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
}
