package view.mainStage;

import controller.MainGUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.GetGUIController;
import view.userStage.UserMainScene;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The method initializes the north panel of the stage.
 *
 * @author Anton Persson
 */
public class NorthPanel implements Initializable {
    @FXML
    private Button profileButton;
    @FXML
    private Circle loggedInStatus;
    private MainGUIController mainGuiController;

    public NorthPanel() {
        this.mainGuiController = GetGUIController.getGuiController();
        mainGuiController.setNorthPanel(this);
    }

    public MainGUIController getGuiController() {
        return this.mainGuiController;
    }

    public NorthPanel(MainGUIController mainGuiController) {
        this.mainGuiController = mainGuiController;
    }

    public void showProfileWindow(ActionEvent e) {
        mainGuiController.startLogInWindow();
    }

    public void setLoggedInStatus(boolean loggedIn) {
        if (loggedIn) {
            loggedInStatus.setFill(Color.rgb(150, 255, 150));
            loggedInStatus.setStroke(Color.rgb(150, 255, 150));
        } else {
            loggedInStatus.setFill(Color.rgb(255, 100, 100));
            loggedInStatus.setStroke(Color.rgb(255, 100, 100));
        }
    }

    /**
     * This method inserts an image to the panel
     *
     * @param url
     * @param resourceBundle
     * @author Anton Persson
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("/view/NotLoggedIn.png"); //getClass().getResourceAsStream("/view/NotLoggedIn.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(45);
        imageView.setFitWidth(48);
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);

        profileButton.setGraphic(imageView);

        loggedInStatus.setFill(Color.rgb(255, 100, 100));
        loggedInStatus.setStroke(Color.rgb(255, 100, 100));
    }
}
