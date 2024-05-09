package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;
import view.RecipeCreationController;
import view.mainStage.CenterPanel;
import view.mainStage.NorthPanel;
import view.mainStage.SouthPanel;
import view.userStage.UserMainScene;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class handles the communication and manipulation of data that is associated to the GUI that is to be shown
 * in the "home page" of the application
 *
 * @author Anton Persson
 */
public class MainGUIController {
    private final RecipeGUIController recipeGUIController;
    private UserController userController;
    private SouthPanel southPanel;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;
    /**
     * List of categories used to filter the recipes.
     */
    private ArrayList<FoodCategory> filters = new ArrayList<>();
    private ArrayList<Recipe> recipes = new ArrayList<>();

    public MainGUIController() {
        this.recipeGUIController = GetGUIController.getrecipeGUIController();
    }

    public void updateListOfRecipes(ArrayList<Recipe> recipes) {
        southPanel.addRecipes(recipes);
    }

    public RecipeGUIController getRecipeGUIController() {
        return recipeGUIController;
    }


    /**
     * This method loops through the list of recipes to find recipes that contains the characters entered by the
     * user from {@link CenterPanel}. If the characters are inside the recipes name, the recipe gets added to a new list.
     * Lastly, a method in the center panel wth the list containing recipes matching the characters from the GUI.
     *
     * @param wordToSearchFor A String containing the characters enetered by the user in the GUI
     * @author Anton Persson
     */
    public void searchForRecipe(String wordToSearchFor) {
        ArrayList<Recipe> searchedRecipes = new ArrayList<>();
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getRecipeName().toLowerCase().contains(wordToSearchFor.toLowerCase()) || recipes.get(i).getDish().getNameOfFood().toLowerCase().contains(wordToSearchFor.toLowerCase())) {
                searchedRecipes.add(recipes.get(i));
            }
        }
        southPanel.addRecipes(searchedRecipes);
    }

    /**
     * This method adds or alternatively removes categories from the list containing categories. If the category
     * already is in the list, it gets removed, if it's not in the list, it gets added. If the list of categories is
     * empty after removing a category, a method is called to add every recipe to the list in {@link SouthPanel}.
     * If the list isn't empty, a method is called that will update the list based on the categories in the list.
     * Lastly, a method is called that formats the categories into a String.
     *
     * @param foodCategory The category to add of the type {@link FoodCategory}
     * @author Anton Persson
     */
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

    /**
     * This method formats the categories to a string to easily insert it into the {@link CenterPanel}
     *
     * @author Anton Persson
     */
    public void setFormattedCategories() {
        StringBuilder formattedCategories = new StringBuilder();
        if (filters != null) {
            for (FoodCategory category : filters) {
                formattedCategories.append(category.toString()).append(" | ");
            }
        }
        centerPanel.setCurrentCategories(String.valueOf(formattedCategories));
    }

    /**
     * This method filters the recipes after the categories. If a recipe has a category in the category list, it gets
     * added to a new {@link ArrayList}. This ArrayList is then used to update the list in the {@link SouthPanel}
     * <p>
     * The first loop loops through the recipes. Each recipe has a boolean which will be set to false if a recipe
     * contains a category in the category list. This is done to make sure that the same category is not added twice in
     * case the recipe has two categories that matches.
     * <p>
     * The second loop loops through each category in the current recipe.
     * <p>
     * The last loop loops through the list of categories. If the category in the recipe matches any of the categories
     * in the list of categories and a category has not matched yet, the recipe gets added to the list. If a
     * category has been found, the loop breaks.
     *
     * @author Anton Persson
     */
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

        southPanel.addRecipes(recipes);
    }

    public void setSouthPanel(SouthPanel southPanel) {
        this.southPanel = southPanel;
        insertRecipes();
    }

    public void setLoginStatus(boolean loggedIn) {
        northPanel.setLoggedInStatus(loggedIn);
    }

    public void setNorthPanel(NorthPanel northPanel) {
        this.northPanel = northPanel;
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public void setRecipes(Recipe[] recipes) {
        this.recipes = new ArrayList<>(Arrays.asList(recipes));
        insertRecipes();
    }

    public void logOut() {
        userController.logOut();
        setLoginStatus(false);
    }

    @FXML
    public void openRecipeCreationScene() throws IOException {
        Stage recipeCreationStage = new Stage();
        recipeCreationStage.setTitle("Create new Recipe");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/recipeStage/RecipeCreation.fxml"));
        recipeCreationStage.setScene(new Scene(loader.load()));
        recipeCreationStage.show();
        //Pane recipeCreationPane = loader.load();

        RecipeCreationController recipeCreationController = loader.getController();
        recipeCreationController.setMainGUIController(this);

        //borderPane.setCenter(recipeCreationPane);
    }

    public void startLogInWindow() {
        if (userController.getLoggedInUser() == null) {
            UserMainScene userMainScene = new UserMainScene();
            userMainScene.createUserWindow();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Redan inloggad");
            alert.setHeaderText(null);
            alert.setContentText("Du är redan inloggad");
            alert.showAndWait();
        }

    }

    public void setUserController(UserController userController) {
        this.userController = userController;
    }
}