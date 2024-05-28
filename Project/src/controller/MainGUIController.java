package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.*;
import view.mainStage.WestPanel;
import view.recipeCreationStage.RecipeCreationStage;
import view.addIngredientStage.AddIngredientStage;

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
    private RecipeController recipeController;
    private UserController userController;
    private SouthPanel southPanel;
    private CenterPanel centerPanel;
    private NorthPanel northPanel;


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
        ArrayList<Recipe> recipes = collectRecipes();

        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getRecipeName().toLowerCase().contains(wordToSearchFor.toLowerCase()) || recipes.get(i).getDish().getNameOfFood().toLowerCase().contains(wordToSearchFor.toLowerCase())) {
                searchedRecipes.add(recipes.get(i));
            }
        }
        southPanel.addRecipes(searchedRecipes);
    }

    public ArrayList<Recipe> collectRecipes() {
        return recipeController.getRecipes();
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
        ArrayList<FoodCategory> filters = recipeController.getRecipeFilters();
        if (filters.contains(foodCategory)) {
            recipeController.removeFilter(foodCategory);
        } else {
            recipeController.addFilter(foodCategory);
        }

        if (filters.isEmpty()) {
            southPanel.addRecipes(collectRecipes());
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
        ArrayList<FoodCategory> filters = recipeController.getRecipeFilters();
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
        ArrayList<Recipe> recipes = collectRecipes();
        ArrayList<FoodCategory> filters = recipeController.getRecipeFilters();

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

    public void setLoginStatus(boolean loggedIn) {
        northPanel.setLoggedInStatus(loggedIn);
    }

    public void setRecipes(Recipe[] recipes) {
        ArrayList<Recipe> recipesToAdd = new ArrayList<>(Arrays.asList(recipes));

        southPanel.addRecipes(recipesToAdd);
    }

    public void logOut() {
        userController.logOut();
        setLoginStatus(false);
    }

    public UserController getUserController() {
        return userController;
    }

    public RecipeController getRecipeController() {
        return recipeController;
    }

    @FXML
    public boolean openRecipeCreationScene() throws IOException {
        if (userController.getLoggedInUser() == null) {
            return false;
        }
        Stage recipeCreationStage = new Stage();
        recipeCreationStage.setTitle("Create new Recipe");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/recipeCreationStage/RecipeCreation.fxml"));
        recipeCreationStage.setScene(new Scene(loader.load()));
        recipeCreationStage.show();
        //Pane recipeCreationPane = loader.load();

//        RecipeCreationStage recipeCreationController = loader.getController();

        //borderPane.setCenter(recipeCreationPane);
        return true;
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

    public void setRecipeController(RecipeController recipeController) {
        this.recipeController = recipeController;
    }

    public SouthPanel getSouthPanel() {
        return southPanel;
    }

    public void setNorthPanel(NorthPanel northPanel) {
        this.northPanel = northPanel;
    }

    public void setCenterPanel(CenterPanel centerPanel) {
        this.centerPanel = centerPanel;
    }

    public void setSouthPanel(SouthPanel southPanel) {
        this.southPanel = southPanel;
    }
}