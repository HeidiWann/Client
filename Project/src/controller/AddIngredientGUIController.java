package controller;


import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.GetGUIController;
import model.Ingredient;
import model.Measurement;
import view.addIngredientStage.AddIngredientCenterPanel;
import view.addIngredientStage.AddIngredientSouthPanel;
import view.addIngredientStage.AddIngredientStage;

import java.util.ArrayList;
import java.util.Locale;

/**
 * This class handels most of the logic behind the "add a new ingredient"-window.
 *
 * @author Anton Jansson
 */
public class AddIngredientGUIController {
    private static AddIngredientCenterPanel addIngredientCenterPanel;
    private AddIngredientSouthPanel addIngredientSouthPanel;
    private Ingredient selectedIngredient;


    /**
     * Method that starts and shows the window
     * @author Anton Jansson
     */
    public void openAddIngredientWindow() {
        AddIngredientStage addIngredientStage = new AddIngredientStage();
        Stage newStage = new Stage();
        newStage.setTitle("Lägg till ingrediens");
        addIngredientStage.start(newStage);
    }

    /**
     * Method that searches for ingredient names that contains the searchText and returns only names that have a match
     * @param searchedText This is the search word that the user gave
     * @return matchedIngredients Is an ArrayList containing the result of the search
     * @author Anton Jansson
     */
    public ArrayList<Ingredient> search(String searchedText) {
        ArrayList<Ingredient> matchedIngredients = new ArrayList<>();
        ArrayList<Ingredient> ingredients = fetchAllIngredients();
        for (Ingredient ingredient : ingredients) {
            String ingredientName = ingredient.getIngredientName().toLowerCase();
            if (ingredientName.contains(searchedText.toLowerCase())) {
                matchedIngredients.add(ingredient);
            }
        }
        return matchedIngredients;
    }

    public ArrayList<Ingredient> fetchAllIngredients() {
        ArrayList<Ingredient> ingredients = GetGUIController.getIngredientController().getIngredients();
        return ingredients;
    }

    public ArrayList<String> fetchAllIngredientsAsStrings() {
        ArrayList<Ingredient> ingredients = GetGUIController.getIngredientController().getIngredients();
        ArrayList<String> newListOfIngredients = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            newListOfIngredients.add(ingredient.toString2());
        }
        return newListOfIngredients;
    }

    /**
     * Method that adds the chosen ingredient to the create new recipe logic
     * @param amount The amount of how much of an ingredient is needed.
     * @param measurment The intended measurement for measuring the amount
     */
    public void addIngredient(String amount, String measurment) {
        String[] dataFromChosenIngredient = fetchDataFromChosenIngredient();
        if (dataFromChosenIngredient[0] == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vänligen välj en en råvara från listan");
            alert.setHeaderText(null);
            alert.setContentText("En råvara måste väljas. Vänligen välj en från listan och försök igen");
            alert.showAndWait();
        } else {
            String ingredientName = dataFromChosenIngredient[0];
            String ingredientCost = dataFromChosenIngredient[1];
            ingredientCost = getCorrectPirce(ingredientCost, amount, measurment);
            GetGUIController.getRecipeCreationController().getRecipeCreationBottomMiddleLayer().addIngredientToList(ingredientName, ingredientCost, amount, measurment);
            selectedIngredient=null;

            ArrayList<Ingredient> clearList=new ArrayList<>();
            getAddIngredientCenterPanel().addIngredientsToTextField(clearList);
        }
    }

    /**
     * This method returns the cost of an ingredient after its calculated for the amount and measurment as a String
     * @param oldCostOfIngredient
     * @param amountOfIngredient
     * @param measurement
     * @return
     * @author Anton Persson
     */
    public String getCorrectPirce (String oldCostOfIngredient, String amountOfIngredient, String measurement) {
        double newCostOfIngredient = Double.parseDouble(oldCostOfIngredient);
        double ingredientAmount = Double.parseDouble(amountOfIngredient);

        if (measurement.equals(String.valueOf(Measurement.G)) || measurement.equals(String.valueOf(Measurement.ML)) || measurement.equals(String.valueOf(Measurement.KRM))) {
            newCostOfIngredient = (newCostOfIngredient / 1000) * ingredientAmount;
        } else if (measurement.equals(String.valueOf(Measurement.KG)) || measurement.equals(String.valueOf(Measurement.L))) {
            newCostOfIngredient = newCostOfIngredient * ingredientAmount;
        } else if (measurement.equals(String.valueOf(Measurement.DL))) {
            newCostOfIngredient = (newCostOfIngredient / 10) * ingredientAmount;
        } else if (measurement.equals(String.valueOf(Measurement.CL))) {
            newCostOfIngredient = (newCostOfIngredient / 100) * ingredientAmount;
        } else if (measurement.equals(String.valueOf(Measurement.TSK))) {
            newCostOfIngredient = (newCostOfIngredient * 0.005) * ingredientAmount;
        } else if (measurement.equals(String.valueOf(Measurement.MSK))) {
            newCostOfIngredient = (newCostOfIngredient * 0.015) * ingredientAmount;
        }
        return String.valueOf(newCostOfIngredient);
    }

    private String[] fetchDataFromChosenIngredient() {
        // Ingredient chosenIngredient = getAddIngredientCenterPanel().getSelectedIngredient();
        Ingredient chosenIngredient = selectedIngredient;
        String[] chosenIngredientStrings = new String[2];
        if (selectedIngredient != null) {
            chosenIngredientStrings[0] = chosenIngredient.getIngredientName();
            chosenIngredientStrings[1] = String.valueOf(chosenIngredient.getIngredientCost());
        }
        return chosenIngredientStrings;
    }

    private static AddIngredientCenterPanel getAddIngredientCenterPanel() {
        if (addIngredientCenterPanel == null) {
            addIngredientCenterPanel = new AddIngredientCenterPanel();
        }
        return addIngredientCenterPanel;
    }

    public void setSelectedIngredient(String key) {
        this.selectedIngredient = GetGUIController.getIngredientController().getIngredient(key);
    }
}
