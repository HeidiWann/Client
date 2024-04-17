package view.recipeStage;

import controller.MainGUIController;
import controller.RecipeController;
import controller.RecipeGUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.GetGUIController;


import java.net.URL;
import java.util.ArrayList;
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
        ingredientsInfo.setWrapText(true);
    }

    public void insertIngredientInfo(ArrayList<String> ingredientsInRecipe, String totalCostOfRecipe) {
        for (int i = 0; i < ingredientsInRecipe.size(); i++) {
            ingredientsInfo.insertText(0, ingredientsInRecipe.get(i));
        }
        ingredientsInfo.insertText(0, "Total cost of recipe is: " + totalCostOfRecipe + "kr \n");

    }

    public void insertImage(ImageView imageView) { // Bildformatet bestämmer hur mycket av bilden som visas.
        imageOfRecipe.setImage(imageView.getImage());
    }
}
