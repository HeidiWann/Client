package controller;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
//import model.User;
import view.mainStage.CenterPanel;
import view.mainStage.SouthPanel;
import view.recipeStage.RecipeCenterPanel;
import view.recipeStage.RecipeMainScene;
import view.recipeStage.RecipeNorthPanel;
import view.recipeStage.RecipeSouthPanel;

import java.util.ArrayList;

public class MainGUIController {

    private RecipeGUIController recipeGUIController;
    private UserGUIController userGUIController;
    private ClientController clientController;
    private RecipeController recipeController;
    private SouthPanel southPanel;
    private CenterPanel centerPanel;
    private ArrayList<FoodCategory> filters = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public MainGUIController() {
        this.recipeGUIController = GetGUIController.getrecipeGUIController();
        this.userGUIController = GetGUIController.getUserGUIController();
    }



    public void updateListOfRecipes(ArrayList<Recipe> recipes) {
        southPanel.addRecipes(recipes);
    }

    public User getUserName(User userName) {
        return userName;
    }

    public RecipeGUIController getRecipeGUIController() {
        return recipeGUIController;
    }

    public UserGUIController getUserGUIController() {
        return userGUIController;
    }

    public void searchForRecipe(String wordToSearchFor) {
        ArrayList<Recipe> searchedRecipes = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).getRecipeName().contains(wordToSearchFor)) {
                searchedRecipes.add(recipes.get(i));
            }
        }
        southPanel.addRecipes(searchedRecipes);
    }

    public void addFilter(FoodCategory foodCategory) {
        if (filters.contains(foodCategory)) {
            filters.remove(foodCategory);
        } else {
            filters.add(foodCategory);
        }

        if (filters.isEmpty()) {
            southPanel.addRecipes(recipes);
        } else {
            updateRecipeList();
        }
        setFormattedCategories();
    }

    public void setFormattedCategories() {
        StringBuilder formattedCategories = new StringBuilder();
        if (filters != null) {
            for (FoodCategory category : filters) {
                formattedCategories.append(category.toString()).append(" | ");
            }
        }
        centerPanel.setCurrentCategories(String.valueOf(formattedCategories));
    }

    public void updateRecipeList() {
        ArrayList<Recipe> filteredRecipeList = new ArrayList<>();

        for (int i = 0; i < recipes.size(); i++) {
            boolean recipeContainedCategory = true;
            for (int j = 0; j < recipes.get(i).getDish().getTypeOfFood().size(); j++) {
                for (int k = 0; k < filters.size(); k++) {
                    if (recipes.get(i).getDish().getTypeOfFood().get(j).equals(filters.get(k)) && recipeContainedCategory) {
                        filteredRecipeList.add(recipes.get((i)));
                        recipeContainedCategory = false;
                    } else if (!recipeContainedCategory) {
                        break;
                    }
                }
            }
        }

        southPanel.addRecipes(filteredRecipeList);
    }

    public void insertRecipes() {
        ArrayList<Ingredient> salmonIngredients = new ArrayList<>();
        ArrayList<FoodCategory> salmonCategory = new ArrayList<>();
        String salmonInstructions;

        salmonIngredients.add(new Ingredient("Lax", 30, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Ris", 25, 1, Measurement.DL));
        salmonIngredients.add(new Ingredient("Broccoli", 15, 100, Measurement.G));
        salmonIngredients.add(new Ingredient("Champinjoner", 5, 5, Measurement.ST));
        salmonIngredients.add(new Ingredient("Kikomans svampsoja", 30, 5, Measurement.CL));
        salmonCategory.add(FoodCategory.Fish);
        salmonInstructions = "Börja första med att skära alla grönsakerna. \n" +
                "Börja sedan koka riset och sätt en timer på 10 minuter. \n" +
                "Börja steka laxen på hög värme \n " +
                "Börja steka grönsakerna direkt efter laxen lagts på stekpannen. Använda rikligt med olja. \n" +
                "Häll i soja i grönsakerna och sänk värmen på laxen till låg när det är tre minuter kvar på klockan tills riset är klart.";

        Image salmonTemp = new Image("/view/SalmonImage.jpg");
        ImageView salmonImage = new ImageView(salmonTemp);
        salmonImage.setFitWidth(50);
        salmonImage.setFitHeight(50);

        recipes.add(new Recipe(salmonInstructions, salmonImage, salmonIngredients, "Lax med ris och stekta grönsaker",salmonCategory));

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
        recipes.add(new Recipe(oreoShakeInstructions, oreoImage, oreoShakeIngredients, "Oreo Milkshake", oreoShakeCategory));

        ArrayList<Ingredient> tacoIngredient = new ArrayList<>();
        ArrayList<FoodCategory> tacoCategory = new ArrayList<>();
        String tacoInstructions;
        tacoIngredient.add(new Ingredient("Sallad", 15, 1, Measurement.ST));
        tacoIngredient.add(new Ingredient("Tortilla bröd", 20,8,Measurement.ST));
        tacoIngredient.add(new Ingredient("Salsa", 15, 2, Measurement.DL));
        tacoIngredient.add(new Ingredient("Lök", 3,1,Measurement.ST));
        tacoIngredient.add(new Ingredient("Tomat",5,3,Measurement.ST));
        tacoIngredient.add(new Ingredient("Annanas", 15,1, Measurement.Burk));
        tacoCategory.add(FoodCategory.Cow);
        tacoInstructions = "Skär alla grönsaker. Stek sedan köttfärsen. När köttfärsen fått färg häller du i salsan. " +
                "Gör sedan din tacos med din favoritsås";
        Image tacoTemp = new Image("/view/Tacos.jpg");
        ImageView tacoImage = new ImageView(tacoTemp);
        tacoImage.setFitWidth(50);
        tacoImage.setFitHeight(50);
        recipes.add(new Recipe(tacoInstructions, tacoImage, tacoIngredient, "Tacos", tacoCategory));

        southPanel.addRecipes(recipes);
    }

    public void setSouthPanel(SouthPanel southPanel) {
        this.southPanel = southPanel;
        insertRecipes();
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }
}