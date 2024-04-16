package view.recipeStage;

import controller.MainGUIController;
import controller.RecipeController;
import controller.RecipeGUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import model.GetGUIController;


import java.net.URL;
import java.util.ResourceBundle;

public class RecipeCenterPanel implements Initializable {
    @FXML
    ImageView imageOfRecipe;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TextArea ingredientsInfo;
    private RecipeGUIController recipeGUIController;
    public RecipeCenterPanel(){
        this.recipeGUIController = GetGUIController.getrecipeGUIController();
        recipeGUIController.setRecipeCenterPanel(this);
        System.out.println("Nått centerpanelen");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initierade centerpanelen");
        scrollPane.setContent(ingredientsInfo);
    }

    public void insertText(String text) {
        ingredientsInfo.insertText(0, text);
    }
}
