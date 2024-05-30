package view.recipeCreationStage;

import controller.RecipeCreationController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.GetGUIController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the top middle layer of the recipe creation stage.
 * Manages the input fields for the recipe name and instructions.
 * Implements Initializable for JavaFX initialization.
 *
 * @Author: Salma Omar
 * @Author: Anton Persson
 */
 public class RecipeCreationTopMiddleLayer implements Initializable {
    @FXML
    private TextField recipeNameField;
    @FXML
    private TextArea instructionsArea;
    private RecipeCreationController recipeCreationController;

    public RecipeCreationTopMiddleLayer() {
        recipeCreationController = GetGUIController.getRecipeCreationController();
        recipeCreationController.setRecipeCreationTopMiddleLayer(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public String getRecipeName() {
        return recipeNameField.getText();
    }

    public String getInstruction() {
        return instructionsArea.getText();
    }
}
