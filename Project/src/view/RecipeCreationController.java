package view;

import controller.MainGUIController;
import controller.RecipeGUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Ingredient;
import model.Measurement;

import java.io.File;
import java.util.ArrayList;

public class RecipeCreationController {
    public TextField ingredientNameField1;
    public TextField ingredientCostField1;
    public TextField ingredientAmountField1;
    public ComboBox ingredientMeasurementComboBox1;
    @FXML
    public Button createnewRecipeButton;
    @FXML
    private TextField recipeNameField;
    @FXML
    private TextArea instructionsArea;
    @FXML
    private ImageView recipeImageView;
    @FXML
    private TextField ingredientsField;

    private File selectedImageFile;
    private RecipeGUIController recipeGUIController;
    private MainGUIController mainGUIController;

    public void setRecipeGUIController(RecipeGUIController recipeGUIController) {
        this.recipeGUIController = recipeGUIController;
    }

    @FXML
    private void chooseImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        selectedImageFile = fileChooser.showOpenDialog(null);

        if (selectedImageFile != null) {
            recipeImageView.setImage(new Image(selectedImageFile.toURI().toString()));
        }
    }

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

        handleClicks();
        chooseImage();
        clearFields();
    }

    private void clearFields() {
        recipeNameField.clear();
        instructionsArea.clear();
        ingredientAmountField1.clear();
        ingredientCostField1.clear();
        ingredientAmountField1.clear();
        ingredientMeasurementComboBox1.getSelectionModel().clearSelection();
    }

    public void setMainGUIController(MainGUIController mainGUIController) {
        this.mainGUIController = mainGUIController;
    }

    public void handleClicks() {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createnewRecipeButton.setText("Create new recipe selected");
            }
        };

        createnewRecipeButton.setOnAction(event);
    }
}