package view.recipeCreationStage;

import controller.AddIngredientGUIController;
import controller.MainGUIController;
import controller.RecipeCreationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.GetGUIController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecipeCreationBottomMiddleLayer implements Initializable {
    @FXML
    private Button addIngredient;
    @FXML
    private Button removeIngredients;
    @FXML
    private ListView<String> listOfaddedIngredients;

    private RecipeCreationController recipeCreationController;
    private ObservableList<String> listOfIngredients;
    private ObservableList<String> listOfMeasurments;

    public RecipeCreationBottomMiddleLayer() {
        recipeCreationController = GetGUIController.getRecipeCreationController();
        recipeCreationController.setRecipeCreationBottomMiddleLayer(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addIngredient.setOnAction(new ButtonHandler());
        removeIngredients.setOnAction(new ButtonHandler());

        listOfIngredients = FXCollections.observableArrayList();
        listOfaddedIngredients.setItems(listOfIngredients);

//        listOfMeasurments = FXCollections.observableArrayList();
//        setListOfMeasurments();
//        ingredientMeasurementComboBox.setItems(listOfMeasurments);
    }

//    public void setListOfMeasurments() {
//        listOfMeasurments.addAll(recipeCreationController.getEveryMeasurement());
//    }

    public ArrayList<String> getIngredients() {
        ArrayList<String> listToReturn = new ArrayList<>(listOfIngredients);
        return listToReturn;
    }

    public void addIngredientToList(String ingredientName,String ingredientCost,String amountOfIngredient,String measurement) {
       /*TODO kod som fanns innan men är nu omskrivet @jansson
        String ingredientName = ingredientNameField.getText();
        String ingredientCost = ingredientCostField.getText();
        String amountOfIngredient = ingredientAmountField.getText();
        String measurement = ingredientMeasurementComboBox.getSelectionModel().getSelectedItem();

        */

        if (ingredientName.equals("") || ingredientCost.equals("") || amountOfIngredient.equals("") || measurement.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fyll i alla rutor");
            alert.setHeaderText(null);
            alert.setContentText("Alla rutor är inte fyllda. Vänligen skriv något i alla rutor och försök igen");
            alert.showAndWait();
        } else {
            String ingredientToAdd = ingredientName + " | " + ingredientCost + " | " + amountOfIngredient + " | " + measurement + " | ";
            listOfIngredients.add(ingredientToAdd);
//            resetTextFields();
        }
    }

//    private void resetTextFields() {
//        ingredientAmountField.clear();
//        ingredientNameField.clear();
//        ingredientCostField.clear();
//    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();
            if (clickedButton.getText().equals("Lägg till ingrediens")) {
                // addIngredientToList();//TODO denna funakr men bortkopplad @jansson
                //TODo här ska @jansson fönster starta

                //TODO test för att öppna fönstret (kan/ska tas bort när fönstret funkar och bör föras in här ovan) @jansson
                AddIngredientGUIController test=new AddIngredientGUIController();
                test.openAddIngredientWindow();

            } else if(clickedButton.getText().equals("Ta bort alla ingredienser")) {
                listOfIngredients.clear();
            }
        }
    }
}
