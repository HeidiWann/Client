package view.recipeStage;

import controller.GUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeMainScene {
    private GUIController guiController;
    public RecipeMainScene(GUIController guiController) {
        this.guiController = guiController;
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
