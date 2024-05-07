package view.mainStage;

import controller.MainGUIController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.FoodCategory;
import model.GetGUIController;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class initializes the CenterPanel of the {@link MainStage}
 *
 * @author Anton Persson
 */
public class CenterPanel implements Initializable {
    @FXML
    private Button chickenButton, veganButton, cowButton, vegetarianButton, searchButton, pigButton, fishButton;
    @FXML
    private TextField searchField;
    @FXML
    private Label currentCategories;
    private MainGUIController mainGuiController;

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
    }

    public void setCurrentCategories(String categories) {
        currentCategories.setText(categories);
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
                mainGuiController.addFilter(FoodCategory.Cow);
            } else if (clickedButton.getText().equals("Gris")) {
                mainGuiController.addFilter(FoodCategory.Pig);
            } else if (clickedButton.getText().equals("Fisk")) {
                mainGuiController.addFilter(FoodCategory.Fish);
            } else if (clickedButton.getText().equals("Vegan")) {
                mainGuiController.addFilter(FoodCategory.Vegan);
            } else if (clickedButton.getText().equals("Vegetarian")) {
                System.out.println("Knppen funkade");
                mainGuiController.addFilter(FoodCategory.Vegetarian);
            } else if (clickedButton.getText().equals("Kyckling")) {
                mainGuiController.addFilter(FoodCategory.Chicken);
            } else if (clickedButton.getText().equals("Sök")) {
                mainGuiController.searchForRecipe(searchField.getText());
                searchField.setText("");
                searchField.setPromptText("Sök efter recept, maträtter, etc...");
            }
        }
    }
}
