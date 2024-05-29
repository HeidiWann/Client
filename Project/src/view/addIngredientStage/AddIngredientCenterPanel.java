package view.addIngredientStage;


import controller.AddIngredientGUIController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import model.GetGUIController;
import model.Ingredient;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddIngredientCenterPanel implements Initializable {
    @FXML
    public Button searchButton;
    @FXML
    public ListView<Ingredient> ingredientField;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public TextField searchField;
    private Ingredient selectedIngredient;
    private ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
    private Parent root;


    public void search(ActionEvent actionEvent) throws IOException {
        System.out.println("Test av search knappen, sökord: " + searchField.getText());
        String searchedText = searchField.getText();
        this.ingredients.clear();

        if (!(Objects.equals(searchedText, ""))) {
            System.out.println(searchedText);
            System.out.println(searchedText + " test searchText!=null");
            ArrayList<Ingredient> matchedIngredients = GetGUIController.getAddIngredientGUIController().search(searchedText);
            addIngredientsToTextField(matchedIngredients);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingredientField.setItems(ingredients);

        ingredientField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Ingredient>() {
            @Override
            public void changed(ObservableValue<? extends Ingredient> observableValue, Ingredient ingredient, Ingredient t1) {
                selectedIngredient = ingredientField.getSelectionModel().getSelectedItem();

                if (selectedIngredient != null)
                    GetGUIController.getAddIngredientGUIController().setSelectedIngredient(selectedIngredient);
            }
        });
        this.ingredients.addAll(GetGUIController.getAddIngredientGUIController().fetchAllIngredients());
    }

    public void addIngredientsToTextField(ArrayList<Ingredient> ingredients) {
        this.ingredients.clear();
        //TODO ändra så listView är strings o inte object
        ArrayList<String> showList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient);
            showList.add(ingredient.toString());
        }
        this.ingredients.addAll(ingredients);
    }

    public Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }
}
