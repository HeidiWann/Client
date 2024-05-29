package view.recipeCreationStage;

import controller.RecipeCreationController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.GetGUIController;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecipeCreationBottomLayer implements Initializable {
    @FXML
    private Button createnewRecipeButton;
    @FXML
    private Button choosePicture;
    @FXML
    private Button cancel;
    @FXML
    private Button addCategoryButton;
    @FXML
    private Button removeCategoriesButton;
    @FXML
    private ImageView recipeImageView;
    @FXML
    private ChoiceBox<String> categoryDropList;
    @FXML
    private ListView<String> chosenCategories;

    private File selectedImageFile;
    private RecipeCreationController recipeCreationController;
    private ObservableList<String> listOfCategories;
    private ObservableList<String> listOfChosenCategories;

    public RecipeCreationBottomLayer() {
        recipeCreationController = GetGUIController.getRecipeCreationController();
        recipeCreationController.setRecipeCreationBottomLayer(this);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choosePicture.setOnAction(new ButtonHandler());
        createnewRecipeButton.setOnAction(new ButtonHandler());
        cancel.setOnAction(new ButtonHandler());
        addCategoryButton.setOnAction(new ButtonHandler());
        removeCategoriesButton.setOnAction(new ButtonHandler());

        listOfCategories = FXCollections.observableArrayList();
        listOfCategories.addAll(recipeCreationController.getEveryCategory());
        categoryDropList.setItems(listOfCategories);

        listOfChosenCategories = FXCollections.observableArrayList();
        chosenCategories.setItems(listOfChosenCategories);

    }

    public void insertCategoryToList(String chosenCategory) {
        boolean categoryAlreadyAdded = false;
        for (String category : listOfChosenCategories) {
            if (category.equals(chosenCategory)) {
                categoryAlreadyAdded = true;
                break;
            }
        }
        if (categoryAlreadyAdded) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Kategori redan tillagd");
            alert.setHeaderText(null);
            alert.setContentText("Kategorin är redan tillagd");
            alert.showAndWait();
        } else {
            listOfChosenCategories.add(chosenCategory);
        }
    }

    public ImageView getImageOfRecipe() {
        return recipeImageView;
    }

    public ArrayList<String> getListOfCategories() {
        ArrayList<String> listToReturn = new ArrayList<>(listOfChosenCategories);
        return listToReturn;
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();

            if (clickedButton.getText().equals("Välj bild")) {
                chooseImage();
            } else if(clickedButton.getText().equals("Skapa recept")) {
                if (recipeCreationController.createRecipe()) {
                    Stage stage = (Stage) createnewRecipeButton.getScene().getWindow();
                    stage.close();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Något gick fel");
                    alert.setHeaderText(null);
                    alert.setContentText("All information är inte ifylld. Vänligen fyll i alla rutor");
                    alert.showAndWait();
                }
            } else if(clickedButton.getText().equals("Avbryt")) {
                recipeCreationController.closeWindow(cancel);
            } else if (clickedButton.getText().equals("Lägg till")) {
                if (categoryDropList.getSelectionModel().getSelectedItem() != null) {
                    insertCategoryToList(categoryDropList.getSelectionModel().getSelectedItem());
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ingen kategori vald");
                    alert.setHeaderText(null);
                    alert.setContentText("Vänligen välj en kategori först.");
                    alert.showAndWait();
                }
            } else if (clickedButton.getText().equals("Ta bort alla")) {
                listOfChosenCategories.clear();
            }
        }
    }
}