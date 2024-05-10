package view.recipeCreationStage;

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
    private TextField ingredientNameField;
    @FXML
    private TextField ingredientCostField;
    @FXML
    private TextField ingredientAmountField;
    @FXML
    private ComboBox<String> ingredientMeasurementComboBox;
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

        listOfMeasurments = FXCollections.observableArrayList();
        setListOfMeasurments();
        ingredientMeasurementComboBox.setItems(listOfMeasurments);
    }

    public void setListOfMeasurments() {
        listOfMeasurments.addAll(recipeCreationController.getEveryMeasurement());
    }

    public ArrayList<String> getIngredients() {
        ArrayList<String> listToReturn = new ArrayList<>(listOfIngredients);
        return listToReturn;
    }

    public void addIngredientToList() {
        String recipeName = ingredientNameField.getText();
        String ingredientCost = ingredientCostField.getText();
        String amountOfIngredient = ingredientAmountField.getText();
        String measurement = ingredientMeasurementComboBox.getSelectionModel().getSelectedItem();

        if (recipeName.equals("") || ingredientCost.equals("") || amountOfIngredient.equals("") || measurement.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fyll i alla rutor");
            alert.setHeaderText(null);
            alert.setContentText("Alla rutor är inte fyllda. Vänligen skriv något i alla rutor och försök igen");
            alert.showAndWait();
        } else {
            String ingredientToAdd = recipeName + " | " + ingredientCost + " | " + amountOfIngredient + " | " + measurement + " | ";
            listOfIngredients.add(ingredientToAdd);
            resetTextFields();
        }
    }

    private void resetTextFields() {
        ingredientAmountField.clear();
        ingredientNameField.clear();
        ingredientCostField.clear();
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();
            if (clickedButton.getText().equals("Lägg till ingrediens")) {
                addIngredientToList();
            } else if(clickedButton.getText().equals("Ta bort alla ingredienser")) {
                listOfIngredients.clear();
            }
        }
    }
}
