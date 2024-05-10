package view.recipeCreationStage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeCreationBottomLayer implements Initializable {
    @FXML
    private Button createnewRecipeButton;
    @FXML
    private Button choosePicture;
    @FXML
    private ImageView recipeImageView;
    private File selectedImageFile;

    public RecipeCreationBottomLayer() {
        
    }

    /**
     * @author Salma Omar
     */
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


    public void createOwnRecipe(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choosePicture.setOnAction(new ButtonHandler());
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();

            if (clickedButton.getText().equals("Välj bild")) {
                chooseImage();
            }
        }
    }

}
