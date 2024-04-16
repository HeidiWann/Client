package view.mainStage;

import controller.GUIController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.GetGUIController;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NorthPanel implements Initializable {
    @FXML
    private Button profileButton;
    private GUIController guiController;
    public NorthPanel() {
        this.guiController = GetGUIController.getGuiController();
    }

    public GUIController getGuiController() {
        return this.guiController;
    }

    public NorthPanel(GUIController guiController) {
        this.guiController = guiController;
    }

    public void showProfileWindow(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "You have chosen to log in");
    }

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

        System.out.println("Hej");
    }
}
