package view.recipeStage;

import controller.RecipeGUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import model.GetGUIController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class intializes the south panel of the {@link RecipeSouthPanel}
 * @author Anton Persson
 */
public class RecipeSouthPanel implements Initializable {
    @FXML
    private TextArea textArea;
    public RecipeSouthPanel() {
        RecipeGUIController recipeGUIController = GetGUIController.getrecipeGUIController();
        recipeGUIController.setRecipeSouthPanel(this);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setWrapText(true);
    }

    public void insertInstructions(String instructions) {
        textArea.insertText(0,instructions);
    }
}
