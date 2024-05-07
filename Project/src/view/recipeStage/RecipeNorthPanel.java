package view.recipeStage;

import controller.RecipeGUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.GetGUIController;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeNorthPanel implements Initializable {
    @FXML
    private Label authorID;
    @FXML
    private Label nameOfRecipe;
    @FXML
    private Label categoryOfRecipe;

    public RecipeNorthPanel() {
        RecipeGUIController recipeGUIController = GetGUIController.getrecipeGUIController();
        recipeGUIController.setRecipeNorthPanel(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setHeaders(String nameOfAuthor, String nameOfRecipe, String categoryOfRecipe) {
        authorID.setText(nameOfAuthor);
        this.nameOfRecipe.setText(nameOfRecipe);
        this.categoryOfRecipe.setText("Categories : " + categoryOfRecipe);
    }
}
