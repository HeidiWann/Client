package view.mainStage;

import controller.MainGUIController;
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
    private MainGUIController mainGuiController;
    public CenterPanel(){}

    public CenterPanel(MainGUIController mainGuiController) {
        this.mainGuiController = mainGuiController;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
