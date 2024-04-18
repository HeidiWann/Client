package view.recipeStage;

import controller.MainGUIController;
import controller.RecipeGUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class's main purpose it to create a new Stage.
 * @author Anton Persson
 */
public class RecipeMainScene {
    private RecipeGUIController recipeGUIController;
    public RecipeMainScene(RecipeGUIController recipeGUIController) {
        this.recipeGUIController = recipeGUIController;
    }

    public void createRecipeWindow() {
        Stage recipeStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../recipeStage/RecipeMainScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeStage.setScene(new Scene(root, 535, 640));
        recipeStage.setResizable(false);
        recipeStage.show();
    }
}
