package view.recipeCreationStage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecipeCreationBottomMiddleLayer implements Initializable {
    @FXML
    private Button addIngredient;
    @FXML
    private ListView<String> listOfaddedIngredients;
    @FXML
    private TextField ingredientsField;
    private ObservableList<String> listOfIngredients;
    public RecipeCreationBottomMiddleLayer() {
        System.out.println("Nådde konstruktorn i RCBML");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addIngredient.setOnAction(new ButtonHandler());
        listOfIngredients = FXCollections.observableArrayList();
        listOfaddedIngredients.setItems(listOfIngredients);
    }

    public void addIngredientToList(String ingredientToAdd) {
        listOfIngredients.add(ingredientToAdd);
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();
            if (clickedButton.getText().equals("Lägg till ingrediens")) {
                addIngredientToList("Potatis");
            }
        }
    }
}
