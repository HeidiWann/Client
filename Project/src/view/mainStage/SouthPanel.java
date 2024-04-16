package view.mainStage;

import controller.MainGUIController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.GetGUIController;
import model.Recipe;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SouthPanel implements Initializable {
    /**
     * This is a {@link ListView} that holds {@link Recipe}. A ListView allows us to vertically display and store
     * objects. It has a FXML tag since it is gathered from the FXML file SouthPanel.
     */
    @FXML
    private ListView<Recipe> recipeField;
    @FXML
    private ScrollPane scrollPane;
    private MainGUIController mainGuiController;

    /**
     * This is a {@link ObservableList} that holds {@link Recipe}. It is basically an ArrayList with the difference
     * being that the list is linked with the {@link ListView} and keeps track of when something is changed in the list.
     * This means that when something is change in the ArrayList, the ListView also gets changed.
     */
    private ObservableList<Recipe> recipes = FXCollections.observableArrayList();

    public SouthPanel() {
        this.mainGuiController = GetGUIController.getGuiController();
    }

    public void setGuiController(MainGUIController mainGuiController) {
        this.mainGuiController = mainGuiController;
    }

    /**
     * This method initializes the components from the FXML file SouthPanel. It also creates a custom "CellFactory" to
     * allow us to store both text and images in the {@link ListView}. It uses a lambada "->" expression to
     * automatically format each item that is added to the list as the custom {@link ListCell}. You need to (I think)
     * crate a cell in order to store something in the ListVIew.
     * @param url I think this is the url to the FXML file?
     * @param resourceBundle No idea what this does.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scrollPane.setContent(recipeField);
        scrollPane.setPickOnBounds(false);
        recipeField.setOnMouseClicked(new ListViewHandler());

        Image image = new Image("/view/NotLoggedIn.png");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        recipes.add(new Recipe("Gör detta", imageView, null, "SOUP",null));


        recipeField.setCellFactory(listView -> new ListCell<Recipe>() {
            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(15);
                    hBox.setAlignment(Pos.CENTER);

                    hBox.getChildren().addAll(
                            recipe.getImageOfRecipe(),
                            new Label(recipe.getRecipeName())
                    );
                    setGraphic(hBox);
                }
            }
        });
        recipeField.setItems(recipes);
        addItems();
    }

    public void addItems() {
        Image image = new Image("/view/NotLoggedIn.png");

        for (int i = 0; i < 10; i++) {
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);
            recipes.add(new Recipe("Gör detta", imageView, null, "SOUP", null));
        }

    }

    /**
     * This method replaces the list of the recipes and since the list of recipes automatically updates the
     * {@link ListView}, nothing else needs to be done
     * @param recipes
     */
    public void addRecipes(ArrayList<Recipe> recipes) {
        this.recipes.removeAll();
        this.recipes.addAll(recipes);
    }

    private class ListViewHandler extends Thread implements EventHandler<MouseEvent>  { // -Testa om den verligen behöver ärva en tråd
        @Override
        public void handle(MouseEvent mouseEvent) {
            mainGuiController.getRecipeGUIController().showSelectedRecipe(recipeField.getSelectionModel().getSelectedItem());
        }
    }
}