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

/**
 * Controller class for the bottom layer of the recipe creation stage.
 * Manages the actions for buttons related to recipe creation, image selection, and category management.
 * Implements Initializable for JavaFX initialization.
 *
 * @Author: Anton Persson
 * @Author: Salma Omar
 */
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

    /**
     * Default constructor initializes the recipe creation controller and sets this layer.
     *
     * @Author: Anton Persson
     */
    public RecipeCreationBottomLayer() {
        recipeCreationController = GetGUIController.getRecipeCreationController();
        recipeCreationController.setRecipeCreationBottomLayer(this);
    }

    /**
     * Handles the image selection process. Opens a file chooser to select an image file.
     * Sets the selected image to the ImageView if a file is chosen.
     *
     * @Author: Salma Omar
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

    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     *
     * @Author: Salma Omar
     * @Author: Anton Persson
     */
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

    /**
     * Inserts a selected category into the list of chosen categories.
     * Checks if the category is already added to avoid duplicates.
     *
     * @param chosenCategory The category to be added to the list of chosen categories.
     * @Author: Anton Persson
     */
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

    /**
     * Inner class for handling button actions.
     *
     * @Author: Salma Omar
     * @Author: Anton Persson
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        /**
         * Handles button click events and performs actions based on which button was clicked.
         *
         * @param actionEvent The event that occurred.
         */
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