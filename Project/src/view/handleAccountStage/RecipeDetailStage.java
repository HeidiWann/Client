package view.handleAccountStage;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Recipe;

public class RecipeDetailStage {
    public void displayRecipeDetails(Recipe recipe) {
        Stage stage = new Stage();
        stage.setTitle("Recipe Details");

        Label nameLabel = new Label("Name: " + recipe.getRecipeName());
        Label dishLabel = new Label("Dish: " + recipe.getDish());
        Label instructionsLabel = new Label("Instructions: " + recipe.getInstructions());
        Label authorLabel = new Label("Author: " + recipe.getAuthor());

        ImageView recipeImageView = new ImageView();
        if (recipe.getImageOfRecipe() != null) {
            recipeImageView.setImage(new Image(String.valueOf(recipe.getImageViewOfRecipe())));
        }

        VBox vbox = new VBox(10, nameLabel, dishLabel, instructionsLabel, authorLabel, recipeImageView);
        Scene scene = new Scene(vbox, 400, 300);

        stage.setScene(scene);
        stage.show();
    }
}
