package view.mainStage;

import controller.MainGUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import model.FoodCategory;
import model.GetGUIController;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class initializes the CenterPanel of the {@link MainStage}
 *
 * @author Anton Persson
 */
public class CenterPanel implements Initializable {
    @FXML
    private Button chickenButton, veganButton, cowButton, vegetarianButton, searchButton, pigButton, fishButton, resetButton;
    @FXML
    private TextField searchField;
    @FXML
    private Label currentCategories;
    private MainGUIController mainGuiController;
    private boolean nötPressed = false;
    private boolean fläskPressed = false;
    private boolean fiskPressed = false;
    private boolean veganPressed = false;
    private boolean vegetarianPressed = false;
    private boolean kycklingPressed = false;

    public CenterPanel() {
        this.mainGuiController = GetGUIController.getGuiController();
        mainGuiController.setCenterPanel(this);
    }

    /**
     * This method gives the buttons action handlers.
     *
     * @param url
     * @param resourceBundle
     * @author Anton Persson
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cowButton.setOnAction(new ButtonHandler());
        pigButton.setOnAction(new ButtonHandler());
        fishButton.setOnAction(new ButtonHandler());
        veganButton.setOnAction(new ButtonHandler());
        vegetarianButton.setOnAction(new ButtonHandler());
        chickenButton.setOnAction(new ButtonHandler());
        searchButton.setOnAction(new ButtonHandler());
        resetButton.setOnAction(new ButtonHandler());
        searchField.setOnKeyPressed(new SearchHandler());
    }

    public void setCurrentCategories(String categories) {
        currentCategories.setText("Valda kategorier | " + categories);
    }

    /**
     * This class is used to handle the functionalities behind the buttons in the panel
     *
     * @author Anton Persson
     */
    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Button clickedButton = (Button) actionEvent.getSource();

            if (clickedButton.getText().equals("Nöt")) {
                if (nötPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    nötPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    nötPressed = true;
                }
                mainGuiController.addFilter(FoodCategory.Nöt);
            } else if (clickedButton.getText().equals("Fläsk")) {
                if (fläskPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    fläskPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    fläskPressed = true;
                }
                mainGuiController.addFilter(FoodCategory.Fläsk);
            } else if (clickedButton.getText().equals("Fisk")) {
                if (fiskPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    fiskPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    fiskPressed = true;
                }
                mainGuiController.addFilter(FoodCategory.Fisk);
            } else if (clickedButton.getText().equals("Vegan")) {
                if (veganPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    veganPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    veganPressed = true;
                }
                mainGuiController.addFilter(FoodCategory.Vegan);
            } else if (clickedButton.getText().equals("Vegetarian")) {
                if (vegetarianPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    vegetarianPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    vegetarianPressed = true;
                }
                System.out.println("Knppen funkade");
                mainGuiController.addFilter(FoodCategory.Vegetarian);
            } else if (clickedButton.getText().equals("Kyckling")) {
                if (kycklingPressed) {
                    clickedButton.setStyle("-fx-background-color: #81E86D;");
                    kycklingPressed = false;
                } else {
                    clickedButton.setStyle("-fx-background-color: #FFAEBC;");
                    kycklingPressed = true;
                }
                mainGuiController.addFilter(FoodCategory.Kyckling);
            } else if (clickedButton.getText().equals("Sök")) {
                mainGuiController.searchForRecipe(searchField.getText());
                searchField.setText("");
                searchField.setPromptText("Sök efter recept, maträtter, etc...");
            } else if (clickedButton.getText().equals("Återställ")) {
                System.out.println("Fungerar du?");
                mainGuiController.resetSearchAndCategory();
                cowButton.setStyle("-fx-background-color: #81E86D;");
                chickenButton.setStyle("-fx-background-color: #81E86D;");
                veganButton.setStyle("-fx-background-color: #81E86D;");
                vegetarianButton.setStyle("-fx-background-color: #81E86D;");
                fishButton.setStyle("-fx-background-color: #81E86D;");
                pigButton.setStyle("-fx-background-color: #81E86D;");

                nötPressed = false;
                fläskPressed = false;
                fiskPressed = false;
                veganPressed = false;
                vegetarianPressed = false;
                kycklingPressed = false;
            }
        }
    }

    private class SearchHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                mainGuiController.searchForRecipe(searchField.getText());
                searchField.setText("");
                searchField.setPromptText("Sök efter recept, maträtter, etc...");
            }
        }
    }
}
