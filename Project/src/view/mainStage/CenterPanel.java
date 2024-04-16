package view.mainStage;

import controller.GUIController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CenterPanel implements Initializable {
    @FXML
    private Button chickenButton, veganButton, cowButton, vegetarianButton, searchButton, pigButton, fishButton;
    @FXML
    private TextField searchField;
    private GUIController guiController;
    public CenterPanel(){}

    public CenterPanel(GUIController guiController) {
        this.guiController = guiController;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
