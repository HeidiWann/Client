package view.recipeCreationStage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeCreationTopMiddleLayer implements Initializable {
    @FXML
    private TextField recipeNameField;
    @FXML
    private TextArea instructionsArea;
    public RecipeCreationTopMiddleLayer() {
        System.out.println("MNådde konstruktorn");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
