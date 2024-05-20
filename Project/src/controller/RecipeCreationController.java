package controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import model.*;
import view.recipeCreationStage.RecipeCreationBottomLayer;
import view.recipeCreationStage.RecipeCreationBottomMiddleLayer;
import view.recipeCreationStage.RecipeCreationTopMiddleLayer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class RecipeCreationController {
    private RecipeCreationBottomLayer recipeCreationBottomLayer;
    private RecipeCreationBottomMiddleLayer recipeCreationBottomMiddleLayer;
    private RecipeCreationTopMiddleLayer recipeCreationTopMiddleLayer;
    private RecipeController recipeController;
    private User currentUser;
    private ConnectionController connectionController;

    public RecipeCreationController() {

    }

    public void closeWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }



    public boolean createRecipe() {
        String author = GetGUIController.getGuiController().getUserController().getLoggedInUser().getUserName();
        String recipeName = recipeCreationTopMiddleLayer.getRecipeName();
        String recipeInstruction = recipeCreationTopMiddleLayer.getInstruction();
        ArrayList<Ingredient> ingredients = convertToIngredientArray(recipeCreationBottomMiddleLayer.getIngredients());
        ImageView imageOfRecipe = recipeCreationBottomLayer.getImageOfRecipe();
        ArrayList<FoodCategory> categories = convertToFoodCategory(recipeCreationBottomLayer.getListOfCategories());

        if (recipeName.isEmpty() || recipeInstruction.isEmpty() || ingredients.isEmpty() || imageOfRecipe == null || categories.isEmpty()) {
            return false;
        } else {
            createNewRecipe(author, recipeInstruction, imageOfRecipe, ingredients, recipeName, categories);
        }
        return true;
    }
    public void createNewRecipe(String author, String instructions, ImageView imageOfRecipe, ArrayList<Ingredient> ingredients, String nameOfFood, ArrayList<FoodCategory> typeOfFood) {
        Recipe recipe = new Recipe(author, instructions, imageOfRecipe, ingredients, nameOfFood, typeOfFood);
        recipeController.addRecipeToArray(recipe);
        recipeController.updateListOfRecipes();
        connectionController.createNewRecipe(recipe);

    }
    public Recipe convertRecipe(Recipe recipe) {
        ImageView imageview = byteArrayToImageView(recipe.getImageOfRecipe());
        Recipe convertedRecipe = new Recipe(recipe.getAuthor(), recipe.getInstructions(), imageview, recipe.getDish().getIngredients(), recipe.getRecipeName(), recipe.getDish().getTypeOfFood());
        if(recipe.getImageOfRecipe() == null){
            System.out.println("Fungerar inte ens här");
        }
        return convertedRecipe;


    }

    public ArrayList<FoodCategory> convertToFoodCategory (ArrayList<String> listToConvert) {
        ArrayList<FoodCategory> listOfCategories = new ArrayList<>();
        if (listToConvert != null) {
            for (int i = 0; i < listToConvert.size(); i++) {
                listOfCategories.add(FoodCategory.valueOf(listToConvert.get(i)));
            }
        }
        return listOfCategories;
    }

    public ArrayList<Ingredient> convertToIngredientArray (ArrayList<String> listToConvert) {
        ArrayList<Ingredient> listOfCategories = new ArrayList<>();
        String ingredientName = "";
        String ingredientCost = "";
        String amountOfIngredient = "";
        String measurement = "";

        for (int i = 0; i < listToConvert.size(); i++) {
            String[] splitList = listToConvert.get(i).split("\\|");
            for (int j = 0; j < 4; j++) {
                String currentWord = splitList[j].trim();
                if (j == 0) {
                    ingredientName = currentWord;
                } else if (j == 1) {
                    ingredientCost = currentWord;
                } else if (j ==2) {
                    amountOfIngredient = currentWord;
                } else {
                    measurement = currentWord;
                    listOfCategories.add(new Ingredient(ingredientName, Double.parseDouble(ingredientCost), Double.parseDouble(amountOfIngredient), Measurement.valueOf(measurement)));
                }
            }
        }
        return listOfCategories;
    }

    public ArrayList<String> getEveryCategory() {
        return recipeController.gatherFoodCategories();
    }

    public ArrayList<String> getEveryMeasurement() {
        return recipeController.gatherMeasurements();
    }

    public void setRecipeCreationBottomLayer(RecipeCreationBottomLayer recipeCreationBottomLayer) {
        this.recipeCreationBottomLayer = recipeCreationBottomLayer;
    }

    public void setRecipeCreationBottomMiddleLayer(RecipeCreationBottomMiddleLayer recipeCreationBottomMiddleLayer) {
        this.recipeCreationBottomMiddleLayer = recipeCreationBottomMiddleLayer;
    }

    public void setRecipeCreationTopMiddleLayer(RecipeCreationTopMiddleLayer recipeCreationTopMiddleLayer) {
        this.recipeCreationTopMiddleLayer = recipeCreationTopMiddleLayer;
    }

    public void setRecipeController(RecipeController recipeController) {
        this.recipeController = recipeController;
    }
    public void setConnectionController(ConnectionController connectionController){
        this.connectionController = connectionController;

    }
    public static ImageView byteArrayToImageView(byte[] byteArray) {
    ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
    Image image = new Image(inputStream);
    ImageView imageView = new ImageView(image);
    return imageView;
    }


    /*
    @FXML
    public void createOwnRecipe() {
        String recipeName = recipeNameField.getText();
        String instructions = instructionsArea.getText();

        String ingredientName1 = ingredientNameField1.getText();
        double ingredientCost = Double.parseDouble(ingredientCostField1.getText());
        double ingredientAmount = Double.parseDouble(ingredientAmountField1.getText());
        String measurement = (String) ingredientMeasurementComboBox1.getValue();
        Measurement ingredientMeasurement = Measurement.valueOf(measurement);

        Ingredient ingredient1 = new Ingredient(ingredientName1, ingredientCost, ingredientAmount, ingredientMeasurement);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);

//        handleClicks();
        chooseImage();
        clearFields();
    }

     */

    /*
    private void clearFields() {
        recipeNameField.clear();
        instructionsArea.clear();
        ingredientAmountField1.clear();
        ingredientCostField1.clear();
        ingredientAmountField1.clear();
        ingredientMeasurementComboBox1.getSelectionModel().clearSelection();
    }

     */

//    public void handleClicks() {
//        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                createnewRecipeButton.setText("Create new recipe selected");
//            }
//        };
//
//        createnewRecipeButton.setOnAction(event);
//    }
}
