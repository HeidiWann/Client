package view.recipeStage;

import controller.GUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import model.GetGUIController;

import javax.swing.text.html.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeCenterPanel implements Initializable {
    @FXML
    ImageView imageOfRecipe;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TextArea ingredientsInfo;
    private GUIController guiController;
    public RecipeCenterPanel(){
        this.guiController = GetGUIController.getGuiController();
        guiController.setRecipeCenterPanel(this);
        System.out.println("Nått centerpanelen");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initierade centerpanelen");
        scrollPane.setContent(ingredientsInfo);
    }

    public void insertText(String text) {
        ingredientsInfo.insertText(1, text);
    }
}
