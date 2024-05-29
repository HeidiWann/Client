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
    public ListView<String> ingredientField;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public TextField searchField;
    private int selectedIngredient;
    private ObservableList<String> ingredients = FXCollections.observableArrayList();
    private Parent root;


    public void search(ActionEvent actionEvent) throws IOException {
        System.out.println("Test av search knappen, sökord: " + searchField.getText());
        String searchedText = searchField.getText();
        this.ingredients.clear();
            System.out.println(searchedText);
            System.out.println(searchedText + " test searchText!=null");
            ArrayList<Ingredient> matchedIngredients = GetGUIController.getAddIngredientGUIController().search(searchedText);
            addIngredientsToTextField(matchedIngredients);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ingredientField.setItems(ingredients);

        ingredientField.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String ingredient, String t1) {
                selectedIngredient = ingredientField.getSelectionModel().getSelectedIndex();
                GetGUIController.getAddIngredientGUIController().setSelectedIngredient(selectedIngredient);
            }
        });
        this.ingredients.addAll(GetGUIController.getAddIngredientGUIController().fetchAllIngredientsAsStrings());
    }

    public void addIngredientsToTextField(ArrayList<Ingredient> ingredients) {
        this.ingredients.clear();
        //TODO ändra så listView är strings o inte object
        ArrayList<String> showList = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient);
            showList.add(ingredient.toString2());
        }
        this.ingredients.addAll(showList);
    }
}